package com.yc.shawantravel;

import java.util.LinkedList;
import java.util.List;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.widget.KJListView;
import org.kymjs.kjframe.widget.KJRefreshListener;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.yc.shawantravel.entity.ActivityManager;
import com.yc.shawantravel.entity.Collection;
import com.yc.shawantravel.entity.DBManager;

@SuppressWarnings("deprecation")
public class CollectionActivity extends KJActivity implements OnTouchListener, OnGestureListener{

    @BindView(id = R.id.btn_search, click = true)
    private ImageButton searchBtn;
    @BindView(id = R.id.back, click = true)
    private ImageButton backBtn;
    @BindView(id = R.id.whichpage)
    private TextView text;
    @BindView(id = R.id.homebtn, click = true)
	private ImageButton homeBtn;
	@BindView(id = R.id.nearbtn, click = true)
	private ImageButton nearBtn;
	@BindView(id = R.id.collectionbtn, click = true)
	private ImageButton collectionBtn;
	@BindView(id = R.id.all, click = true)
	private Button allBtn;
	@BindView(id = R.id.scenic, click = true)
	private Button scenicBtn;
	@BindView(id = R.id.hotel, click = true)
	private Button hotelBtn;
	@BindView(id = R.id.food, click = true)
	private Button foodBtn;
	@BindView(id = R.id.shopping, click = true)
	private Button shoppingBtn;
	@BindView(id = R.id.game, click = true)
	private Button gameBtn;
	
	@BindView(id = R.id.collection_listview)
	private KJListView listview;
	
	private List<Collection> collections = new LinkedList<Collection>();
	private List<Collection> datas = new LinkedList<Collection>();
	private ListViewAdapter adapter = new ListViewAdapter();
	private String kind;
	private String name;	
	
	private int whichButton = 1;
	private static final int ALL = 1;
	private static final int SCENIC = 2;
	private static final int HOTEL = 3;
	private static final int FOOD = 4;
	private static final int SHOPPING = 5;
	private static final int GAME = 6;
	
	private GestureDetector mGestureDetector;

	@Override
	public void setRootView() {
		// TODO Auto-generated method stub
		this.setContentView(R.layout.collection);
	}
	
	@Override
    public void initData() {
        super.initData(); 
        
        ActivityManager.getInstace().addActivity(aty);
        
        mGestureDetector = new GestureDetector((OnGestureListener) this);
        
        DBManager dbManager = new DBManager();
        collections = dbManager.queryCollection();
        for ( int i = 0; i < collections.size(); i++ )
        {
        	datas.add(collections.get(i));
        }
    }
	
	private void refresh() {
        //刷新方法 
    	listview.setPullLoadEnable(true);
    	listview.setPullRefreshEnable(true); 	
    }
	
	@Override
	public void initWidget() {
		// TODO Auto-generated method stub
		super.initWidget();

		text.setText("收藏");
		backBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CollectionActivity.this.finish();
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
        
        homeBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CollectionActivity.this.finish();
			}     	
        });
        
        nearBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showActivity(aty,NearActivity.class);
				CollectionActivity.this.finish();
			}     	
        });
        
        listview.setOnTouchListener(new OnTouchListener()
        {
			@Override
			public boolean onTouch(View v, MotionEvent e) {
				// TODO Auto-generated method stub
				mGestureDetector.onTouchEvent(e);
				return false;
			}     	
        });
        
        if ( datas.size() != 0 )
        {
        	listview.setOnItemClickListener(new OnItemClickListener()
        	{
        		@Override
        		public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
        			// TODO Auto-generated method stub
        			Bundle bundle = new Bundle();
        			name = datas.get(position-1).getName();
        			kind = datas.get(position-1).getKind();
				
        			bundle.putString("kind", kind);
    				bundle.putString("name", name);
        			if ( kind.equals("hotel") || kind.equals("food") || kind.endsWith("game") )
        			{
        				String phoneNum = datas.get(position-1).getPhoneNum();
        				String address = datas.get(position-1).getAddress();
        				
        				bundle.putString("phoneNum", phoneNum);
        				bundle.putString("address", address);
        			}
				
        			showActivity(aty, DetailActivity.class, bundle);
        		}      	
        	});
        
        	listview.setAdapter(adapter);
        	listview.setOnRefreshListener(new KJRefreshListener() {
            
        		@Override
        		public void onRefresh() {
        			/** 做耗时操作 */
        			refresh();
        		}

        		@Override
        		public void onLoadMore() {
        			/** 做耗时操作 */
        			refresh();
        		}
        	});
        }
        
        else
        	listview.setBackgroundResource(R.drawable.nocollection);
	}
	
	@SuppressLint("ResourceAsColor")
	@Override
	public void widgetClick(View v) {
		// TODO Auto-generated method stub
		super.widgetClick(v);

		switch(v.getId()) {
		case R.id.all:
			whichButton = ALL;
			allBtn.setBackgroundResource(R.color.zangqing);
			scenicBtn.setBackgroundResource(R.color.transparent);
			hotelBtn.setBackgroundResource(R.color.transparent);
			foodBtn.setBackgroundResource(R.color.transparent);
			shoppingBtn.setBackgroundResource(R.color.transparent);
			gameBtn.setBackgroundResource(R.color.transparent);
			
			datas.clear();
			for ( int i = 0; i < collections.size(); i++ )
	        {
	        	datas.add(collections.get(i));
	        }

			adapter.notifyDataSetChanged();
			break;
		case R.id.scenic:
			whichButton = SCENIC;
			allBtn.setBackgroundResource(R.color.transparent);
			scenicBtn.setBackgroundResource(R.color.zangqing);
			hotelBtn.setBackgroundResource(R.color.transparent);
			foodBtn.setBackgroundResource(R.color.transparent);
			shoppingBtn.setBackgroundResource(R.color.transparent);
			gameBtn.setBackgroundResource(R.color.transparent);
			
			datas.clear();
			for ( int i = 0; i < collections.size(); i++ )
			{
				if ( collections.get(i).getKind().equals("scenic") )
				{
					datas.add(collections.get(i));
				}
			}
			adapter.notifyDataSetChanged();
			break;
		case R.id.hotel:
			whichButton = HOTEL;
			allBtn.setBackgroundResource(R.color.transparent);
			scenicBtn.setBackgroundResource(R.color.transparent);
			hotelBtn.setBackgroundResource(R.color.zangqing );
			foodBtn.setBackgroundResource(R.color.transparent);
			shoppingBtn.setBackgroundResource(R.color.transparent);
			gameBtn.setBackgroundResource(R.color.transparent);
			
			datas.clear();
			for ( int i = 0; i < collections.size(); i++ )
			{
				if ( collections.get(i).getKind().equals("hotel") )
				{
					datas.add(collections.get(i));
				}
			}
			adapter.notifyDataSetChanged();
			break;
		case R.id.food:
			whichButton = FOOD;
			allBtn.setBackgroundResource(R.color.transparent);
			scenicBtn.setBackgroundResource(R.color.transparent);
			hotelBtn.setBackgroundResource(R.color.transparent);
			foodBtn.setBackgroundResource(R.color.zangqing);
			shoppingBtn.setBackgroundResource(R.color.transparent);
			gameBtn.setBackgroundResource(R.color.transparent);
			
			datas.clear();
			for ( int i = 0; i < collections.size(); i++ )
			{
				if ( collections.get(i).getKind().equals("food") )
				{
					datas.add(collections.get(i));
				}
			}
			adapter.notifyDataSetChanged();
			break;
		case R.id.shopping:
			whichButton = SHOPPING;
			allBtn.setBackgroundResource(R.color.transparent);
			scenicBtn.setBackgroundResource(R.color.transparent);
			hotelBtn.setBackgroundResource(R.color.transparent);
			foodBtn.setBackgroundResource(R.color.transparent);
			shoppingBtn.setBackgroundResource(R.color.zangqing);
			gameBtn.setBackgroundResource(R.color.transparent);
			
			datas.clear();
			for ( int i = 0; i < collections.size(); i++ )
			{
				if ( collections.get(i).getKind().equals("shopping") )
				{
					datas.add(collections.get(i));
				}
			}
			adapter.notifyDataSetChanged();
			break;
		case R.id.game:
			whichButton = GAME;
			allBtn.setBackgroundResource(R.color.transparent);
			scenicBtn.setBackgroundResource(R.color.transparent);
			hotelBtn.setBackgroundResource(R.color.transparent);
			foodBtn.setBackgroundResource(R.color.transparent);
			shoppingBtn.setBackgroundResource(R.color.transparent);
			gameBtn.setBackgroundResource(R.color.zangqing);
			
			datas.clear();
			for ( int i = 0; i < collections.size(); i++ )
			{
				if ( collections.get(i).getKind().equals("game") )
				{
					datas.add(collections.get(i));
				}
			}
			adapter.notifyDataSetChanged();
			break;
		}
	}
	
	static class ViewHolder {
        ImageView icon_item;
        TextView text_item;
    }

    class ListViewAdapter extends BaseAdapter {
    	
    	@Override
		public void notifyDataSetChanged() {
			// TODO Auto-generated method stub
			super.notifyDataSetChanged();
			
			if ( datas.size() == 0 )
				listview.setBackgroundResource(R.drawable.nocollection);
			else
				listview.setBackgroundDrawable(null);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return datas.size();
		}

        @Override
        public Object getItem(int position) {
        	return 0;
        } 

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(aty, R.layout.list_item, null);
                holder.icon_item = (ImageView) convertView
                        .findViewById(R.id.icon_item);
                holder.text_item = (TextView) convertView
                        .findViewById(R.id.text_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            
            name = datas.get(position).getName();
            holder.text_item.setText(name);
            
            kind = datas.get(position).getKind();
            if ( kind.equals("scenic") )
    		{
    			if ( name.equals("东大塘风景区") )
    			{
    				holder.icon_item.setImageResource(R.drawable.scenic1);
    			}
            
    			else if ( name.equals("鹿角湾景区") )
    			{
    				holder.icon_item.setImageResource(R.drawable.scenic2);
    			}
            
    			else if ( name.equals("温泉景区") )
    			{
    				holder.icon_item.setImageResource(R.drawable.scenic3);
    			}
            
    			else if ( name.equals("森林公园") )
    			{
    				holder.icon_item.setImageResource(R.drawable.scenic4);
    			}
            
    			else if ( name.equals("千泉湖景区") )
    			{
    				holder.icon_item.setImageResource(R.drawable.scenic5);       	
    			}
            
    			else if ( name.equals("瀚海柳浪景区") )
    			{
    				holder.icon_item.setImageResource(R.drawable.scenic6);
    			}
    		}
    		
    		else if ( kind.equals("hotel") )
    		{
    			if ( name.equals("天博生态大酒店") )
    			{
    				holder.icon_item.setImageResource(R.drawable.hotel1);
    			}
    			
    			else if ( name.equals("沙湾迎宾馆") )
    			{
    				holder.icon_item.setImageResource(R.drawable.hotel2);
    			}
    			
    			else if ( name.equals("鑫东方大酒店") )
    			{
    				holder.icon_item.setImageResource(R.drawable.hotel3);
    			}
    			
    			else if ( name.equals("德荣酒店"))
    			{
    				holder.icon_item.setImageResource(R.drawable.hotel4);
    			}
    			  
    			else if ( name.equals("民政宾馆"))
    			{
    				holder.icon_item.setImageResource(R.drawable.hotel5);
    			}
    			
    			else if ( name.equals("明盛宾馆"))
    			{
    				holder.icon_item.setImageResource(R.drawable.hotel6);
    			}
    			
    			else if ( name.equals("七星商务宾馆"))
    			{
    				holder.icon_item.setImageResource(R.drawable.hotel7);
    			}
    			
    			else if ( name.equals("温州宾馆"))
    			{
    				holder.icon_item.setImageResource(R.drawable.hotel8);
    			}
    			
    			else if ( name.equals("星光快捷宾馆"))
    			{
    				holder.icon_item.setImageResource(R.drawable.hotel9);
    			}
    			
    			else if ( name.equals("和平宾馆"))
    			{
    				holder.icon_item.setImageResource(R.drawable.hotel10);
    			}
    			
    			else if ( name.equals("佳苑宾馆"))
    			{
    				holder.icon_item.setImageResource(R.drawable.hotel11);
    			}
    			
    			else if ( name.equals("荣旺宾馆"))
    			{
    				holder.icon_item.setImageResource(R.drawable.hotel12);
    			}
    		}
    		
    		else if ( kind.equals("food") )
    		{
    			if ( name.equals("沙湾迎宾馆") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food1);
    			}
    			

    			else if ( name.equals("天博生态园") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food2);
    			}
    			
    			
    			else if ( name.equals("锦绣沙湾大盘美食城") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food3);
    			}
    			
    			else if ( name.equals("千泉湖酒楼") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food4);
    			}
    			
    			else if ( name.equals("金鲁西肥牛火锅") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food5);
    			}
    			
    			else if ( name.equals("徐师傅烤鸭") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food6);
    			}
    			
    			else if ( name.equals("金融酒家") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food7);
    			}
    			
    			else if ( name.equals("锦绣大盘食府饭馆") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food8);
    			}
    			
    			else if ( name.equals("得月楼福顺火锅") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food9);
    			}
    			
    			else if ( name.equals("金手捞火锅城") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food10);
    			}
    			
    			else if ( name.equals("森林雨火锅") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food11);
    			}
    			
    			else if ( name.equals("鑫星宴会厅") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food12);
    			}
    			
    			else if ( name.equals("沙味王") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food13);
    			}
    			
    			else if ( name.equals("桥头回民大盘鸡总店") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food14);
    			}
    			
    			else if ( name.equals("杏花村大盘鸡总店") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food15);
    			}
    			else if ( name.equals("阿腾布拉克草原牧鸡专卖") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food16);
    			}
    			else if ( name.equals("稻花香大盘鸡店") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food17);
    			}
    			
    			else if ( name.equals("风云上海滩特色架子肉") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food18);
    			}
    			
    			else if ( name.equals("祥福顺特色大盘土鸡店") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food19);
    			}

    			else if ( name.equals("沙湾金港农家特色土鸡店") )
    			{
    				holder.icon_item.setImageResource(R.drawable.food20);
    			}
    		
    		}
    		
    		else if ( kind.equals("shopping") )
    		{
    			if ( name.equals("人民路商业街") )
    			{
    				holder.icon_item.setImageResource(R.drawable.shopping);
    			}
    			
    			else if ( name.equals("鑫沙湾步行街") )
    			{
    				holder.icon_item.setImageResource(R.drawable.shopping);
    			}
    			
    			else if ( name.equals("人民路地下街") )
    			{
    				holder.icon_item.setImageResource(R.drawable.shopping);
    			}
    			
    			else if ( name.equals("星光街") )
    			{
    				holder.icon_item.setImageResource(R.drawable.shopping);
    			}
    			
    			else if ( name.equals("爱家超市") )
    			{
    				holder.icon_item.setImageResource(R.drawable.shopping);
    			}			
    			
    			else if ( name.equals("万客隆") )
    			{
    				holder.icon_item.setImageResource(R.drawable.shopping);
    			}
    		}
    		
    		else if ( kind.equals("game") )
    		{
    			if ( name.equals("飞达影城") )
    			{
    				holder.icon_item.setImageResource(R.drawable.game1);
    			}
    			
    			else if ( name.equals("银河娱乐会所") )
    			{
    				holder.icon_item.setImageResource(R.drawable.game2);
    			}
    			
    			else if ( name.equals("好声音欢唱城") )
    			{
    				holder.icon_item.setImageResource(R.drawable.game3);
    			}
    			
    			else if ( name.equals("夜猫经典音乐酒吧") )
    			{
    				holder.icon_item.setImageResource(R.drawable.game4);
    			}
    			
    			else if ( name.equals("最劲KTV") )
    			{
    				holder.icon_item.setImageResource(R.drawable.game5);
    			}

    			else if ( name.equals("歌谷欢唱城") )
    			{
    				holder.icon_item.setImageResource(R.drawable.game6);
    			}
    			
    			else if ( name.equals("凯萨酒吧") )
    			{
    				holder.icon_item.setImageResource(R.drawable.game7);
    			}
    			
    			else if ( name.equals("金柜欢唱会所") )
    			{
    				holder.icon_item.setImageResource(R.drawable.game8);
    			}
    			
    			else if ( name.equals("蓝月亮酒吧") )
    			{
    				holder.icon_item.setImageResource(R.drawable.game9);
    			}
    			
    			else if ( name.equals("金色年华娱乐会所") )
    			{
    				holder.icon_item.setImageResource(R.drawable.game10);
    			}
    		}
            
    		else if ( kind.equals("info") )
    		{
    			if ( name.equals("沙湾县鼓励投资和招商引资优惠政策（试行）") )
    			{
    				holder.icon_item.setImageResource(R.drawable.info1 );
    			}
    			
    			else if ( name.equals("真味沙湾") )
    			{
    				holder.icon_item.setImageResource(R.drawable.info2 );
    			}
    			
    			else if ( name.equals("沙湾爱情") )
    			{
    				holder.icon_item.setImageResource(R.drawable.info3 );
    			}
    		}
            
    		else
    		{
    			holder.icon_item.setImageResource(R.drawable.info1);
    		}
            
            return convertView;
        }
    }

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		if (e2.getX() - e1.getX() > 120) {			 // 从左向右滑动（左进右出）
			whichButton = whichButton - 1;
			if ( whichButton <= 0 )
				whichButton = ALL;
		} 
	
		else if (e2.getX() - e1.getX() < -120) {		 // 从右向左滑动（右进左出）
			whichButton = whichButton + 1;
			if ( whichButton >= 7 )
				whichButton = GAME;
		}
		
		switch(whichButton) {
		case ALL:
			whichButton = ALL;
			allBtn.setBackgroundResource(R.color.zangqing);
			scenicBtn.setBackgroundResource(R.color.transparent);
			hotelBtn.setBackgroundResource(R.color.transparent);
			foodBtn.setBackgroundResource(R.color.transparent);
			shoppingBtn.setBackgroundResource(R.color.transparent);
			gameBtn.setBackgroundResource(R.color.transparent);
			
			datas.clear();
			for ( int i = 0; i < collections.size(); i++ )
	        {
	        	datas.add(collections.get(i));
	        }

			adapter.notifyDataSetChanged();
			break;
		case SCENIC:
			whichButton = SCENIC;
			allBtn.setBackgroundResource(R.color.transparent);
			scenicBtn.setBackgroundResource(R.color.zangqing);
			hotelBtn.setBackgroundResource(R.color.transparent);
			foodBtn.setBackgroundResource(R.color.transparent);
			shoppingBtn.setBackgroundResource(R.color.transparent);
			gameBtn.setBackgroundResource(R.color.transparent);
			
			datas.clear();
			for ( int i = 0; i < collections.size(); i++ )
			{
				if ( collections.get(i).getKind().equals("scenic") )
				{
					datas.add(collections.get(i));
				}
			}
			adapter.notifyDataSetChanged();
			break;
		case HOTEL:
			whichButton = HOTEL;
			allBtn.setBackgroundResource(R.color.transparent);
			scenicBtn.setBackgroundResource(R.color.transparent);
			hotelBtn.setBackgroundResource(R.color.zangqing );
			foodBtn.setBackgroundResource(R.color.transparent);
			shoppingBtn.setBackgroundResource(R.color.transparent);
			gameBtn.setBackgroundResource(R.color.transparent);
			
			datas.clear();
			for ( int i = 0; i < collections.size(); i++ )
			{
				if ( collections.get(i).getKind().equals("hotel") )
				{
					datas.add(collections.get(i));
				}
			}
			adapter.notifyDataSetChanged();
			break;
		case FOOD:
			whichButton = FOOD;
			allBtn.setBackgroundResource(R.color.transparent);
			scenicBtn.setBackgroundResource(R.color.transparent);
			hotelBtn.setBackgroundResource(R.color.transparent);
			foodBtn.setBackgroundResource(R.color.zangqing);
			shoppingBtn.setBackgroundResource(R.color.transparent);
			gameBtn.setBackgroundResource(R.color.transparent);
			
			datas.clear();
			for ( int i = 0; i < collections.size(); i++ )
			{
				if ( collections.get(i).getKind().equals("food") )
				{
					datas.add(collections.get(i));
				}
			}
			adapter.notifyDataSetChanged();
			break;
		case SHOPPING:
			whichButton = SHOPPING;
			allBtn.setBackgroundResource(R.color.transparent);
			scenicBtn.setBackgroundResource(R.color.transparent);
			hotelBtn.setBackgroundResource(R.color.transparent);
			foodBtn.setBackgroundResource(R.color.transparent);
			shoppingBtn.setBackgroundResource(R.color.zangqing);
			gameBtn.setBackgroundResource(R.color.transparent);
			
			datas.clear();
			for ( int i = 0; i < collections.size(); i++ )
			{
				if ( collections.get(i).getKind().equals("shopping") )
				{
					datas.add(collections.get(i));
				}
			}
			adapter.notifyDataSetChanged();
			break;
		case GAME:
			whichButton = GAME;
			allBtn.setBackgroundResource(R.color.transparent);
			scenicBtn.setBackgroundResource(R.color.transparent);
			hotelBtn.setBackgroundResource(R.color.transparent);
			foodBtn.setBackgroundResource(R.color.transparent);
			shoppingBtn.setBackgroundResource(R.color.transparent);
			gameBtn.setBackgroundResource(R.color.zangqing);
			
			datas.clear();
			for ( int i = 0; i < collections.size(); i++ )
			{
				if ( collections.get(i).getKind().equals("game") )
				{
					datas.add(collections.get(i));
				}
			}
			adapter.notifyDataSetChanged();
			break;
		}
		return true;
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

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}
}
