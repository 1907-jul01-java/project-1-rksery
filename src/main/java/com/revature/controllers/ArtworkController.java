package com.revature.controllers;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.revature.artworks.Artwork;
import com.revature.artworks.ArtworkDao;

@Path(value = "artwork")
public class ArtworkController {

	public ArtworkController() {
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

	@GET
	@Path(value = "/getapproved")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Artwork> getAllApprovedJSON() {
		List<Artwork> approved = null;
		ArtworkDao dao = new ArtworkDao();
		approved = dao.getApproved();
		return approved;
	}

	@GET
	@Path(value = "/getunapproved")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Artwork> getAllUnapprovedJSON() {
		List<Artwork> unapproved = null;
		ArtworkDao dao = new ArtworkDao();
		unapproved = dao.getUnapproved();

		return unapproved;
	}

	@GET
	@Path(value = "/getown")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Artwork> getOwn(@QueryParam("username") String un) {
		List<Artwork> artworks = null;
		ArtworkDao dao = new ArtworkDao();
		System.out.println(un);
		artworks = dao.getOwn(un);

		return artworks;
	}

	@PUT
	@Path(value = "/approve")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
	public void approve(@QueryParam("filepath") String file) {
		ArtworkDao dao = new ArtworkDao();
		dao.approveDeny(1, file);
	}

	@PUT
	@Path(value = "/deny")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
	public void deny(@QueryParam("filepath") String file) {
		ArtworkDao dao = new ArtworkDao();
		dao.approveDeny(2, file);
	}

	@POST
	@Path(value = "/insert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public void postArtwork(@FormParam("username") String username, @FormParam("title") String title,
			@FormParam("price") BigDecimal price, @FormParam("filePath") String filePath) {
		// System.out.println("Form Param Username: " + username);
		ArtworkDao artworkDao = new ArtworkDao();
		int u = artworkDao.getUserIdByUsername(username);
		// System.out.println(u);
		Artwork artwork = new Artwork(u, title, price, filePath);
		// System.out.println(artwork);
		artworkDao.insert(artwork);
		// return artwork;
	}

}