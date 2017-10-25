package org.netsharp.api.controller.test;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.netsharp.api.dto.GoodsDTO;

@Path("/goods")
public class GoodsController {

	@GET
	@Path("/list/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<GoodsDTO> getCars(@PathParam("userId") Long userId) {

		List<GoodsDTO> orderDTOs = new ArrayList<GoodsDTO>();
		return orderDTOs;
	}
}
