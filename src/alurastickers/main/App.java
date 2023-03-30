package alurastickers.main;

import java.io.IOException;
import java.util.List;

import alurasticker.factory.StickerFactory;
import alurastickers.enums.API;
import alurastickers.extractor.ContentExtractor;
import alurastickers.menu.ApiMenu;
import alurastickers.model.Client;
import alurastickers.model.Content;

public class App {
	
	private static StickerFactory stickerFactory = new StickerFactory();
	private static ApiMenu apiMenu = new ApiMenu();

	public static void main(String[] args) {
		
		// Escolha da API a ser utilizada
		API api = apiMenu.chooseApi();

		// Criação da conexão HTTP para buscar os dados
		Client httpClient = new Client();
		String data = httpClient.findData(api.getUrl());

		// Extração dos dados da resposta
		ContentExtractor contentExtractor = api.getContentExtractor();
		List<Content> contentList = contentExtractor.extractContent(data);
		
		// Exibição dos dados e criação dos stickers
		contentList.forEach(content -> {
			content.print();
			try {
				stickerFactory.createSticker(content.getImageInputStream(), content.getTitle(), content.getStickerSubtitle());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		System.out.println("\n😀\tStickers criadas com sucesso\t😀!");
	}	
}
