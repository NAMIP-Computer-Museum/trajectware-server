package io.swagger.api.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.ConnectionPoolDataSource;

import org.apache.log4j.Logger;
import org.mariadb.jdbc.MariaDbDataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/*
public class DBManager {
	
	public static final String DB_URL= "jdbc:mariadb://cl1-sql12.phpnet.org/durnal8";
	public static Logger logger = Logger.getLogger(DBManager.class);
	static String url;
	static String login;
	static String password;
	
	
	static {
		Properties props = new Properties();
		try (FileInputStream fis = new FileInputStream("conf.properties")) {
			try {
				props.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		url = props.getProperty("jdbc.url");
		login = props.getProperty("jdbc.login");
		password = props.getProperty("jdbc.password");
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("Class.forName réussi");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Class.forName échoué");
		}
	}
	
	private static Connection connect;
	
	private static Connection createConnection() {
		System.out.println("Entrée dans fonction createConnection");
		try {
			//connect = DriverManager.getConnection(url, login, password);
			connect = DriverManager.getConnection(DB_URL, "durnal8", "nam-ip2022!");
			System.out.println("Connection réussie");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection échoué");
		}
		return connect;
	}
	
	
	public static Connection getConnection() {
		System.out.println("Entrée dans fonction getConnection");
		logger.info("****** USER DIR: "+System.getProperty("user.dir"));
		if (connect == null) createConnection();
		return connect;
	}
}
*/

// Apache Commons DBCP
public class DBManager {
    
    private static MariaDbDataSource ds = new MariaDbDataSource();
    
    static {
        try {
			ds.setUrl("jdbc:mariadb://cl1-sql12.phpnet.org/durnal8");
			ds.setUser("durnal8");
			ds.setPassword("nam-ip2022!");
			ds.setLoginTimeout(60);
			//ds.setMaxIdle(10);
			//ds.setMaxOpenPreparedStatements(100);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
    }
    
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    
    private DBManager(){ }
}

/*
// HikariCP
public class DBManager {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl( "jdbc:mariadb://cl1-sql12.phpnet.org/durnal8" );
        config.setUsername( "durnal8" );
        config.setPassword( "nam-ip2022!" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }

    private DBManager() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
*/

/*
//C3Po
public class DBManager {

    private static ComboPooledDataSource cpds = new ComboPooledDataSource();

    static {
        try {
            cpds.setDriverClass("org.h2.Driver");
            cpds.setJdbcUrl("jdbc:h2:mem:test");
            cpds.setUser("user");
            cpds.setPassword("password");
        } catch (PropertyVetoException e) {
            // handle the exception
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }
    
    private DBManager(){}
}
*/



/*
public abstract class DBManager 
implements ConnectionPool {

  private static String url = "jdbc:mariadb://cl1-sql12.phpnet.org/durnal8";
  private static String user = "durnal8";
  private static String password = "nam-ip2022!";
  private static List<Connection> connectionPool;
  private List<Connection> usedConnections = new ArrayList<>();
  private static int INITIAL_POOL_SIZE = 10;
  static Connection connect;
  
  
  public static <BasicConnectionPool> BasicConnectionPool create() throws SQLException {
    //String url, String user, 
    //String password) 
		  

      List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
      for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
          pool.add(createConnection(url, user, password));
      }
      //return new ConnectionPool(url, user, password, pool);
      return (BasicConnectionPool) pool.get(0);
  }
  
  // standard constructors
  
  public static Connection getConnection() {
      Connection connection = connectionPool
        .remove(connectionPool.size() - 1);
      usedConnections.add(connection);
      return connection;
  }
  
  public boolean releaseConnection(Connection connection) {
      connectionPool.add(connection);
      return usedConnections.remove(connection);
  }
  
  private static Connection createConnection(
		  String url, String user, String password)
		 throws SQLException { 
    
      return DriverManager.getConnection(url, user, password);
  }
  
  public int getSize() {
      return connectionPool.size() + usedConnections.size();
  }

  // standard getters
}
*/