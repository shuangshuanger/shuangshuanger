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

import com.yc.shawantravel.entity.ActivityManager;
import com.yc.shawantravel.entity.DBManager;
import com.yc.shawantravel.entity.Hotel;

@SuppressWarnings("deprecation")
public class HotelListActivity extends KJActivity{

	@BindView(id = R.id.listview)
	private KJListView listview;
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

	//从数据库读取内容
	private List<Hotel> hotels;
	private String name;
	
	@Override
	public void setRootView() {
		// TODO Auto-generated method stub
		this.setContentView(R.layout.gridlistview);
	}

	@Override
    public void initData() {
        ActivityManager.getInstace().addActivity(aty);
        
        hotels = new LinkedList<Hotel>();
        DBManager dbManager = new DBManager();
        hotels = dbManager.queryHotels();

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
        
        text.setText("住宿");
        backBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				HotelListActivity.this.finish();
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
				String name = hotels.get(position-1).getName();
				String phoneNumber = hotels.get(position-1).getPhoneNum();
				String address = hotels.get(position-1).getAddress();
				
				bundle.putString("kind", "hotel");			
				bundle.putString("name", name);
				bundle.putString("phoneNum",phoneNumber);
				bundle.putString("address", address);
				
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
			return hotels.size();
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
            
            name = hotels.get(position).getName();
            holder.text_item.setText(name);
            
            switch(position){
            case 0:
            	holder.icon_item.setImageResource(R.drawable.hotel1);
            	break;
            case 1:
            	holder.icon_item.setImageResource(R.drawable.hotel2);
            	break;
            case 2:
            	holder.icon_item.setImageResource(R.drawable.hotel3);
            	break;
            case 3:
            	holder.icon_item.setImageResource(R.drawable.hotel4);
            	break;
            case 4:
            	holder.icon_item.setImageResource(R.drawable.hotel5);
            	break;
            case 5:
            	holder.icon_item.setImageResource(R.drawable.hotel6);
            	break;
            case 6:
            	holder.icon_item.setImageResource(R.drawable.hotel7);
            	break;
            case 7:
            	holder.icon_item.setImageResource(R.drawable.hotel8);
            	break;
            case 8:
            	holder.icon_item.setImageResource(R.drawable.hotel9);
            	break;
            case 9:
            	holder.icon_item.setImageResource(R.drawable.hotel10);
            	break;
            case 10:
            	holder.icon_item.setImageResource(R.drawable.hotel11);
            	break;
            case 11:
            	holder.icon_item.setImageResource(R.drawable.hotel12);
            	break;
            default:
            	holder.icon_item.setImageResource(R.drawable.hotel12);
            	break;
            }
            
            return convertView;
        }
    }
}
