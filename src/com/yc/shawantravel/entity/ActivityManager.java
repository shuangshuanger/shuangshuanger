package com.yc.shawantravel.entity;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class ActivityManager extends Application{

	private List<Activity> activityList = new LinkedList<Activity>();
	private static ActivityManager instance;
	
	private ActivityManager(){}
	
	public static ActivityManager getInstace()
	{
		if ( null == instance )
		{
			instance = new ActivityManager();
		}
		return instance;
	}
	
	public void addActivity(Activity aty)
	{
		activityList.add(aty);
	}
	
	public void exit()
	{
		for (Activity aty:activityList)
		{
			aty.finish();
		}
		System.exit(0);
	}
}
