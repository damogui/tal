package com.gongsibao.api.conroller.test;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gongsibao.api.dto.OrderDTO;

@Path("/cartype")
public class CarTypeController {

	@GET
	@Path("/list/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderDTO> list(@PathParam("userId") Long userId) {

		List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
		return orderDTOs;
	}
}
