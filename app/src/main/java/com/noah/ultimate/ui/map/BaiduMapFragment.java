package com.noah.ultimate.ui.map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.google.android.gms.maps.MapView;
import com.noah.ultimate.R;

public class BaiduMapFragment extends Fragment {

    private FragmentManager mParentFragManager;
    private static Activity mActivity;
    private static MapView mMapView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BaiduMapFragment", "onCreate()");
        mParentFragManager = getFragmentManager();
        setHasOptionsMenu(true);

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(getActivity().getApplicationContext());
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i("BaiduMapFragment", "onCreateView()");

        View view = inflater.inflate(R.layout.map_split_fragment, container, false);

        //获取地图控件引用
        mMapView = (MapView) view.findViewById(R.id.mapView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("BaiduMapFragment", "onActivityCreated()");
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
//        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
}
