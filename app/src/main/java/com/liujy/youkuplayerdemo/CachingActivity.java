package com.liujy.youkuplayerdemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.youku.service.download.DownloadInfo;
import com.youku.service.download.DownloadManager;


/**
 * 简单展示正在的视频，用户可定制自己的界面
 *
 */

public class CachingActivity extends Activity{
	//展示视频信息的ListView
	private ListView lv;
	
	//通过DownloadManager获取下载视频列表
	private DownloadManager downloadManager;
	
	//记录当前下载中的视频列表
	private ArrayList<DownloadInfo> downloadingList_show = new ArrayList<DownloadInfo>();
	
	//数据Adapter
	private CachingVideoAdapter adapter;
	
	private static final int MSG_STATE_CHANGE = 0;
	
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if(msg.what == MSG_STATE_CHANGE){
				initData();
				//更改界面现实状态
				adapter.notifyDataSetChanged();
			}
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initData();
		setContentView(R.layout.caching_activity);
		lv = (ListView)this.findViewById(R.id.list);
		
		adapter = new CachingVideoAdapter(this,handler);
		lv.setAdapter(adapter);
	}
	
	/**
	 * 获取当前正在下载的视频信息
	 */
	private void initData(){
		downloadingList_show.clear();
		
		//通过DownloadManager类的getDownloadingData()接口函数获取已经下载的视频信息
		downloadManager = DownloadManager.getInstance();
		Iterator iter = downloadManager.getDownloadingData().entrySet().iterator();
		
		while(iter.hasNext()){
			Entry entry = (Entry) iter.next();
			//视频信息实体类用DownloadInfo表示
			DownloadInfo info = (DownloadInfo) entry.getValue();
			downloadingList_show.add(info);
		}
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		finish();
		super.onDestroy();
	}
	
	
	/**
	 * 简单示例：用户展示视频信息的Adapter
	 * 第三方需要自己完善
	 *
	 */
	class CachingVideoAdapter extends BaseAdapter{
		
		private LayoutInflater inflater;
		private Handler h;
		
		public CachingVideoAdapter(Context context,Handler handler){
			inflater = LayoutInflater.from(context);
			h = handler;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return downloadingList_show.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@SuppressLint("ViewHolder")
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			final DownloadInfo info = downloadingList_show.get(position);			
						
			View view = inflater.inflate(R.layout.downloading_item, null);
			TextView tv_title = (TextView)view.findViewById(R.id.title);					//视频标题
			final Button btn_pause_continue = (Button)view.findViewById(R.id.pause);		//暂停/继续 按钮
			Button btn_cancel = (Button)view.findViewById(R.id.cancel);						//须消当前下载任务按钮	
			
			//展示视频标题
			tv_title.setText(info.title);
			
			if(info.state == DownloadInfo.STATE_DOWNLOADING){							//当前视频的下载状态：正在下载
				btn_pause_continue.setText("正在下载");					
			}else if(info.state == DownloadInfo.STATE_PAUSE){								//当前视频的下载状态：暂停中
				btn_pause_continue.setText("暂停中");
			}else if(info.state == DownloadInfo.STATE_INIT								//当前视频的下载状态：等待中
					|| info.state == DownloadInfo.STATE_EXCEPTION
					|| info.state == DownloadInfo.STATE_WAITING){
				btn_pause_continue.setText("等待中");
			}

			
			//单击暂停/继续按钮改变当前下载任务的状态
			btn_pause_continue.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(info.state == DownloadInfo.STATE_DOWNLOADING
							|| info.state == DownloadInfo.STATE_WAITING
							|| info.state == DownloadInfo.STATE_INIT
							|| info.state == DownloadInfo.STATE_EXCEPTION){
						
						//处于以上几种状态时单击进行暂停任务
						downloadManager.pauseDownload(info.taskId);

					}else if(info.state == DownloadInfo.STATE_PAUSE){						
						//处于暂停状态时单击进行继续任务
						downloadManager.startDownload(info.taskId);
						
					}
					
					h.sendEmptyMessageDelayed(0, 200);
				}
			});
			
			//取消当前下载任务
			btn_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					new AsyncTask<Void, Void, Boolean>(){

						@Override
						protected Boolean doInBackground(Void... params) {
							// TODO Auto-generated method stub
							//通过DonwloadManager类的deleteDownloading()接口函数删除所限泽的正在下载的任务
							return downloadManager.deleteDownloading(info.taskId);
						}
						
					}.execute();
					//通知更新
					h.sendEmptyMessageDelayed(0, 100);
				}
				
				
			});
			return view;
		}
		
	}


	
}
