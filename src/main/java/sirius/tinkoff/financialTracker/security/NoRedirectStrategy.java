package sirius.tinkoff.financialTracker.security;

import org.springframework.security.web.RedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoRedirectStrategy implements RedirectStrategy {
    @Override
    public void sendRedirect(
            final HttpServletRequest request,
            final HttpServletResponse response, final String url
    ) throws IOException {
        // no redirect
    }
}
