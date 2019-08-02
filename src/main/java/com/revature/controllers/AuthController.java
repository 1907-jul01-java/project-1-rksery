package com.revature.controllers;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("auth")
public class AuthController {
	String url = "jdbc:postgresql://localhost:5432/postgres";
	String username = "postgres";
	String password = "postgres";

	public AuthController() {
		super();
	}

	// @POST
	// @Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	// public Response auth(String username, String password) {
	//
	// try {
	//
	// // Authenticate the user using the credentials provided
	// int au = authenticateUser(username, password);
	//
	// // Return the token on the response
	// if (au > 0) {
	// return Response.ok(username).build();
	// }
	//
	// } catch (Exception e) {
	// return Response.status(Response.Status.FORBIDDEN).build();
	// }
	// return Response.status(Response.Status.FORBIDDEN).build();
	// }
	// @POST
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	// public Response postArtist(Artist artist) {
	// try {
	// String username = artist.getUsername();
	// String pw = artist.getPw();
	// // Authenticate the user using the credentials provided
	// int au = authenticateUser(username, pw);
	//
	// // Return the token on the response
	// if (au > 0) {
	// return Response.ok(username).build();
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// return Response.status(Response.Status.FORBIDDEN).build();
	// }
	// return Response.status(Response.Status.FORBIDDEN).build();
	// }

	@POST
	// @Path("/auth")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public void postArtist(@FormParam("username") String username, @FormParam("password") String pw,
			@Context HttpServletResponse resp) throws IOException {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
				"postgres", "postgres")) {
			// username = artist.getUsername();
			// pw = artist.getPw();
			// Authenticate the user using the credentials provided
			int au = authenticateUser(username, pw);

			// Return the token on the response
			switch (au) {
			case 1:
				resp.sendRedirect("/project1/artistDashboard.html");
				break;
			case 2:
				resp.sendRedirect("/project1/curatorDashboard.html");
				break;
			default:
				resp.sendRedirect("/project1/unauthorized.html");
				break;
			}
			// if (au > 0) {
			// resp.sendRedirect("/project1/success.html");
			// } else {
			// resp.sendRedirect("/project1/unauthorized.html");
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int authenticateUser(String un, String pass) {
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			PreparedStatement pStatement = connection.prepareStatement("select pw from users where username = ?");
			pStatement.setString(1, un.toString());
			ResultSet resultSet = pStatement.executeQuery();
			resultSet.next();
			// System.out.println(pass);
			// System.out.println(resultSet.getString("pw"));
			// System.out.println(getPermissionByUsername(un));

			if (pass.equals(resultSet.getString("pw"))) {
				int perm = getPermissionByUsername(un);
				connection.close();
				return perm;

			} else {
				connection.close();
				return 0;
			}

		} catch (SQLException e) {
		}

		return 0;
	}

	private int getPermissionByUsername(String un) {
		int result = 0;
		String sql = "{ ? = call get_user_perm(?) }";
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			CallableStatement cStatement = connection.prepareCall(sql);
			cStatement.registerOutParameter(1, Types.INTEGER);
			cStatement.setString(2, un);
			cStatement.execute();
			result = cStatement.getInt(1);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
