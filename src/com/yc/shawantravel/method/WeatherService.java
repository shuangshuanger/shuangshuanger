package com.yc.shawantravel.method;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


public class WeatherService extends Service {

	
	private IBinder binder = new SimpleBinder();
	
	public class SimpleBinder extends Binder
	{
		public WeatherService getService()
		{
			return WeatherService.this;
		}
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return this.binder;
	}
}
