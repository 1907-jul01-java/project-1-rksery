package com.revature.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revature.artists.Artist;
import com.revature.artists.ArtistDao;

@Path("artist")
public class ArtistController {
	public ArtistController() {
		super();
	}
//	public static List<Artist> movieCache;

	// public ArtistController() {
	// movieCache = new ArrayList<>();
	// movieCache.add(new Artist(1, "Jurassic Park"));
	// movieCache.add(new Artist(2, "Avatar"));
	// movieCache.add(new Artist(3, "The Matrix"));
	// }

	// @GET
	// @Produces(MediaType.TEXT_HTML)
	// public String index() {
	// return "<h1>Welcome to the Movies API</h1>";
	// }

	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	// public List<Artist> getAllMoviesJSON() {
	// return movieCache;
	// }

	// @GET
	// @Produces(MediaType.APPLICATION_XML)
	// public List<Movie> getAllMoviesXML() {
	// return movieCache;
	// }

	// @GET
	// @Path("{id}")
	// @Produces(MediaType.APPLICATION_JSON)
	// public Movie getMovieById(@PathParam("id") int id) {
	// return movieCache.get(id - 1);
	// }

	// @GET
	// @Path("{id}")
	// @Produces(MediaType.APPLICATION_XML)
	// public Movie getMovieByIdXML(@PathParam("id") int id) {
	// return movieCache.get(id-1);
	// }

	// @GET
	// @Path("search")
	// @Produces(MediaType.APPLICATION_JSON)
	// public Movie getMovieByTitle(@QueryParam("title") String title) {
	// for (Movie m : movieCache) {
	// if (m.getTitle().equalsIgnoreCase(title))
	// return m;
	// }
	// return null;
	// }

//	@GET
//	@Path("get")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Artist getArtist() {
//		Artist artist = new Artist();
//		artist.setUsername("Test Username");
//		artist.setPw("Test Password");
//		artist.setDisplayname("Test Display Name");
//		return artist;
//	}

	@POST
	// @Path("post")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Artist postArtist(Artist artist) {
		ArtistDao artistDao = new ArtistDao();
		artistDao.insert(artist);
		return artist;
	}

}