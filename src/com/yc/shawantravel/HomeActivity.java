package com.yc.shawantravel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.KJLoger;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.yc.shawantravel.entity.ActivityManager;
import com.yc.shawantravel.entity.CityCode;
import com.yc.shawantravel.entity.GridMenuItem;
import com.yc.shawantravel.entity.News;
import com.yc.shawantravel.entity.Picture;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class HomeActivity extends KJActivity implements OnGestureListener {
	
	//图片自动播放的变量
	@BindView(id = R.id.viewflipper2)
    private ViewFlipper viewFlipper;
    @BindView(id = R.id.menu, click = true)
    private ImageButton menuBtn;
    @BindView(id = R.id.search, click = true)
    private ImageButton searchBtn;
    @BindView(id = R.id.location)
    private TextView cityOfLocation;
    @BindView(id = R.id.weather)
    private TextView weatherOfCity;
    @BindView(id = R.id.homebtn, click = true)
	private ImageButton homeBtn;
	@BindView(id = R.id.nearbtn, click = true)
	private ImageButton nearBtn;
	@BindView(id = R.id.collectionbtn, click = true)
	private ImageButton collectionBtn;
	@BindView(id = R.id.searchcontent)
    private EditText searchContent;
	@BindView(id = R.id.news)
	private TextView newsText;
	@BindView(id = R.id.aboutnews)
	private View newsView;
	
	//新闻显示
	private List<News> newsList;
	private News latestNews;
	
    //网格显示菜单页面的变量
	private int[] icons_menu = new int[] { R.drawable.btn_route, R.drawable.btn_scenicspot,
			R.drawable.btn_hotel, R.drawable.btn_eat, R.drawable.btn_shopping,
			R.drawable.btn_game, R.drawable.btn_about, R.drawable.btn_news};
	private String[] texts_menu = new String[]{"线路","景点","住宿","美食","购物","娱乐",
			"关于","资讯"};
	private GridView gridMenu = null;
	private LayoutInflater inflater = null;
	
	//图片显示
	private int[] showImgs = { R.drawable.show1, R.drawable.show2,R.drawable.show3,R.drawable.show4};
	private GestureDetector gestureDetector;

	//GPS定位当前城市
	private LocationClient locationClient = null;
	private static final int UPDATE_TIME = 50000;
	private String city = "广州市";
	
	//天气信息
	private Handler mHandler = new Handler();
	private Runnable r = new Runnable()
	{
		@Override
		public void run()
		{	 		
			HomeActivity.this.setCurrentWeather();
			mHandler.postDelayed(r, 1000000);
		}
	};
	
	//菜单变量
	private View leftmenuView ;
	private PopupWindow leftMenu= null;
	private Runnable popupWindow = new Runnable()
	{
		@Override
		public void run()
		{	 			
			if ( leftMenu != null )
			{
				if (leftMenu.isShowing()){
					HomeActivity.this.leftMenuClick(leftmenuView);	
				}
			}
			
			else
				mHandler.postDelayed(popupWindow, 1000);
			}
	};

	
	@Override
	public void setRootView() {
		// TODO Auto-generated method stub
		this.setContentView(R.layout.home);
	}
	
	@Override
    public void initData() {
        super.initData(); 
        
        ActivityManager.getInstace().addActivity(aty); 
          
        newsList = new LinkedList<News>();
        latestNews = new News();
        
		String url = "http://192.168.0.146:8080/shawan/news";
		HttpConfig config = new HttpConfig();// 每个KJHttp对象对应一个config
        KJLoger.debug(config.toString());
        
        config.cacheTime = 0;// 强制不使用缓存
        // （你可以自己设置缓存时间，建议区分WiFi模式和3G网模式设置不同缓存时间并动态切换）
        config.maxRetries = 10;// 出错重连次数
        KJHttp kjhttp = new KJHttp(config);
        
        kjhttp.get(url, new HttpCallBack() {
            @Override
            public void onPreStart() {
                super.onPreStart();
                KJLoger.debug("即将开始http请求");
            }
            
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                KJLoger.debug("请求成功:" + t.toString());
                try {  
                	JSONObject mJsonObject = new JSONObject(t);
                	JSONArray myJsonArray = mJsonObject.getJSONArray("news");
                	               	
                	for(int i=0 ; i < myJsonArray.length() ;i++)
                	{
                	    //获取每一个JsonObject对象
                	    JSONObject itemObject = myJsonArray.getJSONObject(i);           			 
            			int id = itemObject.getInt("newsId");
            			String title = itemObject.getString("title");
            			String date = itemObject.getString("date");
            			String content = itemObject.getString("content");
            			
            			News news = new News();
            			news.setId(id);
            			news.setTitle(title);
            			news.setDate(date);
            			news.setContent(content);
            			newsList.add(news);
            			
            			if ( i == myJsonArray.length()-1 )
            			{
            				latestNews.setId(id);
            				latestNews.setContent(content);
            				latestNews.setDate(date);
            				latestNews.setTitle(title);
            				
            				newsText.setText(title);
            			}
                	}    
        				  
        		} catch (JSONException e) {  
        			Log.i("Tag", "解析json失败");  
        			e.printStackTrace();  
        		}
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                KJLoger.debug("出现异常:" + strMsg);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                KJLoger.debug("请求完成，不管成功还是失败");
            }
        });
	}
	
	@SuppressWarnings("deprecation")
	@Override
    public void initWidget() {
        super.initWidget();

        //显示所在城市
        this.setCurrentCity();
        
        //设置显示高度
        WindowManager wm = this.getWindowManager();
        int height = wm.getDefaultDisplay().getHeight();
        viewFlipper.getLayoutParams().height = height*2/5;
        
        //图片自动播放
        gestureDetector = new GestureDetector(this);	// 声明检测手势事件
        // 添加图片源
        for (int i = 0; i < showImgs.length; i++) { 			
			ImageView iv = new ImageView(this);
			iv.setImageResource(showImgs[i]);
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			//viewFlipper.addView(iv, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			viewFlipper.addView(iv, viewFlipper.getLayoutParams());
		}
        
        // 设置自动播放功能
		viewFlipper.setAutoStart(true);			
		viewFlipper.setFlipInterval(3000);
		if(viewFlipper.isAutoStart() && !viewFlipper.isFlipping()){
			viewFlipper.startFlipping();
		}
        
		//网格显示图片    
        gridMenu = (GridView) findViewById(R.id.grid_menu); 
        PictureAdapter adapter = new PictureAdapter(texts_menu, icons_menu, this); 
        gridMenu.setAdapter(adapter); 
        gridMenu.setOnItemClickListener( new ItemClickListener());  
        
        collectionBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showActivity(aty,CollectionActivity.class);
			}     	
        });
        
        nearBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showActivity(aty,NearActivity.class);
			}     	
        });
        
        newsView.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Bundle bundle2 = new Bundle();
	        	bundle2.putString("title", latestNews.getTitle());
	        	bundle2.putString("content", latestNews.getContent());
	        	showActivity(aty,DetailNewsActivity.class,bundle2);
			}
        	
        });
    } 
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (locationClient != null && locationClient.isStarted()) 
			locationClient.stop();
		locationClient = null;
		
		mHandler.removeCallbacks(r);
	}

	@SuppressWarnings("deprecation")
	@Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
        case R.id.menu:      
        	//左菜单
            //装载选项菜单布局文件
            leftmenuView =getLayoutInflater().inflate(R.layout.leftmenu, null);  
            //创建PopupWindow对象，并在指定位置弹出用于显示菜单的窗口  
            Rect rect = new Rect();  
            this.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);  
            
            int windowsHeight = this.getWindowManager().getDefaultDisplay().getHeight();
            int windowsWidth = this.getWindowManager().getDefaultDisplay().getWidth();
            
            
            leftMenu = new PopupWindow(leftmenuView, windowsWidth/2, 
            		LayoutParams.FILL_PARENT); 
            leftMenu.setBackgroundDrawable(new BitmapDrawable());
            leftMenu.setOutsideTouchable(true);
            leftMenu.setFocusable(true);
            //设置弹出窗口的位置
            leftMenu.showAtLocation(leftmenuView,Gravity.LEFT, 0,
            		windowsHeight-rect.height());
            //leftMenu.setAnimationStyle(R.style.LeftAnimationFade);
            mHandler.post(popupWindow);
            break;
        case R.id.search:
        	Bundle bundle = new Bundle();
        	bundle.putString("content", searchContent.getText().toString());
        	searchContent.setText("");
        	
        	showActivity(aty,SearchResultActivity.class, bundle);
        	break;
        }             
	}
	
	public void leftMenuClick(View v)
	{
		View shareView = (View)v.findViewById(R.id.share);
		if (shareView != null)
		{
			shareView.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View arg0) {
					Intent shareInt=new Intent(Intent.ACTION_SEND);
					shareInt.setType("text/plain");   
					shareInt.putExtra(Intent.EXTRA_SUBJECT, "分享");   
					shareInt.putExtra(Intent.EXTRA_TEXT, "www.baidu.com");    
					shareInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
					startActivity(shareInt);
				
					leftMenu.dismiss();
					mHandler.removeCallbacks(popupWindow);
				}
			});
		}
		
		View aboutSoftwareView = (View)v.findViewById(R.id.aboutsoftware);
		if (aboutSoftwareView != null)
		{
			aboutSoftwareView.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					leftMenu.dismiss();
					showActivity(aty,AboutSoftwareActivity.class);
				}		
			});
		}
		
		View feedbackView = (View)v.findViewById(R.id.feedback);
		if (feedbackView != null)
		{
			feedbackView.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					leftMenu.dismiss();
					showActivity(aty,FeedbackActivity.class);
				}
			});
		}
		
		View settingView = (View)v.findViewById(R.id.setting);
		if (settingView != null)
		{
			settingView.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					leftMenu.dismiss();
					showActivity(aty,SettingActivity.class);
				}				
			});
		}
	}

	//获得当前所在城市
	public void setCurrentCity()
	{
		//显示所在城市
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

        		if ( location.getLocType() == BDLocation.TypeNetWorkLocation )
        		{
        			city = location.getCity();
        			if ( city.contains("地区") )
        				city = location.getDistrict();
        		
        			if ( city != null )
        			{
        				cityOfLocation.setText(city);
        				HomeActivity.this.setCurrentWeather();
        			
        				mHandler.post(r);
        			}
        		}
        	}
        });
	}
	
	//获得当前城市的温度
	public void setCurrentWeather(){
		System.out.println("mHandler======111=="+mHandler);
		
		this.city = cityOfLocation.getText().toString();
		 if (city.contains("省") || city.contains("市") || city.contains("省")) {   
	            city = city.substring(0, city.length() - 1);  
		 }
		 
		 if (city.contains("地区")) {   
	            city = city.substring(0, city.length() - 2);  
		 }
     
		String url = getString(R.string.weatherBaseSite)
				+ "?cityCode="+CityCode.getCityIdByName(city)+"&weatherType=1";
		
		HttpConfig config = new HttpConfig();// 每个KJHttp对象对应一个config
        KJLoger.debug(config.toString());
        
        config.cacheTime = 0;// 强制不使用缓存
        // （你可以自己设置缓存时间，建议区分WiFi模式和3G网模式设置不同缓存时间并动态切换）
        config.httpHeader.put("cache", "kjlibrary");// 设置http请求头信息
        config.maxRetries = 10;// 出错重连次数
        KJHttp kjhttp = new KJHttp(config);
        
        kjhttp.get(url, new HttpCallBack() {
            @Override
            public void onPreStart() {
                super.onPreStart();
                KJLoger.debug("即将开始http请求");
            }
            
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                KJLoger.debug("请求成功:" + t.toString());
                KJLoger.log("test", t.toString());
                weatherOfCity.setText("当前温度为"+ json(t.toString())+"℃"); 
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                KJLoger.debug("出现异常:" + strMsg);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                KJLoger.debug("请求完成，不管成功还是失败");
            }
        });
	}

	//解析天气的json数据  
	public String json(String json) {  
		String weather = null;   
		try {  
			JSONObject obj = new JSONObject(json);  
			JSONObject contentObject = obj.getJSONObject("weatherinfo");  	      
			weather = contentObject.getString("temp");    
				  
		} catch (JSONException e) {  
			Log.i("Tag", "解析json失败");  
			e.printStackTrace();  
		}
		return weather;  
	}
	
	//网格视图每一项的点击结果
	class ItemClickListener implements OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			switch(position){
			case 0:
				showActivity(aty, RouteActivity.class);
				break;
			case 1:
				showActivity(aty, ScenicListActivity.class);
				break;
			case 2:
				showActivity(aty, HotelListActivity.class);
				break;
			case 3:
				showActivity(aty, FoodListActivity.class);
				break;
			case 4:
				showActivity(aty, ShoppingListActivity.class);
				break;
			case 5:
				showActivity(aty, GameListActivity.class);
				break;
			case 6:
				showActivity(aty, AboutActivity.class);
				break;
			case 7:
				 showActivity(aty,InformationActivity.class);
				break;
			default:
				break;
			}
		}
		
	}
	
	////网格图片和文字的适配器
	class PictureAdapter extends BaseAdapter{ 
	    private List<Picture> pictures; 
	 
	    public PictureAdapter(String[] texts_menu, int[] icons_menu, Context context) 
	    { 
	        super(); 
	        pictures = new ArrayList<Picture>(); 
	        inflater = LayoutInflater.from(context); 
	        for (int i = 0; i < icons_menu.length; i++) 
	        { 
	            Picture picture = new Picture(texts_menu[i], icons_menu[i]); 
	            pictures.add(picture); 
	        } 
	    }

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if (null != pictures) 
	        { 
	            return pictures.size(); 
	        } 
			else
	        { 
	            return 0; 
	        } 
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return pictures.get(position); 
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			GridMenuItem gridMenuItem; 
	        if (convertView == null) 
	        { 
	            convertView = inflater.inflate(R.layout.grid_item, null); 
	            gridMenuItem = new GridMenuItem(); 
	            gridMenuItem.text = (TextView) convertView.findViewById(R.id.text_menu); 
	            gridMenuItem.icon = (ImageView) convertView.findViewById(R.id.icon_menu); 
	            convertView.setTag(gridMenuItem); 
	        } else
	        { 
	        	gridMenuItem = (GridMenuItem) convertView.getTag(); 
	        } 
	        gridMenuItem.text.setText(pictures.get(position).getText()); 
	        gridMenuItem.icon.setImageResource(pictures.get(position).getIcon()); 
	        return convertView; 
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		viewFlipper.stopFlipping();				// 点击事件后，停止自动播放
		viewFlipper.setAutoStart(false);	
		return gestureDetector.onTouchEvent(event); 		// 注册手势事件
	}
	
	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		// TODO Auto-generated method stub

		Rect rect = new Rect();  
        this.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
		int windowsHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        int height = viewFlipper.getHeight() + windowsHeight - rect.height();
		if ((e2.getY() < height) &&(e1.getY() < height))
		{
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
		
		else
		{			
			return false;
		}
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

	@SuppressWarnings("deprecation")
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub		
		Rect rect = new Rect();  
        this.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
		int windowsHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        int height = viewFlipper.getHeight() + windowsHeight - rect.height();
		if ((e.getY() < height) )
		{
			int i = 0;
			for ( i = 0; i < showImgs.length-1; i++ )
			{
				View view = viewFlipper.getChildAt(i);
				View curView = viewFlipper.getCurrentView();
				if ( view == curView )
					break;
			}
			
			if ( i == 0 )
			{
				showActivity(aty,ScenicListActivity.class);
			}
			
			else if ( i == 1 )
			{
				showActivity(aty,GameListActivity.class);
			}
			
			else if ( i == 2 )
			{
				showActivity(aty,FoodListActivity.class);
			}
			else if ( i == 3 )
			{
				showActivity(aty,ShoppingListActivity.class);
			}
		}
		return true;
	}
}
