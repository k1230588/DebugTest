import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupConsole {
	String tUrl;
	static Document doc;
	static Elements eles;

	public static void getPLink(String tUrl) {
//		char aur = 'a';
//		for (int i = 0; i < 26; i++) {
//			String mUrl = tUrl + aur;
//			System.out.println(mUrl);
//			aur++;
//		}
		try {
			doc = Jsoup.parse(new URL(tUrl), 30000);
			eles = doc.select("tbody").select("th[data-stat=player]");
//			eles = doc.select("th[data-stat=player]");
			// System.out.println(eles.html());
			for (Element ele : eles) {
				String aLink = ele.select("a").attr("abs:href");
//				 test print
//				 System.out.println(aLink);
				 getPdata(aLink);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getPdata(String pUrl) {
		try {
			List<PD> plist = new ArrayList<PD>();

			doc = Jsoup.parse(new URL(pUrl), 30000);
			String pn = doc.select("h1[itemprop=name]").text();
//			System.out.println(pn);
			Element perg = doc.getElementById("per_game");
//Full Season
			eles = perg.getElementsByClass("full_table");
//			System.out.println(eles.html());
			for (Element ele : eles) {
				if (ele.select("td[data-stat=team_id]").text().equals("TOT")){
				}
				else{
					PD pda = new PD();
					pda.setName(pn);
					pda.setTeam(ele.select("td[data-stat=team_id]").text());

					if (ele.select("td[data-stat=pos]").text().isEmpty()){
						pda.setPos("N/A");
					}else{
						pda.setPos(ele.select("td[data-stat=pos]").text());
					}

					pda.setPyr(ele.select("th[data-stat=season]").text());
//FG
					pda.setFg(Float.parseFloat(ele.select("td[data-stat=fg_per_g]").text()));
					pda.setFga(Float.parseFloat(ele.select("td[data-stat=fga_per_g]").text()));
					if (ele.select("td[data-stat=fg_pct]").text().isEmpty()){
						pda.setFgpa(0);
					}else{
						pda.setFgpa(Float.parseFloat(ele.select("td[data-stat=fg_pct]").text()));
					}
//3pt
					if (ele.select("td[data-stat=fg3_per_g]").text().isEmpty()){
						pda.setTrip(0);
					}else{
						pda.setTrip(Float.parseFloat(ele.select("td[data-stat=fg3_per_g]").text()));
					}
					if (ele.select("td[data-stat=fg3a_per_g]").text().isEmpty()){
						pda.setTripa(0);
					}else{
						pda.setTripa(Float.parseFloat(ele.select("td[data-stat=fg3a_per_g]").text()));
					}
					if (ele.select("td[data-stat=fg3_pct]").text().isEmpty()){
						pda.setTrippa(0);
					}else{
						pda.setTrippa(Float.parseFloat(ele.select("td[data-stat=fg3_pct]").text()));
					}
//2pt
					if (ele.select("td[data-stat=fg2_per_g]").text().isEmpty()){
						pda.setDb(pda.getFg());
					}else{
						pda.setDb(Float.parseFloat(ele.select("td[data-stat=fg2_per_g]").text()));
					}
					if (ele.select("td[data-stat=fg2a_per_g]").text().isEmpty()){
						pda.setDba(pda.getFga());
					}else{
						pda.setDba(Float.parseFloat(ele.select("td[data-stat=fg2a_per_g]").text()));
					}
					if (ele.select("td[data-stat=fg2_pct]").text().isEmpty()){
						pda.setDbpa(pda.getFgpa());
					}else{
						pda.setDbpa(Float.parseFloat(ele.select("td[data-stat=fg2_pct]").text()));
					}

					if (ele.select("td[data-stat=efg_pct]").text().isEmpty()){
						pda.setEfg(-1);
					}else{
						pda.setEfg(Float.parseFloat(ele.select("td[data-stat=efg_pct]").text()));
					}
//FT
					pda.setFt(Float.parseFloat(ele.select("td[data-stat=ft_per_g]").text()));
					pda.setFta(Float.parseFloat(ele.select("td[data-stat=fta_per_g]").text()));
					if (ele.select("td[data-stat=ft_pct]").text().isEmpty()){
						pda.setFtpa(0);
					}else{
						pda.setFtpa(Float.parseFloat(ele.select("td[data-stat=ft_pct]").text()));
					}

					if (ele.select("td[data-stat=trb_per_g]").text().isEmpty()){
						pda.setTrb(0);
					}else{
						pda.setTrb(Float.parseFloat(ele.select("td[data-stat=trb_per_g]").text()));
					}


					if (ele.select("td[data-stat=ast_per_g]").text().isEmpty()){
						pda.setAst(0);
					}else{
						pda.setAst(Float.parseFloat(ele.select("td[data-stat=ast_per_g]").text()));
					}

					if (ele.select("td[data-stat=stl_per_g]").text().isEmpty()){
						pda.setStl(0);
					}else{
						pda.setStl(Float.parseFloat(ele.select("td[data-stat=stl_per_g]").text()));
					}

					if (ele.select("td[data-stat=blk_per_g]").text().isEmpty()){
						pda.setBlk(0);
					}else{
						pda.setBlk(Float.parseFloat(ele.select("td[data-stat=blk_per_g]").text()));
					}

					if (ele.select("td[data-stat=pts_per_g]").text().isEmpty()){
						pda.setPts(0);
					}else{
						pda.setPts(Float.parseFloat(ele.select("td[data-stat=pts_per_g]").text()));
					}

					pda.setPid(pda.getName()+"/"+pda.getPyr()+"/"+pda.getTeam());
					plist.add(pda);
//					System.out.println(plist.get(0).getName());
				}
			}
				Elements peles = perg.getElementsByClass("light_text partial_table");
//				System.out.println(peles);
//	If Trade Occur
				for (Element pele : peles) {
					PD pda = new PD();
					pda.setName(pn);
					pda.setTeam(pele.select("td[data-stat=team_id]").text());

					if (pele.select("td[data-stat=pos]").text().isEmpty()){
						pda.setPos("N/A");
					}else{
						pda.setPos(pele.select("td[data-stat=pos]").text());
					}

					pda.setPyr(pele.select("th[data-stat=season]").text());
//FG
					pda.setFg(Float.parseFloat(pele.select("td[data-stat=fg_per_g]").text()));
					pda.setFga(Float.parseFloat(pele.select("td[data-stat=fga_per_g]").text()));
					if (pele.select("td[data-stat=fg_pct]").text().isEmpty()){
						pda.setFgpa(0);
					}else{
						pda.setFgpa(Float.parseFloat(pele.select("td[data-stat=fg_pct]").text()));
					}
//3pt
					if (pele.select("td[data-stat=fg3_per_g]").text().isEmpty()){
						pda.setTrip(0);
					}else{
						pda.setTrip(Float.parseFloat(pele.select("td[data-stat=fg3_per_g]").text()));
					}
					if (pele.select("td[data-stat=fg3a_per_g]").text().isEmpty()){
						pda.setTripa(0);
					}else{
						pda.setTripa(Float.parseFloat(pele.select("td[data-stat=fg3a_per_g]").text()));
					}
					if (pele.select("td[data-stat=fg3_pct]").text().isEmpty()){
						pda.setTrippa(0);
					}else{
						pda.setTrippa(Float.parseFloat(pele.select("td[data-stat=fg3_pct]").text()));
					}
//2pt
					if (pele.select("td[data-stat=fg2_per_g]").text().isEmpty()){
						pda.setDb(pda.getFg());
					}else{
						pda.setDb(Float.parseFloat(pele.select("td[data-stat=fg2_per_g]").text()));
					}
					if (pele.select("td[data-stat=fg2a_per_g]").text().isEmpty()){
						pda.setDba(pda.getFga());
					}else{
						pda.setDba(Float.parseFloat(pele.select("td[data-stat=fg2a_per_g]").text()));
					}
					if (pele.select("td[data-stat=fg2_pct]").text().isEmpty()){
						pda.setDbpa(pda.getFgpa());
					}else{
						pda.setDbpa(Float.parseFloat(pele.select("td[data-stat=fg2_pct]").text()));
					}

					if (pele.select("td[data-stat=efg_pct]").text().isEmpty()){
						pda.setEfg(-1);
					}else{
						pda.setEfg(Float.parseFloat(pele.select("td[data-stat=efg_pct]").text()));
					}
//FT
					pda.setFt(Float.parseFloat(pele.select("td[data-stat=ft_per_g]").text()));
					pda.setFta(Float.parseFloat(pele.select("td[data-stat=fta_per_g]").text()));
					if (pele.select("td[data-stat=ft_pct]").text().isEmpty()){
						pda.setFtpa(0);
					}else{
						pda.setFtpa(Float.parseFloat(pele.select("td[data-stat=ft_pct]").text()));
					}

					if (pele.select("td[data-stat=trb_per_g]").text().isEmpty()){
						pda.setTrb(0);
					}else{
						pda.setTrb(Float.parseFloat(pele.select("td[data-stat=trb_per_g]").text()));
					}


					if (pele.select("td[data-stat=ast_per_g]").text().isEmpty()){
						pda.setAst(0);
					}else{
						pda.setAst(Float.parseFloat(pele.select("td[data-stat=ast_per_g]").text()));
					}

					if (pele.select("td[data-stat=stl_per_g]").text().isEmpty()){
						pda.setStl(0);
					}else{
						pda.setStl(Float.parseFloat(pele.select("td[data-stat=stl_per_g]").text()));
					}

					if (pele.select("td[data-stat=blk_per_g]").text().isEmpty()){
						pda.setBlk(0);
					}else{
						pda.setBlk(Float.parseFloat(pele.select("td[data-stat=blk_per_g]").text()));
					}

					if (pele.select("td[data-stat=pts_per_g]").text().isEmpty()){
						pda.setPts(0);
					}else{
						pda.setPts(Float.parseFloat(pele.select("td[data-stat=pts_per_g]").text()));
					}

					pda.setPid(pda.getName()+"/"+pda.getPyr()+"/"+pda.getTeam());
					plist.add(pda);
				}
				DBConn dbc = new DBConn();
				dbc.getDBC();
				dbc.upPData(plist);



		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

//			dbc.upPData(pname, pyr, plist);

	}
}
