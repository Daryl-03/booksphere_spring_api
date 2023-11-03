package dev.richryl.booksphere.config;

import com.google.firebase.auth.AuthErrorCode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class FirebaseAuthenticationFilter extends OncePerRequestFilter {

    private final FirebaseAuthenticationService firebaseAuthenticationService;
    private List<String> excludedUrls = List.of("/api/book");

    public FirebaseAuthenticationFilter(FirebaseAuthenticationService firebaseAuthenticationService) {
        this.firebaseAuthenticationService = firebaseAuthenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        System.out.println("FirebaseAuthenticationFilter.doFilterInternal");
//
//        System.out.println("request: " + request);

        String token = request.getHeader("Authorization");

//        System.err.println("token: " + token);

        if(token == null || token.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is missing");
            return;
        }

        //now we have the token, we can validate it
        try {
            firebaseAuthenticationService.validateToken(token);

            filterChain.doFilter(request, response);
        } catch (FirebaseAuthException e) {
            if (e.getAuthErrorCode() == AuthErrorCode.EXPIRED_ID_TOKEN){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is expired");
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is invalid");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, "Something went wrong");
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return excludedUrls.contains(request.getRequestURI());
    }
}
