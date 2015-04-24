package com.yc.shawantravel.method;

import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.utils.KJLoger;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class WeatherThread extends Thread{
	
	private String url = null;
	private Handler handler = null;

	public WeatherThread(String url, Handler handler)
	{
		this.url = url;
		this.handler = handler;
		System.out.println("mHandler======222=="+this.handler);
	}
	
	public String getUrl()
	{
		return this.url;
	}
	
	public void setUrl( String url )
	{
		this.url = url;
	}
	
	public Handler getHandler()
	{
		return this.handler;
	}
	
	public void setHandler( Handler handler )
	{
		this.handler = handler;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//获得天气信息
        Looper.prepare();
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
                Message msg = new Message();
                msg.what = 1;
                msg.obj = json(t.toString());  
                //handler.obtainMessage(msg.what,msg.obj).sendToTarget();
                handler.sendMessage(msg);
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
        Looper.loop();
	}
	
	
	// 解析天气的json数据  
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
}
