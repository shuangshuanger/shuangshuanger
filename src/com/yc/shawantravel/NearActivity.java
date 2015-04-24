package com.yc.shawantravel;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.widget.KJListView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.yc.shawantravel.entity.ActivityManager;

@SuppressWarnings("deprecation")
public class NearActivity extends KJActivity{

	@BindView(id = R.id.near_back, click = true)
	private ImageButton backBtn;
	@BindView(id = R.id.near_search_icon, click = true)
	private ImageButton searchBtn;
	@BindView(id = R.id.near_search_text)
	private EditText searchText;
	@BindView(id = R.id.homebtn, click = true)
	private ImageButton homeBtn;
	@BindView(id = R.id.collectionbtn, click = true)
	private ImageButton collectionBtn;
	@BindView(id = R.id.nearbtn, click = true)
	private ImageButton nearBtn;
	@BindView(id = R.id.near_listview)
	private KJListView listview;
	@BindView(id = R.id.kind_item)
	private TextView kindTXT;

	private ListViewAdapter adapter = new ListViewAdapter();
	
	@Override
	public void setRootView() {
		// TODO Auto-generated method stub
		this.setContentView(R.layout.near);
	}

	@Override
    public void initData() {
        super.initData(); 
        
        ActivityManager.getInstace().addActivity(aty);
    }

	@SuppressLint("ResourceAsColor")
	@Override
	public void initWidget()
	{
		backBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				NearActivity.this.finish();
			}
        });
		
		searchBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				String s = searchText.getText().toString();
				bundle.putString("content", s);
				showActivity(aty,SearchResultActivity.class,bundle);
			}				
        });

        searchText.setHintTextColor(R.color.white);
        homeBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				NearActivity.this.finish();
			}
        });
        
        collectionBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showActivity(aty,CollectionActivity.class);
				NearActivity.this.finish();
			}       	
        });
        
        listview.setOnItemClickListener(new OnItemClickListener()
        {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				kindTXT = (TextView) listview.getChildAt(position).findViewById(R.id.kind_item);
				String kind = kindTXT.getText().toString();
				
				Bundle bundle = new Bundle();				
				bundle.putString("kind", kind);
				showActivity(aty, PoiSearchActivity.class, bundle);
			}
        	
        });
        listview.setAdapter(adapter); 
	}
	
	static class ViewHolder {
        ImageView icon_item;
        TextView kind_item;
        TextView description_item;
        TextView count_item;       
    }

    class ListViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 5;
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
                convertView = View.inflate(aty, R.layout.near_list_item, null);
                holder.icon_item = (ImageView) convertView.findViewById(R.id.icon_item);
                holder.kind_item = (TextView) convertView.findViewById(R.id.kind_item);
                holder.description_item = (TextView)convertView.findViewById(R.id.description_item);
                holder.count_item = (TextView)convertView.findViewById(R.id.count_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            
            switch(position) {
            case 0:
            	holder.icon_item.setImageResource(R.drawable.icon_scenicspot);
            	holder.kind_item.setText(R.string.scenic);
            	holder.description_item.setText(R.string.scenicdescription);
            	holder.count_item.setText("附近有5处");
            	break;
            case 1:
            	holder.icon_item.setImageResource(R.drawable.icon_hotel);
            	holder.kind_item.setText(R.string.hotel);
            	holder.description_item.setText(R.string.hoteldescription);
            	holder.count_item.setText("附近有12处");
            	break;
            case 2:
            	holder.icon_item.setImageResource(R.drawable.icon_eat);
            	holder.kind_item.setText(R.string.food);
            	holder.description_item.setText(R.string.fooddescription);
            	holder.count_item.setText("附近有20处");
            	break;
            case 3:
            	holder.icon_item.setImageResource(R.drawable.icon_shopping);
            	holder.kind_item.setText(R.string.shopping);
            	holder.description_item.setText(R.string.shoppingdescription);
            	holder.count_item.setText("附近有6处");
            	break;
            case 4:
            	holder.icon_item.setImageResource(R.drawable.icon_game);
            	holder.kind_item.setText(R.string.game);
            	holder.description_item.setText(R.string.gamedescription);
            	holder.count_item.setText("附近有10处");
            	break;
            }
            
            return convertView;
        }
    }

}
