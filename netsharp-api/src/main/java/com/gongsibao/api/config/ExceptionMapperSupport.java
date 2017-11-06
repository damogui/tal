package com.gongsibao.api.config;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author hw
 * 异常统一处理
 */
@Provider
public class ExceptionMapperSupport implements ExceptionMapper<Exception> {

	//private static final Logger LOGGER = Logger.getLogger(ExceptionMapperSupport.class);

	// private static final String CONTEXT_ATTRIBUTE =
	// WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;

	@Context
	private HttpServletRequest request;

	@Context
	private ServletContext servletContext;

	/**
	 * 异常处理
	 * 
	 * @param exception
	 * @return 异常处理后的Response对象
	 */
	public Response toResponse(Exception exception) {

		// String message = ExceptionCode.INTERNAL_SERVER_ERROR;
		// Status statusCode = Status.INTERNAL_SERVER_ERROR;
		// WebApplicationContext context = (WebApplicationContext)
		// servletContext.getAttribute(CONTEXT_ATTRIBUTE);
		// // 处理unchecked exception
		// if (exception instanceof BaseException) {
		// BaseException baseException = (BaseException) exception;
		// String code = baseException.getCode();
		// Object[] args = baseException.getValues();
		// message = context.getMessage(code, args, exception.getMessage(),
		// request.getLocale());
		//
		// } else if (exception instanceof NotFoundException) {
		// message = ExceptionCode.REQUEST_NOT_FOUND;
		// statusCode = Status.NOT_FOUND;
		// }
		// // checked exception和unchecked exception均被记录在日志里
		// LOGGER.error(message, exception);
		// return Response.ok(message,
		// MediaType.TEXT_PLAIN).status(statusCode).build();

		return Response.ok().build();
	}
}