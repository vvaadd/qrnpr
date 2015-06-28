package ru.qrnpr.qrweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Application home page and login.
 */
@Controller
public class MainController {

    public static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/")
    public String root(Locale locale) {
        return "redirect:/index.html";
    }

    /**
     * Home page.
     */
    @RequestMapping("/index.html")
    public String index() {
        return "index.html";
    }

    /**
     * User zone index.
     */
    @RequestMapping("/user/index.html")
    public String userIndex() {
        return "user/index.html";
    }

    /**
     * Administration zone index.
     */
    @RequestMapping("/admin/index.html")
    public String adminIndex() {
        return "admin/index.html";
    }

    /**
     * Shared zone index.
     */
    @RequestMapping("/shared/index.html")
    public String sharedIndex() {
        return "shared/index.html";
    }

    /**
     * Login form.
     */
    @RequestMapping("/login.html")
    public String login() {
        LOGGER.info("MainController login");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            LOGGER.info("User logged in as anonymous");
        } else {
            LOGGER.info("User logged in as {}", auth.getName());
            return "redirect:index.html";
        }
        return "login.html";
    }

    /**
     * Login form with error.
     */
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }

    /**
     * Simulation of an exception.
     */
    @RequestMapping("/simulateError.html")
    public void simulateError() {
        throw new RuntimeException("This is a simulated error message");
    }

    /**
     * Error page.
     */
    @RequestMapping("/error.html")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        while (throwable != null) {
            errorMessage.append(escapeTags(throwable.getMessage()));
            throwable = throwable.getCause();
        }
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error.html";
    }

    /**
     * Substitute 'less than' and 'greater than' symbols by its HTML entities.
     */
    private String escapeTags(String text) {
        if (text == null) {
            return null;
        }
        return text.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }
}
