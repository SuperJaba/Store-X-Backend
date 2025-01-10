package pl.storex.storex.controler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import pl.storex.storex.model.LoginDTO;
import pl.storex.storex.model.RequestAuth;
import pl.storex.storex.user.UserDTO;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth Controller", description = "Tokens endpoint")
public class AuthController {

    @Operation(summary = "CSRF token when posting data - deactivated")
    @GetMapping("/csrf")
    public String getCsrfToken(HttpServletRequest request) {
        CsrfToken csrf = (CsrfToken) request.getAttribute("_csrf");
        if (csrf != null) {
            return String.valueOf(csrf);
        }
        return "No Value";
    }

    @Operation(summary = "Refresh token - not implemented")
    @PostMapping("/refreshToken")
    public ResponseEntity<RequestAuth> getRefreshToken(@RequestBody LoginDTO loginDTO) {
        return null;
    }
}
