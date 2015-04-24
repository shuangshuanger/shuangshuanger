package com.yc.shawantravel;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;

import com.yc.shawantravel.entity.ActivityManager;
import com.yc.shawantravel.entity.DBManager;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

@SuppressLint("WorldReadableFiles")
public class MainActivity extends KJActivity implements OnGestureListener {

	@BindView(id = R.id.viewflipper)
    private ViewFlipper viewFlipper;
    @BindView(id = R.id.enter, click = true)
    private Button enterBtn;
    private GestureDetector gestureDetector;
   // private KJActivity instructorActivity;
    private int[] imgs = { R.drawable.instructor1, R.drawable.instructor2,
			  R.drawable.instructor3};
    
	private SharedPreferences preferences;
	private DBManager dbManager;
	private boolean isFirst = false;
    
	@Override
	public void setRootView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.main);
	}
	
	@SuppressWarnings("deprecation")
	@Override
    public void initData() {
        super.initData();
        
        ActivityManager.getInstace().addActivity(aty);
        
        //读取SharedPreferences中需要的数据  
        preferences = getSharedPreferences("count",MODE_WORLD_READABLE);  
        int count = preferences.getInt("count", 0);  
        
        //判断程序与第几次运行，如果是第一次运行则跳转到引导页面  
        dbManager = new DBManager();
        if (count == 0) {  
        	dbManager.initData();
        	dbManager.addDatas();
        	
        	isFirst = true;
        }  
        
        else
        	dbManager.initData();
        
        Editor editor = preferences.edit();  
        //存入数据  
        editor.putInt("count", ++count);  
        //提交修改  
        editor.commit();  
        
        if ( !isFirst )
        {
        	showActivity(aty,HomeActivity.class);
        	this.finish();
        }
    }
	
	@SuppressWarnings("deprecation")
	@Override
    public void initWidget() {
        super.initWidget();
        
        gestureDetector = new GestureDetector(this);	// 声明检测手势事件

		for (int i = 0; i < imgs.length; i++) { 			// 添加图片源
			ImageView iv = new ImageView(this);
			iv.setImageResource(imgs[i]);
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			viewFlipper.addView(iv, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		}
		
		viewFlipper.setAutoStart(true);			// 设置自动播放功能（点击事件前自动播放）
		viewFlipper.setFlipInterval(1000);
		if(viewFlipper.isAutoStart() && !viewFlipper.isFlipping()){
			viewFlipper.startFlipping();
		}
	}
	
	@Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        
        if ( v.getId() == R.id.enter )
        {
        	this.showActivity(aty, HomeActivity.class);
        	aty.finish();
        }
         
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		viewFlipper.stopFlipping();				// 点击事件后，停止自动播放
		viewFlipper.setAutoStart(false);	
		return gestureDetector.onTouchEvent(event); 		// 注册手势事件
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		if (e2.getX() - e1.getX() > 120) {			 // 从左向右滑动（左进右出）
			Animation rInAnim = AnimationUtils.loadAnimation(aty, R.anim.push_right_in); 	// 向右滑动左侧进入的渐变效果（alpha  0.1 -> 1.0）
			Animation rOutAnim = AnimationUtils.loadAnimation(aty, R.anim.push_right_out); // 向右滑动右侧滑出的渐变效果（alpha 1.0  -> 0.1）

			viewFlipper.setInAnimation(rInAnim);
			viewFlipper.setOutAnimation(rOutAnim);
			viewFlipper.showPrevious();
			
			return true;
		} 
		
		else if (e2.getX() - e1.getX() < -120) {		 // 从右向左滑动（右进左出）
			Animation lInAnim = AnimationUtils.loadAnimation(aty, R.anim.push_left_in);		// 向左滑动左侧进入的渐变效果（alpha 0.1  -> 1.0）
			Animation lOutAnim = AnimationUtils.loadAnimation(aty, R.anim.push_left_out); 	// 向左滑动右侧滑出的渐变效果（alpha 1.0  -> 0.1）

			viewFlipper.setInAnimation(lInAnim);
			viewFlipper.setOutAnimation(lOutAnim);
			viewFlipper.showNext();
			
			return true;
		}
		return true;
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
