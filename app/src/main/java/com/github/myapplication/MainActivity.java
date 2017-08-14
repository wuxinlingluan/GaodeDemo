package com.github.myapplication;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.CheckPermissionsActivity;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends CheckPermissionsActivity implements INaviInfoCallback {


    @InjectView(R.id.bt_gaode_inner)
    Button btGaodeInner;
    @InjectView(R.id.bt_gaode_out)
    Button btBaiduOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.bt_gaode_inner, R.id.bt_gaode_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_gaode_inner://高德
                LatLng epoint = new LatLng(36, 117);
                Poi epoi = new Poi("粮仓", epoint, "");
                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), new AmapNaviParams(epoi), MainActivity.this);
                break;
            case R.id.bt_gaode_out://百度
                if (AMapUtil.isInstallByRead("com.autonavi.minimap")) {
                    AMapUtil.goToNaviActivity(this, "test", null, "34.264642646862", "108.95108518068", "1", "2");
                }
                break;
        }
    }


    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onGetNavigationText(String s) {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }

    @Override
    public void onArriveDestination(boolean b) {

    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {

    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onStopSpeaking() {

    }
}
