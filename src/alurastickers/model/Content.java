package alurastickers.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class Content {
	
	private final String title;	
	private final String urlImage;

	protected Content(String title, String urlImage) {
		this.title = title;
		this.urlImage = urlImage;
	}

	public String getTitle() {
		return title;
	}

	public String getUrlImage() {
		return urlImage;
	}
	
	public InputStream getImageInputStream() {
		InputStream inputStream = null;
		try {
			inputStream = new URL(this.getUrlImage()).openStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return inputStream;
	}
	
	public abstract void print();

	public abstract String getStickerSubtitle();
}
