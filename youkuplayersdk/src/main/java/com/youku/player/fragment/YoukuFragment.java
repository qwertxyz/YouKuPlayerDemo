package com.youku.player.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.baseproject.image.ImageResizer;
import com.baseproject.image.ImageWorker;

public class YoukuFragment extends Fragment {
	
	private ImageResizer mImageWorker;

	public YoukuFragment() {
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		BaseActivity base = (BaseActivity) getActivity();
		mImageWorker = new ImageResizer(getActivity());//(ImageResizer) ImageWorker.getInstance(context);
	}

	public ImageResizer getImageWorker() {
		return mImageWorker;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}
}
