package com.yc.shawantravel;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.ui.BindView;
import com.yc.shawantravel.entity.ActivityManager;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Handler;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class FeedbackActivity extends KJActivity{

	@BindView(id = R.id.feedback_back, click = true)
	private ImageButton backBtn;
	@BindView(id = R.id.submit, click = true)
	private ImageButton submitBtn;
	@BindView(id = R.id.feedback_text)
	private EditText feedbackText;
	@BindView(id = R.id.feedback_count)
	private TextView countText;
	@BindView(id = R.id.phonenumber_text)
	private EditText phoneNumText;

	private int counter = 200;
	private Handler mHandler = new Handler(); 
	private Runnable r = new Runnable()
	{
		@Override
		public void run() {
			// TODO Auto-generated method stub	
			counter = 200 - feedbackText.getText().toString().length();
			
			if ( counter >= 0 )
			{
				countText.setText("还可以输入"+counter+"字");
				mHandler.postDelayed(r, 1000);
			}
		}		
	};
	
	@Override
	public void setRootView() {
		// TODO Auto-generated method stub
		this.setContentView(R.layout.feedback);
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		
		ActivityManager.getInstace().addActivity(aty);
		
		InputFilter[] filters = {new InputFilter.LengthFilter(200)};  
		feedbackText.setFilters(filters); 
		mHandler.post(r);
	}

	@Override
	public void initWidget() {
		// TODO Auto-generated method stub
		super.initWidget();
	}

	@SuppressLint("InlinedApi")
	@Override
	public void widgetClick(View v) {
		// TODO Auto-generated method stub
		super.widgetClick(v);
		
		switch( v.getId() ) {
		case R.id.feedback_back:
			FeedbackActivity.this.finish();
			break;
		case R.id.submit:
			HttpParams params = new HttpParams();
	        try {
				params.put("content", URLEncoder.encode(feedbackText.getText().toString(), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        params.put("phoneNum", phoneNumText.getText().toString());

	        KJHttp kjh = new KJHttp();
			kjh.post("http://192.168.0.146:8080/shawan/feedback", params, new HttpCallBack() {
				@Override
	            public void onSuccess(String t) {
	                super.onSuccess(t);
	                feedbackText.setText(null);
	                phoneNumText.setText(null);
	                
	                Builder dialog = new AlertDialog.Builder(aty,AlertDialog.THEME_HOLO_LIGHT);
					dialog.setTitle("消息");
					dialog.setMessage("提交成功，感谢您的反馈！");
					dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
					dialog.show();
	            }

	            @Override
	            public void onFailure(Throwable t, int errorNo, String strMsg) {
	                super.onFailure(t, errorNo, strMsg);                
	                Builder dialog = new AlertDialog.Builder(aty,AlertDialog.THEME_HOLO_LIGHT);
					dialog.setTitle("消息");
					dialog.setMessage("提交失败，您能再试一次吗？");
					dialog.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener()
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
			break;			
		}
	}
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mHandler.removeCallbacks(r);
	}
}
