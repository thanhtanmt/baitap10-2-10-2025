package com.example.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            response.sendRedirect("/login");
            return false;
        }

        String url = request.getRequestURI();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        boolean isUser = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"));

        if (url.startsWith("/admin") && !isAdmin) {
            response.sendRedirect("/login");
            return false;
        }
        if (url.startsWith("/user") && !isUser) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}