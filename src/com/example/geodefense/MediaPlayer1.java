package com.example.geodefense;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

	public class MediaPlayer1 extends Activity
	{
		MediaPlayer mediaPlayer;


		public void onCreate(Bundle savedInstanceState, String song)
		{
			super.onCreate(savedInstanceState); 
			setVolumeControlStream(AudioManager.STREAM_MUSIC); //set volume
			mediaPlayer = new MediaPlayer();
	
			try
			{
				AssetManager assetManager = getAssets();
				AssetFileDescriptor descriptor = assetManager.openFd(song);
				mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength() );
				descriptor.close();
				mediaPlayer.prepare();
				mediaPlayer.setLooping(true);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	
		@Override
		protected void onResume()
		{
			super.onResume();
			if(mediaPlayer != null)
				mediaPlayer.start();
		}
	
		@Override
		protected void onPause()
		{
			super.onPause();
			if(mediaPlayer != null)
				mediaPlayer.pause();
			if(isFinishing())
			{
				mediaPlayer.stop();
				mediaPlayer.release();
			}
		}
	
	}
