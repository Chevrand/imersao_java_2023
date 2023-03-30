package alurastickers.main;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import alurasticker.sticker.StickerFactory;
import alurastickers.parser.JsonParser;

public class App {
	
	private static final String TOP_MOVIES_URL =
			"https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

	private static final String MOVIE_TEMPLATE = "\nFilme %d\nTitulo: %s\nPoster: %s\nIMDB Rating: %s %s";

	private static final String STAR = "✰";
	
	private static StickerFactory stickerFactory = new StickerFactory();

	public static void main(String[] args) throws IOException, InterruptedException {
		
		// Criação da conexão HTTP para buscar os Top 10 filmes
		URI uri = URI.create(TOP_MOVIES_URL);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(uri).GET().build();		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();

		// Extração dos dados da resposta (titulo, poster, classificação)
		JsonParser jsonParser = new JsonParser();
		List<Map<String, String>> listaFilmes = jsonParser.parse(body);
		
		// Exibição dos dados
		listaFilmes.forEach(filme -> {
			int i = listaFilmes.indexOf(filme) + 1;
			String title = filme.get("title");
			String imageUrl = filme.get("image");
			String imdbRating = filme.get("imDbRating");

			System.out.println(String.format(MOVIE_TEMPLATE
					, i
					, title
					, imageUrl
					, imdbRating
					, printStarByRating(imdbRating)));
			try {
				InputStream image = new URL(imageUrl).openStream();
				stickerFactory.createSticker(image, title);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	public static String printStarByRating(String imdbRating) {
		return STAR.repeat(Integer.parseInt(imdbRating.substring(0, 1)));
	}

}
