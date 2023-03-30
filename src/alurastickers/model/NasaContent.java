package alurastickers.model;

public class NasaContent extends Content {
	
	private String date;	

	public NasaContent(String title, String urlImage, String date) {
		super(title, urlImage);
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	@Override
	public void print() {
		String template = "\nTitle: %s\nImage Url: %s\nDate: %s";
		System.out.println(String.format(template,
				this.getTitle(),
				this.getUrlImage(),
				this.getDate()));		
	}

	@Override
	public String getStickerSubtitle() {
		return this.getDate();
	}	
}
