package com.yc.shawantravel;
import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewManager;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.yc.shawantravel.entity.ActivityManager;


public class DetailNewsActivity extends KJActivity{

	@BindView(id = R.id.news_img)
	private ImageView img;
	@BindView(id = R.id.news_title)
	private TextView titleText;
	@BindView(id = R.id.news_content)
	private TextView contentText;
	@BindView(id = R.id.whichpage)
	private TextView text;
	@BindView(id = R.id.back, click = true)
	private ImageButton backBtn;
	@BindView(id = R.id.btn_search, click = true)
	private ImageButton searchBtn;
	
	@Override
	public void setRootView() {
		// TODO Auto-generated method stub
		this.setContentView(R.layout.detailnews);
	}
	
	@Override
    public void initData() {
        super.initData(); 
        
        ActivityManager.getInstace().addActivity(aty);
    }
	
	@SuppressWarnings("deprecation")
	@Override
    public void initWidget() {
        super.initWidget();
        //设置显示高度
        WindowManager wm = this.getWindowManager();
		int height = wm.getDefaultDisplay().getHeight();
        img.getLayoutParams().height = height*3/10;
        
        Bundle bundle = this.getIntent().getExtras();
        titleText.setText(bundle.getString("title"));
        contentText.setText(bundle.getString("content"));      
        text.setText("新闻详情"); 
        
        backBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				DetailNewsActivity.this.finish();
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
						
						DetailNewsActivity.this.finish();
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
