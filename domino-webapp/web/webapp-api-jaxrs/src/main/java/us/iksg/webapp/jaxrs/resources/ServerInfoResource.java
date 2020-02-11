package us.iksg.webapp.jaxrs.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import us.iksg.webapp.ServerInfo;

@Path("/serverinfo")
public class ServerInfoResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ServerInfo get() {
		return new ServerInfo();
	}
}
