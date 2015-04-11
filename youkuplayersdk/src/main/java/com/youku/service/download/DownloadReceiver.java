package com.youku.service.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.baseproject.utils.Logger;
import com.youku.service.acc.AcceleraterServiceManager;

public class DownloadReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Logger.d("Accelerater_", "DownloadReceiver : from = " + intent.getStringExtra("from"));
		
		String from = intent.getStringExtra("from");
		if (from != null && from.equals(AcceleraterServiceManager.FROM_ACC)) {
			String restrictBy = intent.getStringExtra(AcceleraterServiceManager.RESTRICTBY);
			if (!TextUtils.isEmpty(restrictBy)) {
				Logger.d("Accelerater_", "DownloadReceiver : restrictBy = " + restrictBy);
//				IStaticsManager.p2pFail(restrictBy);
			}
			
			String succstartp2p = intent.getStringExtra(AcceleraterServiceManager.SUCCSTARTP2P);
			if (!TextUtils.isEmpty(succstartp2p)) {
				Logger.d("Accelerater_", "DownloadReceiver : succstartp2p = " + succstartp2p);
//				IStaticsManager.p2pSuccess(succstartp2p);
			}
			
			return;
		}
		
		String vid = intent.getStringExtra("vid");
		int state = intent.getIntExtra("state", 0);
		int source = intent.getIntExtra("source", 0);
//		IStaticsManager.p2pCacheVideoState(vid, state, source);
	}

}
