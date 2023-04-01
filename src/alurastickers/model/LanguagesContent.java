package alurastickers.model;

public class LanguagesContent extends Content {
	
	public LanguagesContent(String title, String urlImage) {
		super(title, urlImage);
	}

	@Override
	public void print() {
		String template = "\nTitle: %s\nLogo Url: %s";
		System.out.println(String.format(template,
				this.getTitle(),
				this.getUrlImage()));
	}

	@Override
	public String getStickerSubtitle() {
		return this.getTitle();
	}
}
