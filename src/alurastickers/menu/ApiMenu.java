package alurastickers.menu;

import java.util.List;
import java.util.Scanner;

import alurastickers.enums.API;

public class ApiMenu {
	
	private static final Scanner SC = new Scanner(System.in);
	private static final String TITLE = "######\tALURA STICKERS\t######\n";
	private static final String QUESTION = "\nSelecione uma API:";
	private static final List<API> API_LIST = List.of(API.values());
	
	public API chooseApi() {
		System.out.println(TITLE);
		String response = null;

		do {
			printApiList();
			System.out.println(QUESTION);
			response = SC.next();
		} while (null != response
				&& response.matches("[0-9]*")
				&& Integer.valueOf(response) > API_LIST.size() -1);
		
		return API_LIST.get(Integer.valueOf(response));
	}
	
	public void printApiList() {		
		API_LIST.forEach(api -> {
			int i = API_LIST.indexOf(api);
			System.out.println(String.format("[%d] %s", i, api.name()));			
		});
	}
}
