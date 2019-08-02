package com.revature.artworks;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.ConnectionUtil;
import com.revature.Dao;

public class ArtworkDao implements Dao<Artwork> {
	String url = "jdbc:postgresql://localhost:5432/postgres";
	String username = "postgres";
	String password = "postgres";
	ConnectionUtil connectionUtil = new ConnectionUtil();

	// Connection connection = connectionUtil.getConnection();

//	public ArtworkDao(Connection connection) {
//		try {
//			connection = DriverManager.getConnection(url, username, password);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	// Generates a new artwork
	@Override
	public void insert(Artwork artwork) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {

			PreparedStatement pStatement = connection
					.prepareStatement("insert into artwork (artid, title, price, filepath) values (?,?,?,?)");
			pStatement.setInt(1, artwork.getArtid());
			pStatement.setString(2, artwork.getTitle());
			pStatement.setBigDecimal(3, artwork.getPrice());
			pStatement.setString(4, artwork.getFilepath());
			pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Artwork> getAll() {
		Artwork artwork;
		List<Artwork> artworks = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(url, username, password)) {

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from artwork");
			while (resultSet.next()) {
				artwork = new Artwork();
				artwork.setArtid(resultSet.getInt("artid"));
				artwork.setTitle(resultSet.getString("title"));
				String str = resultSet.getString("price");
				str = str.replaceAll("[$,]", "");
				// System.out.println("After replacing: " + str);
				BigDecimal bd = new BigDecimal(str);
				artwork.setPrice(bd);
				artwork.setFilepath(resultSet.getString("filepath"));
				artwork.setApproved(resultSet.getInt("approved"));
				artworks.add(artwork);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artworks;
	}

	public List<Artwork> getApproved() {
		Artwork artwork;
		List<Artwork> artworks = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(url, username, password)) {

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from artwork where approved = 1");
			while (resultSet.next()) {
				artwork = new Artwork();
				artwork.setArtid(resultSet.getInt("artid"));
				artwork.setTitle(resultSet.getString("title"));
				String str = resultSet.getString("price");
				str = str.replaceAll("[$,]", "");
				// System.out.println("After replacing: " + str);
				BigDecimal bd = new BigDecimal(str);
				artwork.setPrice(bd);
				artwork.setFilepath(resultSet.getString("filepath"));
				artwork.setApproved(resultSet.getInt("approved"));
				artworks.add(artwork);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artworks;
	}

	public List<Artwork> getOwn(String un) {
		Artwork artwork;
		List<Artwork> artworks = new ArrayList<>();
		int nu = getUserIdByUsername(un);
		System.out.println("Your User ID: " + nu);
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			PreparedStatement pStatement = connection.prepareStatement("select * from artwork where artid = ?");
			pStatement.setInt(1, nu);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				artwork = new Artwork();
				artwork.setArtid(resultSet.getInt("artid"));
				artwork.setTitle(resultSet.getString("title"));
				String str = resultSet.getString("price");
				str = str.replaceAll("[$,]", "");
				// System.out.println("After replacing: " + str);
				BigDecimal bd = new BigDecimal(str);
				artwork.setPrice(bd);
				artwork.setFilepath(resultSet.getString("filepath"));
				artwork.setApproved(resultSet.getInt("approved"));
				artworks.add(artwork);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artworks;
	}

	public List<Artwork> getUnapproved() {
		Artwork artwork;
		List<Artwork> artworks = new ArrayList<>();
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from artwork where approved = 0");
			while (resultSet.next()) {
				artwork = new Artwork();
				artwork.setArtid(resultSet.getInt("artid"));
				artwork.setTitle(resultSet.getString("title"));
				String str = resultSet.getString("price");
				str = str.replaceAll("[$,]", "");
				// System.out.println("After replacing: " + str);
				BigDecimal bd = new BigDecimal(str);
				artwork.setPrice(bd);
				artwork.setFilepath(resultSet.getString("filepath"));
				System.out.println(artwork);
				artworks.add(artwork);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artworks;
	}

//	@Override
//	public String getAll() {
//		try {
//			Connection connection = DriverManager.getConnection(url, username, password);
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery("select row_to_json(*) from artworks");
//			connectionUtil.close();
//			return resultSet.toString();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "failed";
//	}
	public void approveDeny(int approved, String file) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println(approved);
			System.out.println(file);
			PreparedStatement pStatement = connection
					.prepareStatement("update artwork set approved = ? where filepath = ?");
			pStatement.setInt(1, approved);
			pStatement.setString(2, file);
			pStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete() {

	}

	public int getUserIdByUsername(String u) {
		// System.out.println("getUserIdByUsername thinks " + username + " is your
		// username");
		int result = 99;
		String sql = "{ ? = call get_user_id(?) }";
		// username.replaceAll("[^a-zA-Z0-9]\"", "");
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			// System.out.println("Username: " + u);

			CallableStatement cStatement = connection.prepareCall(sql);
			cStatement.registerOutParameter(1, Types.INTEGER);
			cStatement.setString(2, u);
			cStatement.execute();
			result = cStatement.getInt(1);
			// System.out.println("Result: " + result);
			// System.out.println("getUserIdByUsername result is: " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}