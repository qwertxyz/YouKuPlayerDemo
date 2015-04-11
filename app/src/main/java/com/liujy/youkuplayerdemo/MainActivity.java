package com.liujy.youkuplayerdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.youku.player.ApiManager;
import com.youku.player.YoukuPlayerBaseApplication;

public class MainActivity extends Activity implements View.OnClickListener{
	Button btn_player,btn_downloaded_video,btn_downloading_video;
	EditText et;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		init();

	}
	
	private void init(){
		btn_player = (Button)this.findViewById(R.id.btn_player);						//跳转到播放界面按钮
		btn_downloaded_video = (Button)this.findViewById(R.id.btn_downloaded);		//接入已经下载视频的展示界面
		btn_downloading_video = (Button)this.findViewById(R.id.btn_downloading);		//进入正在下载视频的展示界面	
		et = (EditText)this.findViewById(R.id.vid);									//测试用，输入视频id
		
		btn_player.setOnClickListener(this);
		btn_downloaded_video.setOnClickListener(this);
		btn_downloading_video.setOnClickListener(this);
		
		
		et.setText("XOTI5NDAzMjM2");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btn_player:
			go2Player();					//跳转到播放界面
			break;
		case R.id.btn_downloaded:			
			go2DownloadedPage();			//跳转到已经下载的界面
			break;
		case R.id.btn_downloading:
			go2DownloadingPage();			//跳转到正在下载的界面
			break;
		}
	}
	
	
	/**
	 * 跳转到播放界面进行播放
	 */
	
	private void go2Player(){
		Intent i = new Intent(MainActivity.this,PlayerActivity.class);
		i.putExtra("vid", et.getText().toString().trim());
		MainActivity.this.startActivity(i);
	}
	
	/**
	 * 应用退出时调用此方法
	 */
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub	
		YoukuPlayerBaseApplication.exit();		//退出应用时请调用此方法
		
		super.onBackPressed();

	}
	
	/**
	 * 跳转到已经下载的界面
	 */
	private void go2DownloadedPage(){
		Intent i = new Intent(this,CachedActivity.class);
		startActivity(i);
		
	}
	
	/**
	 * 跳转到正在下载的界面
	 */
	private void go2DownloadingPage(){
		Intent i = new Intent(this,CachingActivity.class);
		startActivity(i);
		
	}	
	
	/**
	 * 用户登陆
	 */
	private void doLogin(){
		//ApiManager.doLogin(this);
	}
	
	/**
	 * 用户登出
	 */
	private void doLogout(){
		ApiManager.doLogout(this);
	}
	
	/**
	 * 获取当前登陆的用户
	 */
	private void getLoginState(){
		//获取登陆用户的用户名，没有登陆则为空
		String user = ApiManager.getLoginUser();									
		Toast.makeText(this, "user: " + user, Toast.LENGTH_SHORT).show();
	}


}
