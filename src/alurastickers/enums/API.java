package alurastickers.enums;

import alurastickers.extractor.ContentExtractor;
import alurastickers.extractor.ImdbContentExtractor;
import alurastickers.extractor.NasaContentExtractor;

public enum API {
	
	IMDB_TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", new ImdbContentExtractor()),
	NASA_APOD("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2023-03-28&end_date=2023-03-30", new NasaContentExtractor());

	private String url;
	private ContentExtractor contentExtractor;
	
	API(String url, ContentExtractor contentExtractor) {
		this.url = url;
		this.contentExtractor = contentExtractor;
	}
	
	public String getUrl() {
		return url;
	}
	
	public ContentExtractor getContentExtractor() {
		return contentExtractor;
	}
}
