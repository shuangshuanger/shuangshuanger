package com.yc.shawantravel;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;

import com.yc.shawantravel.entity.ActivityManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.NotificationCompat.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class SettingActivity extends KJActivity{

	@BindView(id = R.id.notificationTog, click = true)
	private ToggleButton notificationTog;
	@BindView(id = R.id.shakeTog, click = true)
	private ToggleButton shakeTog;
	@BindView(id = R.id.setting3, click = true)
	private View updateSoftwareView;
	@BindView(id = R.id.setting4, click = true)
	private View aboutUsView;
	@BindView(id = R.id.setting5, click = true)
	private View feedbackView;
	
	@BindView(id = R.id.setting_back, click = true)
	private ImageButton backBtn;
	@BindView(id = R.id.homebtn, click = true)
	private ImageButton homeBtn;
	@BindView(id = R.id.nearbtn, click = true)
	private ImageButton nearBtn;
	@BindView(id = R.id.collectionbtn, click = true)
	private ImageButton collectionBtn;
	
	private SharedPreferences settings;
	private Editor editor;
	
	private NotificationManager notificationManager;
	private Notification notification;
	private Builder builder;
	private static final int NOTICE_ID = 111;
	boolean isNotificationChecked;
	boolean isShakeChecked;
	
	@Override
	public void setRootView() {
		// TODO Auto-generated method stub
		this.setContentView(R.layout.setting);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		
		ActivityManager.getInstace().addActivity(aty);
		
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		builder = new Builder(aty);
		
		settings = getSharedPreferences("setting", Activity.MODE_PRIVATE);
		editor = settings.edit();
		
		isNotificationChecked = settings.getBoolean("notification", notificationTog.isChecked());
		isShakeChecked = settings.getBoolean("shake", shakeTog.isChecked());
	}

	@Override
	public void initWidget() {
		// TODO Auto-generated method stub
		super.initWidget();
		
		notificationTog.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			@SuppressLint("InlinedApi")
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean ischecked) {
				// TODO Auto-generated method stub	
				notificationTog.setChecked(ischecked);

				editor.remove("notification");
				editor.putBoolean("notification", ischecked);
				editor.commit();
				
				notificationTog.setButtonDrawable(null);
				if ( ischecked )
				{					
					SettingActivity.this.notificationForUs(isNotificationChecked);
					SettingActivity.this.shakeForUs(isShakeChecked);
					
					notificationTog.setButtonDrawable(R.drawable.btn_open);
				}
				
				else
				{
					notificationTog.setButtonDrawable(R.drawable.btn_close);
					notificationManager.cancel(NOTICE_ID);
				}
			}			
		});
		
		shakeTog.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean ischecked) {
				// TODO Auto-generated method stub
				editor.remove("shake");
				editor.putBoolean("shake", ischecked);
				editor.commit();
				
				shakeTog.setButtonDrawable(null);
				if ( ischecked )
				{		
					shakeTog.setButtonDrawable(R.drawable.btn_open);
				}
				
				else
				{
					shakeTog.setButtonDrawable(R.drawable.btn_close);
				}
				
				SettingActivity.this.shakeForUs(ischecked);
			}		
		});
		
		updateSoftwareView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UpdateManager updateManager = new UpdateManager(aty);
				updateManager.checkUpdate();
			}			
		});
		
		aboutUsView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showActivity(aty,AboutSoftwareActivity.class);
				SettingActivity.this.finish();
			}		
		});
		
		feedbackView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showActivity(aty,FeedbackActivity.class);
				SettingActivity.this.finish();
			}
			
		});
		
		backBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SettingActivity.this.finish();
			}
        });
		
		homeBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showActivity(aty,HomeActivity.class);
				SettingActivity.this.finish();
			}			
		});
		
		collectionBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showActivity(aty,CollectionActivity.class);
				SettingActivity.this.finish();
			}     	
        });
        
        nearBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showActivity(aty,NearActivity.class);
				SettingActivity.this.finish();
			}     	
        });
	}

	@Override
	public void widgetClick(View v) {
		// TODO Auto-generated method stub
		super.widgetClick(v);
	}
	
	@SuppressLint("InlinedApi")
	public void notificationForUs(boolean isNotificationChecked)
	{  
		if ( isNotificationChecked )
		{
			builder.setContentTitle("休闲沙湾")
			.setContentText("现在可以接受后台消息了")
			.setTicker("来沙湾吧！")
			.setWhen(System.currentTimeMillis())
			.setAutoCancel(true)
			.setSmallIcon(R.drawable.icon);
			
			Intent intent = new Intent(aty,HomeActivity.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(aty, 0, intent, 0);
			builder.setContentIntent(pendingIntent);
		
			notification = builder.build();
			notificationManager.notify(NOTICE_ID, notification);
		}
	}
	
	public void shakeForUs(boolean isShakeChecked)
	{
		if ( isShakeChecked )
		{
			builder.setDefaults(Notification.DEFAULT_VIBRATE);			
		}
		
		else
		{
			builder.setDefaults(Notification.DEFAULT_SOUND);
		}
		notification = builder.build();
		notificationManager.notify(NOTICE_ID, notification);
	}
}
