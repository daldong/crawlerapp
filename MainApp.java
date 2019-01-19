package crawlerapp;

import java.io.IOException;
import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Dong-Hee Park
 * Search a term and scrape the content from the result.
 * It scrapes the content from the first page.
 * Print them as well as the URL.
 */
public class MainApp {
	
	private static String URL ="https://www.search.org/";

	public static void main(String[] args) {
		
		String KEY_WORD = JOptionPane.showInputDialog(null, "Enter a search term.");
		
		System.out.println("Search for " + KEY_WORD);
		
		System.out.println("URL :: " + URL + getParameter(KEY_WORD,1));
		
		try {
			// 1. Bring Document
			Document doc = Jsoup.connect(URL + getParameter(KEY_WORD,1)).get();
			
			// 2. Bring List
			//System.out.println("" + doc.toString());			
			Elements elements = doc.select("#content .container .row .span12 article");	
		
			// 3. Bring contents from the list
			int idx = 0;
			for(Element element : elements){				
				System.out.println(++idx + " : " + element.toString());
			    System.out.println("========================================================================");
			
			    // Bring absolute URL			
				System.out.println("URL: " + element.select("a").attr("href"));
				System.out.println("========================================================================\n\n");
								
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
