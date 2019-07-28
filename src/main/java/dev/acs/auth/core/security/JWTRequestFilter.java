//package dev.acs.auth.core.security;
//
//import dev.acs.auth.module.user.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class JWTRequestFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private JWTTokenUtil jWTTokenUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//
//        final String requestTokenHeader = request.getHeader("Authorization");
//        if(requestTokenHeader == null){
//            chain.doFilter(request, response);
//            return;
//        }
//
//        final String username = jWTTokenUtil.getUsernameFromToken(requestTokenHeader);
//
//        if (username != null &&
//                SecurityContextHolder.getContext().getAuthentication() == null) {
//
//            UserDetails userDetails = this.userService.loadUserByUsername(username);
//            if (jWTTokenUtil.validateToken(requestTokenHeader, userDetails)) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null,
//                        userDetails.getAuthorities()
//                );
//                usernamePasswordAuthenticationToken
//                        .setDetails(
//                                new WebAuthenticationDetailsSource().buildDetails(request)
//                        );
//
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//        chain.doFilter(request, response);
//    }
//}