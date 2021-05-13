package br.com.swconsultoria.apispringsecurity.security;

import br.com.swconsultoria.apispringsecurity.util.Util;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${security.token}")
    private String token;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException,
            IOException {

        String autorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(Util.criaTokenCriptorgrafado(token).equals(autorization)){
            RunAsUserToken userToken =
                    new RunAsUserToken(token, null,null,null,null);
            userToken.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(userToken);
        }
        chain.doFilter(request, response);
    }
}
