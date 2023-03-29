package alurastickers.main;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import alurastickers.parser.JsonParser;

public class App {
	
	private static final String URL =
			"https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

	private static final String MOVIE_TEMPLATE = "\nFilme %d\nTitulo: %s\nPoster: %s\nIMDB Rating: %s %s";

	private static final String STAR = "✰";

	public static void main(String[] args) throws IOException, InterruptedException {
		
		// Criação da conexão HTTP para buscar os Top 10 filmes
		URI uri = URI.create(URL);
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
			System.out.println(String.format(MOVIE_TEMPLATE
					, i
					, filme.get("title")
					, filme.get("image")
					, filme.get("imDbRating")
					, printStarByRating(filme.get("imDbRating"))));
		});
	}
	
	public static String printStarByRating(String imdbRating) {
		return STAR.repeat(Integer.parseInt(imdbRating.substring(0, 1)));
	}

}
