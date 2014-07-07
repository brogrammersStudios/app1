package com.example.geodefense;

import com.example.geodefense.Graphics.PixmapFormat;

public interface Pixmap {	
	public int getWidth();
	public int getHeight();
	public PixmapFormat getFormat();
	public void dispose();
}
