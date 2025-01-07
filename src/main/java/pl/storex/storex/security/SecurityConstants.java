package pl.storex.storex.security;

public final class SecurityConstants {
    public static final String SECRET = "421BDC21576A534D2EABAED271AA8421BDC21576A534D2EABAED2AAA";
    public static final long EXPIRATION_TIME = 36_000_000; // 10 hours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/addUser";
}
