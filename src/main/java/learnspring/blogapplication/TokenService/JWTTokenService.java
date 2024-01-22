package learnspring.blogapplication.TokenService;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("JWTTokenService")
public class JWTTokenService implements TokenService{
    private final Algorithm algorithm;
    private final String Key="My random secret-key for blog-api";
    private final Integer TOKEN_EXPIRY_DIFFERENCE=1000*60 *10; //10 minutes
    private  final String ISSUER="BLOG-API";

    public JWTTokenService() {
        this.algorithm = Algorithm.HMAC256(Key);
    }

    @Override
    public String createToken(String username) {

        String token = JWT.create()
                .withIssuer(ISSUER)
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+TOKEN_EXPIRY_DIFFERENCE))
                .sign(algorithm);
        return token;
    }

    @Override
    public String verifyToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }
}
