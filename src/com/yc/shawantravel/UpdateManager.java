package com.yc.shawantravel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.utils.KJLoger;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


public class UpdateManager
{
    private static final int DOWNLOAD = 1;
    private static final int DOWNLOAD_FINISH = 2;
    private String mSavePath;
    private int progress;
    private boolean cancelUpdate = false;
    private int latestVersion = 0;
    private String downloadUrl = null;
    private String name;

    private Context mContext;
    private ProgressBar mProgress;
    private Dialog mDownloadDialog;

    private Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
            case DOWNLOAD:
                mProgress.setProgress(progress);
                break;
            case DOWNLOAD_FINISH:
                installApk();
                break;
            default:
                break;
            }
        };
    };
    
    private Handler updateHandler = new Handler();
    private Runnable r = new Runnable()
    {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if ( latestVersion == 0 )
			{
				updateHandler.postDelayed(r, 1000);
			}
			
			else
			{
				if (isUpdate())
				{
					showNoticeDialog();
				} else
				{
					Toast.makeText(mContext, "您的版本已经是最新了哦！", Toast.LENGTH_LONG).show();
				}
				updateHandler.removeCallbacks(r);
			}
		}
    	
    };

    public UpdateManager(Context context)
    {
        this.mContext = context;
    }

    protected void getVersionOnService() {
		// TODO Auto-generated method stub
    	String url = "http://192.168.0.146:8080/shawan/update";
		HttpConfig config = new HttpConfig();// 每个KJHttp对象对应一个config
        KJLoger.debug(config.toString());
        
        config.cacheTime = 0;// 强制不使用缓存
        // （你可以自己设置缓存时间，建议区分WiFi模式和3G网模式设置不同缓存时间并动态切换）
        config.maxRetries = 10;// 出错重连次数
        KJHttp kjhttp = new KJHttp(config);
        
        kjhttp.get(url, new HttpCallBack() {

			@Override
			public void onSuccess(String t) {
				// TODO Auto-generated method stub
				super.onSuccess(t);				
				KJLoger.debug("请求成功:" + t.toString());
                try {  
                	JSONObject mJsonObject = new JSONObject(t);
                	JSONObject contentObject = mJsonObject.getJSONObject("updateVersion"); 
                	String s = contentObject.getString("version");
                	latestVersion = Integer.parseInt(s);
                	downloadUrl = contentObject.getString("url");
                	name = contentObject.getString("name");
        		} catch (JSONException e) {  
        			Log.i("Tag", "解析json失败");  
        			e.printStackTrace();  
        		}
			}

			@Override
            public void onPreStart() {
                super.onPreStart();
                KJLoger.debug("即将开始http请求");
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

	public void checkUpdate()
    {
		this.getVersionOnService();
		if ( latestVersion == 0 )
		{
			updateHandler.post(r);
		}
		
		else
		{
			if (isUpdate())
			{
				showNoticeDialog();
			} else
			{
				Toast.makeText(mContext, "您的版本已经是最新了哦！", Toast.LENGTH_LONG).show();
			}
		}
    }

    private boolean isUpdate()
    {
        int versionCode = getVersionCode(mContext);    
        if (latestVersion > versionCode)
        {
        	return true;
        }
        return false;
    }


    private int getVersionCode(Context context)
    {
    	int versionCode = 0;
    	try
    	{
    		versionCode = context.getPackageManager().getPackageInfo("com.yc.shawantravel", 0).versionCode;
    	} catch (NameNotFoundException e)
    	{
    		e.printStackTrace();
    	}
    	return versionCode;
    }


    private void showNoticeDialog()
    {
        AlertDialog.Builder builder = new Builder(mContext);
        builder.setTitle("软件更新");
        builder.setMessage("休闲沙湾有最新版啦，快下载哦~~");
        builder.setPositiveButton("下载", new OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                showDownloadDialog();
            }
        });

        builder.setNegativeButton("取消", new OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        Dialog noticeDialog = builder.create();
        noticeDialog.show();
    }

    private void showDownloadDialog()
    {
        AlertDialog.Builder builder = new Builder(mContext);
        builder.setTitle("休闲沙湾");
        builder.setMessage("下载中，请稍后……");
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.progress);
        builder.setView(v);
        builder.setNegativeButton("取消", new OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            	dialog.dismiss();
            	cancelUpdate = true;
            }
        });
        mDownloadDialog = builder.create();
        mDownloadDialog.show();
        downloadApk();
    }

    private void downloadApk()
    {
        new downloadApkThread().start();
    }

    private class downloadApkThread extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
                {
                    String sdpath = Environment.getExternalStorageDirectory() + "/";
                    mSavePath = sdpath + "download";
                    URL url = new URL(downloadUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();

                    File file = new File(mSavePath);
                    if (!file.exists())
                    {
                        file.mkdir();
                    }
                    File apkFile = new File(mSavePath, name);
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    byte buf[] = new byte[1024];
                    do
                    {
                        int numread = is.read(buf);
                        count += numread;
                        progress = (int) (((float) count / length) * 100);
                        mHandler.sendEmptyMessage(DOWNLOAD);
                        if (numread <= 0)
                        {
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            break;
                        }
                        fos.write(buf, 0, numread);
                    } while (!cancelUpdate);
                    fos.close();
                    is.close();
                }
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            mDownloadDialog.dismiss();
        }
    };

    private void installApk()
    {
    	File apkfile = new File(mSavePath, name);
        if (!apkfile.exists())
        {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        mContext.startActivity(i);
    }
}