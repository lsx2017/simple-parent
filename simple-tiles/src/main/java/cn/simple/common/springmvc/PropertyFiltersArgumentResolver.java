package cn.simple.common.springmvc;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import cn.simple.common.utils.HibernateWebUtils;


/**
 * 用于Spring MVC在解析handler方法参数的时候正确的处理带有PropertyFiltersArgument注解的参数
 * @author 
 *
 */
public class PropertyFiltersArgumentResolver implements HandlerMethodArgumentResolver {

	public boolean supportsParameter(MethodParameter parameter) {

		return parameter.getParameterAnnotation(PropertyFiltersArgument.class) != null;
	}

	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		
		return  HibernateWebUtils.buildPropertyFilters(webRequest);
	}

}
