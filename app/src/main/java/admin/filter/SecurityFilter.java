/**************************************************
 * Android Web Server
 * Based on JavaLittleWebServer (2008)
 * <p/>
 * Copyright (c) Piotr Polak 2008-2018
 **************************************************/

package admin.filter;

import java.io.IOException;

import admin.logic.AccessControl;
import ro.polak.http.configuration.ServerConfig;
import ro.polak.http.exception.ServletException;
import ro.polak.http.servlet.Filter;
import ro.polak.http.servlet.FilterChain;
import ro.polak.http.servlet.FilterConfig;
import ro.polak.http.servlet.HttpServletRequest;
import ro.polak.http.servlet.HttpServletResponse;

import static admin.Login.RELOCATE_PARAM_NAME;

public class SecurityFilter implements Filter {

    private FilterConfig filterConfig;
    private ServerConfig serverConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        serverConfig = (ServerConfig) filterConfig.getServletContext().getAttribute(ServerConfig.class.getName());
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        AccessControl ac = new AccessControl(serverConfig, request.getSession());
        if (!ac.isLogged()) {
            response.sendRedirect(filterConfig.getServletContext().getContextPath() + "/Login?" + RELOCATE_PARAM_NAME + "=" + request.getRequestURI() + (!request.getQueryString().equals("") ? "?" + request.getQueryString() : ""));
            return;
        }

        filterChain.doFilter(request, response);
    }
}
