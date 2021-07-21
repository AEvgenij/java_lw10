
package by.belstu.it.andreev;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//import org.apache.log4j.Logger;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.xml.DOMConfigurator;

import by.belstu.it.andreev.UserData.Role;

public class UserDAO extends AbstractDAO<Integer, UserData> {
	
	public UserDAO() {
		super();
		
		String connectionURL = "jdbc:sqlserver://DESKTOP-T1LPDLS;databaseName=WEB_APP;user=sa;password=hello";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getDeclaredConstructor().newInstance();
			connection = DriverManager.getConnection(connectionURL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public UserData findUserByLogin(String login) throws SQLException {
		String query = "SELECT * FROM [USER] WHERE [USER].[username] = '" + login + "'";
		
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(query);
		UserData ud = new UserData();
		if (rs.next()) {
			ud.username = rs.getString("username");
			ud.password = rs.getString("password");
			int role = rs.getInt("role");
			switch (role) {
			case 0 -> ud.role = Role.ADMIN;
			case 1 -> ud.role = Role.USER;
			}
		}
		else
			return null;
		return ud;
	}

	@Override
	public List<UserData> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserData findEntityById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean Create(UserData entity) throws SQLException {
		String query = "INSERT INTO [USER]([username], [password], [role]) values(?,?,?)";
		PreparedStatement st = connection.prepareStatement(query);
		st.setString(1, entity.username);
		st.setString(2, entity.password);
		st.setInt(3, entity.role.ordinal());
		boolean result = st.execute();
		System.out.println("false");
		if (result == true)
			System.out.println("true");
		closeStatement(st);
		return result;
	}
}
