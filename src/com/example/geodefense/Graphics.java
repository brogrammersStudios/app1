package com.example.geodefense;

public interface Graphics {
	public static enum PixmapFormat{
		ARGB_8888,ARGB_4444,RGB_565
	}
	
	public Pixmap newPixmap(String fileName, PixmapFormat format);
	
	public void drawPixel(int x, int y, int color);
	
	public void drawLine(int x, int y, int x2, int y2, int color);
	
	public void drawRect(int x, int y, int width, int height, int color);
	
	public void drawPixmap(Pixmap pixmap, int x, int y);
	
	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight);
	
	public int getWidth();
	
	public int getHeight();
}
