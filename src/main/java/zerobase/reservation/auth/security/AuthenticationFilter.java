package zerobase.reservation.auth.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import zerobase.reservation.auth.type.MemberType;
import zerobase.reservation.manager.repository.ManagerRepository;
import zerobase.reservation.member.repository.MemberRepository;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final ManagerRepository managerRepository;
    private final MemberRepository memberRepository;

    @Value("${spring.jwt.prefix}")
    private String tokenPrefix;

    @Value("${spring.jwt.header}")
    private String tokenHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader(tokenHeader);

        if (authorization == null || !authorization.startsWith(tokenPrefix + " ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.substring(tokenPrefix.length()).trim(); // 토큰 접두사 이후 문자열 추출

        if (jwtUtil.isExpired(token) || !jwtUtil.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
            return;
        }

        String email = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        MemberType memberType = MemberType.valueOf(role);


        UserDetails userDetails;
        if (memberType == MemberType.PARTNER) {
            userDetails = managerRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Manager not found"));
        } else {
            userDetails = memberRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
        }


        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
