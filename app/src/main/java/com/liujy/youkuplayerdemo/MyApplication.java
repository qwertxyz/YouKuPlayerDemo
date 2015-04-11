package com.liujy.youkuplayerdemo;

import android.app.Activity;

import com.youku.player.YoukuPlayerBaseApplication;

/**
 * 接入时自定义的Application需要继承YoukuPlayerBaseApplication 
 *
 */
public class MyApplication extends YoukuPlayerBaseApplication {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	/**
	 * 通过覆写该方法，返回“正在缓存视频信息的界面”，
	 * 则在状态栏点击下载信息时可以自动跳转到所设定的界面.
	 * 用户需要定义自己的缓存界面
	 */
	@Override
	public Class<? extends Activity> getCachingActivityClass() {
		// TODO Auto-generated method stub
		return CachingActivity.class;
	}
	
	/**
	 * 通过覆写该方法，返回“已经缓存视频信息的界面”，
	 * 则在状态栏点击下载信息时可以自动跳转到所设定的界面.
	 * 用户需要定义自己的已缓存界面
	 */
	
	@Override
	public Class<? extends Activity> getCachedActivityClass() {
		// TODO Auto-generated method stub
		return CachedActivity.class;
	}

	/**
	 * 配置视频的缓存路径，格式举例： /appname/videocache/
	 * 如果返回空，则视频默认缓存路径为： /应用程序包名/videocache/ 
	 *
	 */
	@Override
	public String configDownloadPath() {
		// TODO Auto-generated method stub
		
		//return "/myapp/videocache/";			//举例
		return null;
	}

}
