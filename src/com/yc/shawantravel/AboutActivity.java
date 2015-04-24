package com.yc.shawantravel;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;

import com.yc.shawantravel.entity.ActivityManager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends KJActivity{

	@BindView(id = R.id.about_img)
	private ImageView img;
	@BindView(id = R.id.whichpage)
	private TextView text;
	@BindView(id = R.id.back, click = true)
	private ImageButton backBtn;
	@BindView(id = R.id.btn_search, click = true)
	private ImageButton searchBtn;
	@BindView(id = R.id.homebtn, click = true)
	private ImageButton homeBtn;
	@BindView(id = R.id.nearbtn, click = true)
	private ImageButton nearBtn;
	@BindView(id = R.id.collectionbtn, click = true)
	private ImageButton collectionBtn;
	
	@Override
	public void setRootView() {
		// TODO Auto-generated method stub
		this.setContentView(R.layout.about);
	}
	
	@Override
    public void initData() {
        super.initData(); 
        
        ActivityManager.getInstace().addActivity(aty);
    }
	
	@Override
    public void initWidget() {
        super.initWidget();
        
        text.setText("关于"); 
        backBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AboutActivity.this.finish();
			}
        }); 
        
        searchBtn.setOnClickListener(new OnClickListener()
        {
			@SuppressLint("InlinedApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				View view = LayoutInflater.from(aty).inflate(R.layout.dialog, null);
				final EditText editText = (EditText) view.findViewById(R.id.search_content);
				
				Builder dialog = new AlertDialog.Builder(aty,AlertDialog.THEME_HOLO_LIGHT);
				dialog.setTitle("搜索");
				dialog.setIcon(R.drawable.btn_search);
				((ViewManager) editText.getParent()).removeView(editText);
				dialog.setView(editText);
				dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Bundle bundle = new Bundle();
						String s = editText.getText().toString();
						bundle.putString("content", s);
						showActivity(aty,SearchResultActivity.class,bundle);
						
						AboutActivity.this.finish();
						dialog.dismiss();
					}
				});
				
				dialog.setNegativeButton(R.string.cancle, new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}					
				});
				dialog.show();
			}
        });
	}
	
	@Override
	public void widgetClick(View v) {
        super.widgetClick(v);
	}
}
