package us.iksg.webapp.jaxrs.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorldResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String get() {
		return "Hello, world!"; //$NON-NLS-1$
	}
}