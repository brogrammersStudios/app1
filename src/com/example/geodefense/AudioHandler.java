package com.example.geodefense;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;
import android.media.SoundPool;

	public class AudioHandler extends Activity
	{
		MediaPlayer mediaPlayer;

//////////methodsForMusic///////////
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
	//////methodForSounds///////
		public void SoundPool1(String sound)
		{
			SoundPool soundPool;
			int soundHandler = -1;
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC,0);
			
			try
			{
				AssetManager assetManager = getAssets();
				AssetFileDescriptor descriptor = assetManager.openFd(sound);
				soundHandler = soundPool.load(descriptor, 1);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		
	}
