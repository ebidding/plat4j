package org.net.plat4j.sr.core.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.RequestMethod;

@Retention(RUNTIME)
@Target(METHOD)
public @interface EbsRequestMethod {
	public RequestMethod[] value() default {RequestMethod.GET,RequestMethod.POST};
}
