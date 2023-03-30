package alurastickers.extractor;

import java.util.List;

import alurastickers.model.Content;

public interface ContentExtractor {
	
	public List<Content> extractContent(String json);

}
