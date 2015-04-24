package com.yc.shawantravel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.widget.KJListView;
import org.kymjs.kjframe.widget.KJRefreshListener;

import com.yc.shawantravel.entity.ActivityManager;
import com.yc.shawantravel.entity.DBManager;
import com.yc.shawantravel.entity.Food;
import com.yc.shawantravel.entity.Game;
import com.yc.shawantravel.entity.Hotel;
import com.yc.shawantravel.entity.Scenic;
import com.yc.shawantravel.entity.SearchResult;
import com.yc.shawantravel.entity.Shopping;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressWarnings("deprecation")
public class SearchResultActivity extends KJActivity{

	@BindView(id = R.id.listview)
	private KJListView listview;
	@BindView(id = R.id.whichpage)
	private TextView text;
	@BindView(id = R.id.back, click = true)
	private ImageButton backBtn;
	@BindView(id = R.id.btn_search, click = true)
	private ImageButton searchBtn;
	
	private ArrayList<SearchResult> searchResults;
	private String content;
	private String name;
	private String kind;
	private String phoneNum;
	private String address;
	
	@Override
	public void setRootView() {
		// TODO Auto-generated method stub
		this.setContentView(R.layout.gridlistview);
	}		

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		
		ActivityManager.getInstace().addActivity(aty);
		 
		Bundle bundle = this.getIntent().getExtras();
		content = bundle.getString("content");
		
		searchResults = new ArrayList<SearchResult>();
        DBManager dbManager = new DBManager();
        
        List<Scenic> scenics = new LinkedList<Scenic>();
        List<Hotel> hotels = new LinkedList<Hotel>();
        List<Food> food = new LinkedList<Food>();
        List<Shopping> shopping = new LinkedList<Shopping>();
        List<Game> games = new LinkedList<Game>();
        
        scenics = dbManager.queryScenics();
        hotels = dbManager.queryHotels();
        food = dbManager.queryFood();
        shopping = dbManager.queryShopping();
        games = dbManager.queryGame();
        
        for ( int i = 0; i < scenics.size(); i++ )
        {
        	name = scenics.get(i).getName();
        	if ( name.contains(content) )
        	{
        		SearchResult searchResult = new SearchResult();
        		searchResult.setKind("scenic");
        		searchResult.setName(name);
        		searchResults.add(searchResult);
        	}
        }
        
        for ( int i = 0; i < hotels.size(); i++ )
        {
        	name = hotels.get(i).getName();
        	phoneNum = hotels.get(i).getPhoneNum();
        	address = hotels.get(i).getAddress();
        	if ( name.contains(content) || phoneNum.contains(content) || address.contains(content) )
        	{
        		SearchResult searchResult = new SearchResult();
        		searchResult.setKind("hotel");
        		searchResult.setName(name);
        		searchResult.setAddress(address);
        		searchResult.setPhoneNum(phoneNum);
        		searchResults.add(searchResult);
        	}
        }
        
        for ( int i = 0; i < food.size(); i++ )
        {
        	name = food.get(i).getName();
        	phoneNum = food.get(i).getPhoneNum();
        	address = food.get(i).getAddress();
        	if ( name.contains(content) || phoneNum.contains(content) || address.contains(content) )
        	{
        		SearchResult searchResult = new SearchResult();
        		searchResult.setKind("food");
        		searchResult.setName(name);
        		searchResult.setAddress(address);
        		searchResult.setPhoneNum(phoneNum);
        		searchResults.add(searchResult);
        	}
        }
        
        for ( int i = 0; i < shopping.size(); i++ )
        {
        	name = shopping.get(i).getName();
        	if ( name.contains(content) )
        	{
        		SearchResult searchResult = new SearchResult();
        		searchResult.setKind("shopping");
        		searchResult.setName(name);
        		searchResults.add(searchResult);
        	}
        }
        
        for ( int i = 0; i < games.size(); i++ )
        {
        	name = games.get(i).getName();
        	phoneNum = games.get(i).getPhoneNum();
        	address = games.get(i).getAddress();
        	if ( name.contains(content) || phoneNum.contains(content) || address.contains(content) )
        	{
        		SearchResult searchResult = new SearchResult();
        		searchResult.setKind("game");
        		searchResult.setName(name);
        		searchResult.setAddress(address);
        		searchResult.setPhoneNum(phoneNum);
        		searchResults.add(searchResult);
        	}
        }
        
        refresh();
	}
	
	private void refresh() {
        //刷新方法
    	listview.setPullLoadEnable(true);
    	listview.setPullRefreshEnable(true);    	
    }

	@Override
    public void initWidget() {
        super.initWidget();
        listview.setAdapter(new ListViewAdapter());
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
        
        text.setText("搜索结果");
        backBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SearchResultActivity.this.finish();
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

        listview.setOnItemClickListener(new OnItemClickListener()
        {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				name = searchResults.get(position-1).getName();
				kind = searchResults.get(position-1).getKind();
				
				bundle.putString("kind", kind);
				bundle.putString("name", name);
				
				if ( kind.equals("hotel") || kind.equals("food") || kind.endsWith("game") )
    			{
    				String phoneNum = searchResults.get(position-1).getPhoneNum();
    				String address = searchResults.get(position-1).getAddress();
    				
    				bundle.putString("phoneNum", phoneNum);
    				bundle.putString("address", address);
    			}
				
				showActivity(aty, DetailActivity.class, bundle);
			}      	
        });
    }

    static class ViewHolder {
        ImageView icon_item;
        TextView text_item;
    }

    class ListViewAdapter extends BaseAdapter {
    	
    	@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return searchResults.size();
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
            
            name = searchResults.get(position).getName();
            holder.text_item.setText(name);

            
            kind = searchResults.get(position).getKind();
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
            
            return convertView;
        }
    }
}