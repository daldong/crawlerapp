package crawlerapp;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Dong-Hee Park
 *
 */
public class MainApp {
	
	private static String URL ="https://www.search.org/";

	public static void main(String[] args) {
		
		String KEY_WORD = JOptionPane.showInputDialog(null, "Enter a search term.");
		
		System.out.println("Search for " + KEY_WORD);
		
		System.out.println("URL :: " + URL + getParameter(KEY_WORD,1));
		
		List<String> links = new LinkedList<String>();

		try {
			// 1. Bring Document
			Document doc = Jsoup.connect(URL + getParameter(KEY_WORD,1)).get();
			
			// 2. Bring List
			//System.out.println("" + doc.toString());			
			Elements elements = doc.select("#content .container");	
		
			// 3. Bring contents from the list
			int idx = 0;
			for(Element element : elements){				
				System.out.println(++idx + " : " + element.toString());
			    System.out.println("============================================\n\n");
			
				links.add(element.absUrl("href"));
			}
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}	
	
	/**
	 * Make URL
	 * @param KEY_WORD
	 * @param PAGE
	 * @return params
	 */
	public static String getParameter(String KEY_WORD, int PAGE) {
		String params = "page/" + PAGE +""
				        + "?s=" + KEY_WORD +"";						
						
		return params;
	}

}
