package com.laggo.filter;

import com.laggo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

        @Autowired
    private RestTemplate template;
    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    static class MyRequestBody{
        public String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
//                    //REST call to AUTH service
                   HttpHeaders httpHeaders = new HttpHeaders();
                   httpHeaders.set("Content-Type", "application/json");
                   MyRequestBody myRequestBody = new MyRequestBody();
                   myRequestBody.setToken(authHeader);
                    HttpEntity<MyRequestBody>entity = new HttpEntity<>(myRequestBody, httpHeaders);

                ResponseEntity<String> response = template.postForEntity("http://127.0.0.1:8091/api/v1/auth/verify/", entity, String.class);
                    System.out.println(response);


                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("invalid access...!");
                    throw new RuntimeException(e.getMessage());
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
