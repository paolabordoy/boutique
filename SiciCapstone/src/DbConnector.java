import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DbConnector {

	private String url;
	private String uname;
	private String password;
	private Connection con;
	
	public String getUrl() {
		return url;
	}
	
	public String getUname() {
		return uname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Connection getConnection() {
		return con;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public void setUpDB() {
		this.url = "jdbc:postgresql://localhost:5432/Boutique"; //jdbc:postgresql://host:port/database
		this.uname = "postgres";
		this.password = "lunita01"; //"admin";
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			this.con = DriverManager.getConnection(url, uname, password);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("succesful connection");
	}
	
}
