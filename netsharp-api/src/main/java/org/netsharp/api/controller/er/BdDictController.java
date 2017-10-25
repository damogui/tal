package org.netsharp.api.controller.er;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.netsharp.api.dto.OrderDTO;

@Path("/dict")
public class BdDictController {

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderDTO> getCars(@PathParam("userId") Long userId) {

		List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
		return orderDTOs;
	}
}
