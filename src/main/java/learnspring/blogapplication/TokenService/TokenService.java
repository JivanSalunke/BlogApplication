package learnspring.blogapplication.TokenService;

public interface TokenService {
    String createToken(String username);
    String verifyToken(String token);
}
