package argmus.restaurantwebapp.security;

public class SecurityConstants {

    public static final String USER_REGISTER_URLS = "/user/**";
    public static final String PRODUCTS_GET_URLS = "/menu/product";
    public static final String SECRET = "ArgDevIO-mustran";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 30 * 60_000; //30 minutes
}
