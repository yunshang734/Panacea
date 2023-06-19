package com.panacea.open.filter;

import com.panacea.common.util.ServletUtil;
import com.panacea.common.wrapper.CommonRequestWrapper;
import com.panacea.common.wrapper.CommonResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CommonFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String method = httpServletRequest.getMethod();
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(method)) {
            // 预请求直接返回OK
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            httpServletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST,GET,OPTIONS,DELETE");
            httpServletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "x-requested-with,Cache-Control,Pragma,Content-Type,Token,Content-Type");
            httpServletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
            httpServletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            httpServletResponse.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
            chain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            Long requestId = Thread.currentThread().getId();
            CommonRequestWrapper commonRequestWrapper = new CommonRequestWrapper((HttpServletRequest) servletRequest);
            CommonResponseWrapper commonResponseWrapper = new CommonResponseWrapper((HttpServletResponse) servletResponse);
            commonResponseWrapper.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            commonResponseWrapper.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST,GET,OPTIONS,DELETE");
            commonResponseWrapper.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "x-requested-with,Cache-Control,Pragma,Content-Type,Token,Content-Type");
            commonResponseWrapper.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
            commonResponseWrapper.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            commonResponseWrapper.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
            long startTimeMillions = System.currentTimeMillis();
            log.info("CommonFilter.doFilter() with requestId: {}, requestUrl: {}, method: {}, IP: {}, parameter: {}",
                    requestId,
                    commonRequestWrapper.getRequestURL().toString(),
                    commonRequestWrapper.getMethod(),
                    ServletUtil.getRealIp(commonRequestWrapper),
                    commonRequestWrapper.getRequestContent());
            chain.doFilter(commonRequestWrapper, commonResponseWrapper);
            log.info("CommonFilter.doFilter() with requestId: {}, response: {}, cost: {}ms",
                    requestId,
                    commonResponseWrapper.getResponseContent(),
                    System.currentTimeMillis() - startTimeMillions);
        }
    }

    @Override
    public void destroy() {

    }
}