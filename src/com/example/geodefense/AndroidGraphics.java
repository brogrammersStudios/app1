package com.example.geodefense;

import java.io.IOException;
import java.io.InputStream;
import android.content.res.AssetManager;
import android.graphics.*;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.Paint.Style;

import com.example.geodefense.Graphics.PixmapFormat;
import com.example.geodefense.Pixmap;


public class AndroidGraphics implements Graphics{
	AssetManager assets;
	Bitmap frameBuffer;
	Canvas canvas;
	Paint paint;
	Rect srcRect = new Rect();
	Rect destRect = new Rect();
	
	public AndroidGraphics(AssetManager assets, Bitmap frameBuffer){
		this.assets=assets;
		this.frameBuffer=frameBuffer;
		this.canvas = new Canvas(frameBuffer);
		this.paint = new Paint();
	}
	
	public Pixmap newPixmap(String fileName, PixmapFormat format){
		Config config = null;
		if(format == PixmapFormat.RGB_565)
			config = Config.RGB_565;
		else if(format == PixmapFormat.ARGB_4444)
			config = Config.ARGB_4444;
		else
			config = Config.ARGB_8888;
		
		Options options = new Options();
		options.inPreferredConfig = config;
		
		InputStream is = null;
		Bitmap bitmap = null;
		
		try{
			is = assets.open(fileName);
			bitmap = BitmapFactory.decodeStream(is);
			if(bitmap == null)
				throw new RuntimeException("Failed to load bitmap from asset "+fileName);
		}catch(IOException e){
			throw new RuntimeException("Failed to load bitmap from asset "+fileName);
		}finally{
			if(is!=null){
				try{
					is.close();
				}catch(IOException e){}
			}
		}
		
		if(bitmap.getConfig() == Config.RGB_565)
			format = PixmapFormat.RGB_565;
		else if(bitmap.getConfig() == Config.ARGB_4444)
			format = PixmapFormat.ARGB_4444;
		else
			format = PixmapFormat.ARGB_8888;
		
		return new AndroidPixmap(bitmap,format);
		
	}

	

	@Override
	public void drawPixel(int x, int y, int color) {
		paint.setColor(color);
		canvas.drawPoint(x,y,paint);
	}

	@Override
	public void drawLine(int x, int y, int x2, int y2, int color) {
		paint.setColor(color);
		canvas.drawLine(x, y, x2, y2, paint);
	}

	@Override
	public void drawRect(int x, int y, int width, int height, int color) {
		paint.setColor(color);
		paint.setStyle(Style.FILL);
		canvas.drawRect(x, y, x+width-1, y+width-1, paint);
	}

	@Override
	public void drawPixmap(Pixmap pixmap, int x, int y) {
		canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap , x, y, null);
	}

	@Override
	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
			int srcWidth, int srcHeight) {
		srcRect.left=srcX;
		srcRect.top=srcY;
		srcRect.right=srcX+srcWidth-1;
		srcRect.bottom=srcY+srcHeight-1;
		
		destRect.left=x;
		destRect.top=y;
		destRect.right=x+srcWidth-1;
		destRect.bottom=y+srcHeight-1;
		
		canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap , srcRect, destRect, null);
	}

	@Override
	public int getWidth() {
		return frameBuffer.getWidth();
	}

	@Override
	public int getHeight() {
		return frameBuffer.getHeight();
	}
	
	public void clear(int color){
		canvas.drawRGB((color & 0xff0000)>>16, (color & 0xff00)>>8, (color & 0xff));
	}
	

}
