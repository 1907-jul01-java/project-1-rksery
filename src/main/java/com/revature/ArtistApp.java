package com.revature;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class ArtistApp extends ResourceConfig {
	public ArtistApp() {
		packages("com.revature");
		register(MultiPartFeature.class);
	}
}