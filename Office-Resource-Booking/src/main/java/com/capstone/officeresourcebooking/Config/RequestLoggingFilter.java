package com.capstone.officeresourcebooking.Config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestLoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String clientIp = httpRequest.getRemoteAddr();
        int clientPort = httpRequest.getRemotePort();

        System.out.println("Incoming request from IP: " + clientIp + ", Port: " + clientPort);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}

