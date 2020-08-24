import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class GetDataTest {

	public static void main(String[] args) {
		String url ="https://www.basketball-reference.com/players/a/";
		int year = 0;
		JsoupConsole.getPLink(url);
		System.out.println("done");


	}

}
