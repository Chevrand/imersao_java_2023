package alurastickers.extractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import alurastickers.model.Content;
import alurastickers.model.NasaContent;
import alurastickers.parser.JsonParser;

public class NasaContentExtractor implements ContentExtractor {
	
	public List<Content> extractContent(String json) {
		// Extração dos dados
		JsonParser jsonParser = new JsonParser();
		List<Map<String, String>> dataList = jsonParser.parse(json);
		
		// Preenchimento da lista de conteúdos com os dados extraídos
		List<Content> contentList = new ArrayList<>();
		dataList.forEach(item -> {
			String title = item.get("title");
			String urlImage = item.get("url");
			String date = item.get("date");
			NasaContent content = new NasaContent(title, urlImage, date);
			
			contentList.add(content);
		});
		
		return contentList;
	}
}
