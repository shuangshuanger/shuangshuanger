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
import com.yc.shawantravel.entity.Scenic;

@SuppressWarnings("deprecation")
public class RouteActivity extends KJActivity {

	@BindView(id = R.id.route_listview)
	private KJListView listview;
	@BindView(id = R.id.whichpage)
	private TextView text;
	@BindView(id = R.id.back, click = true)
	private ImageButton backBtn;
	@BindView(id = R.id.btn_search, click = true)
	private ImageButton searchBtn;
	
	//从数据库读取内容
	private List<Scenic> scenics;
	private String name;
	private ViewHolder holder = null;
	
	private boolean isFirst;
	private int accRoute;
		
	@Override
	public void setRootView() {
		// TODO Auto-generated method stub
		this.setContentView(R.layout.route);
	}

	@Override
    public void initData() {
        ActivityManager.getInstace().addActivity(aty);
        
        scenics = new LinkedList<Scenic>();
        DBManager dbManager = new DBManager();
        scenics = dbManager.queryScenics();
        
        isFirst = true;

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
        
        text.setText("线路");
        backBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				RouteActivity.this.finish();
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
				isFirst = false;
				accRoute = position-1;
				
				ListViewAdapter adapter = new ListViewAdapter();
				adapter.getView(position-1, null, null);
				
				refresh();
				
				Bundle bundle = new Bundle();
				name = scenics.get(position-1).getName();
				
				bundle.putString("kind", "scenic");
				bundle.putString("name", name);
				
				showActivity(aty, DetailActivity.class, bundle);
			}      	
        });
    }

    static class ViewHolder {
        ImageView icon_item;
    }

    class ListViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
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
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(aty, R.layout.route_list_item, null);
                holder.icon_item = (ImageView) convertView
                        .findViewById(R.id.route_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            
//            if ( isFirst )
//            {
            	switch(position){
            	case 0:
            		holder.icon_item.setImageResource(R.drawable.btn_img01_press);
            		break;
            	case 1:
            		holder.icon_item.setImageResource(R.drawable.btn_img02_normal);
            		break;
            	case 2:
            		holder.icon_item.setImageResource(R.drawable.btn_img03_normal);
            		break;
//            	case 3:
//            		holder.icon_item.setImageResource(R.drawable.btn_route_normal);
//            		break;
//            	case 4:
//            		holder.icon_item.setImageResource(R.drawable.btn_route_normal);
//            		break;	
//            	case 5:
//            		holder.icon_item.setImageResource(R.drawable.btn_route_normal);
//            		break;
            	}
//            }
            
//            else
//            {
//            	if ( accRoute == position )
//            	{
//            		holder.icon_item.setImageResource(R.drawable.btn__press);
//            	}
//            	
//            	else
//            	{
//            		holder.icon_item.setImageResource(R.drawable.btn_route_normal);
//            	}
//            }
            
            return convertView;
        }
    }
}
