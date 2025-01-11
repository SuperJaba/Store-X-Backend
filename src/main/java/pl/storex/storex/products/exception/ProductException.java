package pl.storex.storex.products.exception;

import io.jsonwebtoken.lang.Classes;
import io.jsonwebtoken.lang.Registry;
import io.jsonwebtoken.security.AeadAlgorithm;
import io.jsonwebtoken.security.SecureDigestAlgorithm;

import java.io.File;

public final class EXCEPTIONS {


    public static final class MY_EXC {

        String fs = File.separator;
        private static final String IMPL_CLASSNAME = "src/main/java/pl/storex/storex/products/exception/ProductExceptions.java";
        private static final Registry<Class, SebixExceptions> REGISTRY = Classes.newInstance(IMPL_CLASSNAME);

        private static final Registry<String, SebixExceptions<?, ?>> REGISTRY = Classes.newInstance(IMPL_CLASSNAME);

        public static Registry<String, AeadAlgorithm> get() {
            return REGISTRY;
        }

        private MY_EXC() {}

        public static final SebixExceptions UserNotFound = get().forKey("USER_NOT_FOUND");


    }


}
