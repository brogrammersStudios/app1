package com.example.geodefense;
import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.geodefense.Audio;
import com.example.geodefense.Sound;
import com.example.geodefense.Music;

	public class AndroidAudio implements Audio
	{
		AssetManager assets;
		SoundPool soundPool;
		
		public AndroidAudio(Activity activity)
		{
			activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
			this.assets = activity.getAssets();
			this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC,0);     
		}
	
		@Override
		public Music newMusic(String filename) 
		{
			AssetFileDescriptor assetDescriptor=null;
			try{
				assetDescriptor = assets.openFd(filename);
			   }
				        
		    catch(IOException e){
		    	e.printStackTrace();
		    }
			return new AndroidMusic(assetDescriptor);
		}

		@Override
		public Sound newSound(String filename) {
			AssetFileDescriptor assetDescriptor=null;
				try{
				assetDescriptor = assets.openFd(filename);
				}
				catch(IOException e)
				{
				e.printStackTrace();	
				}
				int soundId = soundPool.load(assetDescriptor,0);
				return new AndroidSound(soundPool, soundId);
				}
		}
	
