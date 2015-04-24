package com.yc.shawantravel;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.yc.shawantravel.entity.PointList;

import android.os.Bundle;
import android.view.View;


public class LocationActivity extends KJActivity {

	@BindView(id = R.id.map_view, click = true)
	private MapView mMapView;
	
	private BaiduMap map;
	private LatLng point;
	
	@Override
	public void setRootView() {
		// TODO Auto-generated method stub
		//在使用SDK各组件之前初始化context信息，传入ApplicationContext          
		//注意该方法要再setContentView方法之前实现 
		SDKInitializer.initialize(getApplicationContext());
		this.setContentView(R.layout.location); 
	} 
	
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		
		map = mMapView.getMap();
		map.setMapType(BaiduMap.MAP_TYPE_NORMAL);
				
		Bundle bundle = this.getIntent().getExtras();
		String name = bundle.getString("name");

		PointList pointList = new PointList();
		point = pointList.getPoitnList().get(name);
        MapStatus mMapStatus = new MapStatus.Builder().target(point).zoom(15)
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory
                .newMapStatus(mMapStatus);
        // 改变地图状态
        map.setMapStatus(mMapStatusUpdate);
        mMapView.refreshDrawableState();
        
		//标注覆盖物		
		BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.icon_marker);
		OverlayOptions option = new MarkerOptions().position(point).icon(icon);
		map.addOverlay(option);
	}

	@Override
	public void initWidget() {
		// TODO Auto-generated method stub
		super.initWidget();
	}

	@Override
	public void widgetClick(View v) {
		// TODO Auto-generated method stub
		super.widgetClick(v);
	}
	
	@Override      
	protected void onDestroy() 
	{          
		super.onDestroy();          
		//在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理          
		mMapView.onDestroy();      
	}     
	
	@Override      
	protected void onResume() 
	{          
		super.onResume();          
		//在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理          
		mMapView.onResume();          	
	}      
	
	@Override      
	protected void onPause() 
	{          
		super.onPause();          
		//在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理          
		mMapView.onPause();          
	} 
}
	