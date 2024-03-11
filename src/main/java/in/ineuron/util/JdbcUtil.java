package in.ineuron.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {

	private JdbcUtil() {
	
	}
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
//	public static Connection getJdbcConnection() throws SQLException {
//		HikariConfig config = new HikariConfig("src\\\\com\\\\ineuron\\\\jdbc\\\\properties\\\\db.properties");
//		HikariDataSource datasource = new HikariDataSource(config);
//		Connection connection = datasource.getConnection();
//		return connection;
//	}
//	
	public static Connection getJdbcConnection() throws SQLException, IOException {
		String loc = "S:\\Ineuron.practise\\JDBCMVCAPP\\src\\main\\java\\in\\ineuron\\properties\\db.properties";
		FileInputStream fis = new FileInputStream(loc);
		Properties props = new Properties();
		props.load(fis);
		String url = props.getProperty("url");
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
		
		
	}
}
