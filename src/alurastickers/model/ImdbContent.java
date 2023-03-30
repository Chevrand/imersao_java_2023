package alurastickers.model;

public class ImdbContent extends Content {
	
	private String imdbRating;

	public ImdbContent(String title, String urlImage, String imdbRating) {
		super(title, urlImage);
		this.imdbRating = imdbRating;
	}

	public String getImdbRating() {
		return imdbRating;
	}
	
	public static String printStarByRating(String imdbRating) {
		String star = "✰";
		return star.repeat(Integer.parseInt(imdbRating.substring(0, 1)));
	}

	@Override
	public void print() {
		String template = "\nTitle: %s\nPoster Url: %s\nIMDB Rating: %s %s";
		System.out.println(String.format(template,
				this.getTitle(),
				this.getUrlImage(),
				this.getImdbRating(),
				printStarByRating(this.getImdbRating())));
	}

	@Override
	public String getStickerSubtitle() {
		Double rating = Double.valueOf(this.getImdbRating());
		if (rating <= 3.0) return "O risco é seu";
		if (rating <= 6.0) return "É oq tem pra hj";
		if (rating <= 9.0) return "Vale o ingresso";
		
		return "Megadica";
	}
}
