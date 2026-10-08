package org.example.student.filter;

import jakarta.servlet.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletReq , ServletResponse servletResp, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpReq=(HttpServletRequest) servletReq;
        HttpServletResponse httpResp=(HttpServletResponse) servletResp;
        filterChain.doFilter(servletReq,servletResp);
    }
}
