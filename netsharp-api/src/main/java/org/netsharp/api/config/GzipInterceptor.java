package org.netsharp.api.config;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

public class GzipInterceptor implements WriterInterceptor {
	
	
    public void aroundWriteTo(WriterInterceptorContext context)
            throws IOException, WebApplicationException {

        MultivaluedMap<String, Object> headers = context.getHeaders();
        headers.add("Content-Encoding", "gzip");
        String ContentType = context.getMediaType().toString();
        headers.add("Content-Type",ContentType+";charset=utf-8");//解决乱码问题
        headers.add("Access-Control-Allow-Origin","*");
        final OutputStream outputStream = context.getOutputStream();
        context.setOutputStream(new GZIPOutputStream(outputStream));
        context.proceed();
        System.out.println("GZIP拦截器压缩");
    }
}