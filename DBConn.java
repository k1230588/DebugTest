import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBConn {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rset = null;
	String url = "jdbc:postgresql://localhost:5432/nbaplayer";
	String user = "postgres";
	String password = "postgres";

	public Statement getDBC() throws ClassNotFoundException {
		if (conn == null) {
			try {
				Class.forName("org.postgresql.Driver");
				// PostgreSQL
				conn = DriverManager.getConnection(url, user, password);
				// auto commit OFF
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
			} catch (SQLException e) {

			}
		}
		return stmt;
	}

	public void closeDBC() throws SQLException {
		if (conn != null) {
			stmt.close();
			conn.close();

		}
	}

	public void upPData(List<PD> plist) throws SQLException{
		String sql = "insert into players values ";
		for (int i = 0; i < plist.size()-1 ; i++){
			sql += "('" + plist.get(i).getPid() + "','";
			sql += plist.get(i).getName() + "','";
			sql += plist.get(i).getTeam() + "','";
			sql += plist.get(i).getPos() + "','";
			sql += plist.get(i).getPyr() + "',";
			sql += plist.get(i).getFg() + ",";
			sql += plist.get(i).getFga() + ",";
			sql += plist.get(i).getFgpa() + ",";
			sql += plist.get(i).getTrip() + ",";
			sql += plist.get(i).getTripa() + ",";
			sql += plist.get(i).getTrippa() + ",";
			sql += plist.get(i).getDb() + ",";
			sql += plist.get(i).getDba() + ",";
			sql += plist.get(i).getDbpa() + ",";
			sql += plist.get(i).getEfg() + ",";
			sql += plist.get(i).getFt() + ",";
			sql += plist.get(i).getFta() + ",";
			sql += plist.get(i).getFtpa() + ",";
			sql += plist.get(i).getTrb() + ",";
			sql += plist.get(i).getAst() + ",";
			sql += plist.get(i).getStl() + ",";
			sql += plist.get(i).getBlk() + ",";
			sql += plist.get(i).getPts() + "),";
		}
		sql += "('" + plist.get(plist.size()-1).getPid() + "','";
		sql += plist.get(plist.size()-1).getName() + "','";
		sql += plist.get(plist.size()-1).getTeam() + "','";
		sql += plist.get(plist.size()-1).getPos() + "','";
		sql += plist.get(plist.size()-1).getPyr() + "',";
		sql += plist.get(plist.size()-1).getFg() + ",";
		sql += plist.get(plist.size()-1).getFga() + ",";
		sql += plist.get(plist.size()-1).getFgpa() + ",";
		sql += plist.get(plist.size()-1).getTrip() + ",";
		sql += plist.get(plist.size()-1).getTripa() + ",";
		sql += plist.get(plist.size()-1).getTrippa() + ",";
		sql += plist.get(plist.size()-1).getDb() + ",";
		sql += plist.get(plist.size()-1).getDba() + ",";
		sql += plist.get(plist.size()-1).getDbpa() + ",";
		sql += plist.get(plist.size()-1).getEfg() + ",";
		sql += plist.get(plist.size()-1).getFt() + ",";
		sql += plist.get(plist.size()-1).getFta() + ",";
		sql += plist.get(plist.size()-1).getFtpa() + ",";
		sql += plist.get(plist.size()-1).getTrb() + ",";
		sql += plist.get(plist.size()-1).getAst() + ",";
		sql += plist.get(plist.size()-1).getStl() + ",";
		sql += plist.get(plist.size()-1).getBlk() + ",";
		sql += plist.get(plist.size()-1).getPts() + ")";
		//test print
//		System.out.println(sql);
		stmt.executeUpdate(sql);
        conn.commit();
	}

	public void testd() throws SQLException {
		String sql = "insert into players values ('Alaa Abdelnaby/1990-91/POR','Alaa Abdelnaby','POR','PF','1990-91',1.3,2.7,0.474,0.0,0.0,0.0,1.3,2.7,0.474,0.474,0.6,1.0,0.568,2.1,0.3,0.1,0.3,3.1)";
		stmt.executeUpdate(sql);
        conn.commit();
	}
}
