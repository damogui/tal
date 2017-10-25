package org.netsharp.api.auth;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthorizationFilterFeature implements DynamicFeature {

	public void configure(ResourceInfo resourceInfo, FeatureContext context) {

//		Annotation[] arr = UserController.class.getAnnotations();
//		System.out.println(arr.length);
		
		List<Annotation> authzSpecs = new ArrayList<Annotation>();
		Annotation classAuthzSpec = resourceInfo.getResourceClass().getAnnotation(AuthAnnotation.class);
		Annotation methodAuthzSpec = resourceInfo.getResourceMethod().getAnnotation(AuthAnnotation.class);
		if (classAuthzSpec != null){

			authzSpecs.add(classAuthzSpec);
			System.out.println(resourceInfo.getResourceClass());
		}
		
		if (methodAuthzSpec != null){

			authzSpecs.add(methodAuthzSpec);
			System.out.println(resourceInfo.getResourceMethod());
		}

		if (!authzSpecs.isEmpty()) {
			
			//需要拦截的api
			context.register(AuthorizationFilter.class);
		}
	}
}