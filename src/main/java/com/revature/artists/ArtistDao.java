package com.revature.artists;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.ConnectionUtil;
import com.revature.Dao;

public class ArtistDao implements Dao<Artist> {
	ConnectionUtil connectionUtil = new ConnectionUtil();
	// Connection connection = connectionUtil.getConnection();
	String url = "jdbc:postgresql://localhost:5432/postgres";
	String username = "postgres";
	String password = "postgres";

	// Generates a new artist account
	@Override
	public void insert(Artist artist) {
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement pStatement = connection
					.prepareStatement("insert into users (username, pw, displayname, permissions) values (?,?,?, 1)");
			pStatement.setString(1, artist.getUsername());
			pStatement.setString(2, artist.getPw());
			pStatement.setString(3, artist.getDisplayname());
			pStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	@Override
//	public String getAll() {
//		try {
//			Connection connection = DriverManager.getConnection(url, username, password);
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery("select row_to_json(*) from users where permissions = 1");
//			connectionUtil.close();
//			return resultSet.toString();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "failed";
//	}

	@Override
	public void update() {

	}

	@Override
	public void delete() {

	}

	@Override
	public List<Artist> getAll() {
		Artist artist;
		List<Artist> artists = new ArrayList<>();
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from users where permissions = 1");
			while (resultSet.next()) {
				artist = new Artist();
				artist.setUsername(resultSet.getString("username"));
				artist.setPw(resultSet.getString("pw"));
				artist.setDisplayname(resultSet.getString("displayname"));
				artists.add(artist);
			}
		} catch (SQLException e) {

		}
		return artists;
	}

}