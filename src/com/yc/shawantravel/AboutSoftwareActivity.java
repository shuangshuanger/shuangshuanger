package com.yc.shawantravel;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yc.shawantravel.entity.ActivityManager;

public class AboutSoftwareActivity extends KJActivity{
	
	@BindView(id = R.id.aboutsoftware_back, click = true)
	private Button backBtn;
	@BindView(id = R.id.aboutsoftware_exit, click = true)
	private Button exitBtn;
	@BindView(id = R.id.phonenumber)
	private TextView phone;
	@BindView(id = R.id.website)
	private TextView website;
	
	private String phoneNum;
	private String webSite;

	@Override
	public void setRootView() {
		// TODO Auto-generated method stub
		this.setContentView(R.layout.aboutsoftware);
	}
	
	@Override
    public void initData() {
        super.initData(); 
        
        phoneNum = "0991-3634616";
        webSite = "www.yc-e.net";
        
        ActivityManager.getInstace().addActivity(aty);
    }

	@Override
	public void initWidget() {
		// TODO Auto-generated method stub
		super.initWidget();
		
		phone.setText(phone.getText().toString() + phoneNum);
		website.setText(website.getText().toString() + webSite);
	}
	
	@Override
	public void widgetClick(View v) {
		// TODO Auto-generated method stub
		super.widgetClick(v);
		
		switch( v.getId() ) {
		case R.id.aboutsoftware_back:
			this.finish();
			break;
		case R.id.aboutsoftware_exit:
			ActivityManager.getInstace().exit();
			break;
		}
	}
}
