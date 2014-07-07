package com.example.geodefense;

import java.io.*;

import android.os.Environment;


public class ExternalStorage {
	
	public static void writeTextFile(String fileName, String text){
		
		if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			return;
		
		File externalDir = Environment.getExternalStorageDirectory();
		File textFile = new File(externalDir.getAbsolutePath()+File.separator+fileName+".txt");
		
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(textFile));
			bw.write(text);
			bw.close();
		}catch(IOException e){e.printStackTrace();}
	}
	
	public static String readTextFile(String fileDir){
		File file = new File(fileDir);
		StringBuilder text = new StringBuilder();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine())!=null){
				text.append(line);
				text.append("\n");
			}
			br.close();
		} catch (Exception e) {e.printStackTrace();}
		
		return text.toString();
	}

}
