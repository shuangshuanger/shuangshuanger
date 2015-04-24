package com.yc.shawantravel;

import java.util.List;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.yc.shawantravel.entity.ActivityManager;


public class PoiSearchActivity extends KJActivity{

	@BindView(id = R.id.map_view, click = true)
	private MapView mMapView;
	
	private BaiduMap map = null;
	
	private PoiSearch mPoiSearch;
	private String kind = null;
	
	private LocationClient locationClient = null;
	private static final int UPDATE_TIME = 1000;
	private LatLng latlng = null;
	
	private Handler mHandler = new Handler();
	private Runnable r = new Runnable()
	{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			if ( latlng != null && kind != null)
			{
				map = mMapView.getMap();
				map.setMapType(BaiduMap.MAP_TYPE_NORMAL);

				MapStatus mMapStatus = new MapStatus.Builder().target(latlng).zoom(16)
						.build();
				MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory
						.newMapStatus(mMapStatus);
				// 改变地图状态
				map.setMapStatus(mMapStatusUpdate);
				mMapView.refreshDrawableState();
				
				mPoiSearch.searchNearby(new PoiNearbySearchOption()
	    		.keyword(kind)
	    		.location(latlng)
	    		.radius(1000)
	    		);
				mHandler.removeCallbacks(r);
			}
			
			else
				mHandler.postDelayed(r, 2000);
		}	
	};
	
	@Override
	public void setRootView() {
		// TODO Auto-generated method stub
		SDKInitializer.initialize(getApplicationContext());
		this.setContentView(R.layout.location);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		
		ActivityManager.getInstace().addActivity(aty);

		Bundle bundle = this.getIntent().getExtras();
		kind = bundle.getString("kind");
		
        this.setLatLng();
        mHandler.post(r);
	}

	@Override
	public void initWidget() {
		// TODO Auto-generated method stub
		super.initWidget();
		
		//搜索
        mPoiSearch = PoiSearch.newInstance();
		OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener()
    	{
			@Override
			public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
				// TODO Auto-generated method stub
				Log.d("详细结果",poiDetailResult.toString());
			}

			@Override
			public void onGetPoiResult(PoiResult poiResult) {
				// TODO Auto-generated method stub
				Log.d("结果",poiResult.toString());	
				//标注覆盖物					
				PoiOverlay poiOverlay = new PoiOverlay(map);
				poiOverlay.setData(poiResult);
				List<OverlayOptions> option = poiOverlay.getOverlayOptions();
				for ( int i = 0; i < option.size(); i++ )
				{
					map.addOverlay(option.get(i));
				}
			}   		
    	};
    	
    	mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
	}
	
	@Override      
	protected void onDestroy() 
	{          
		super.onDestroy();          
		//在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理          
		mMapView.onDestroy();      
		mHandler.removeCallbacks(r);
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

	public void setLatLng()
	{
		locationClient = new LocationClient(this);
        //设置定位条件
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);        //是否打开GPS
        option.setCoorType("bd09ll");       //设置返回值的坐标类型
        option.setProdName("shawantravel"); //设置产品线名称
        option.setScanSpan(UPDATE_TIME);    //设置定时定位的时间间隔 单位毫秒
        option.setAddrType("all");
        locationClient.setLocOption(option);
        locationClient.start();
        
        //注册位置监听器
        locationClient.registerLocationListener(new BDLocationListener() {
        	@Override
        	public void onReceiveLocation(BDLocation location) {
        		// TODO Auto-generated method stub
        		if (location == null) 
        		{
        			return;
        		}
        		
        		latlng = new LatLng(location.getLatitude(), location.getLongitude());
        	}
        });		
	}
}
