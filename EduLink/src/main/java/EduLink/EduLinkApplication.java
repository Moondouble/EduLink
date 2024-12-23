package EduLink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@SpringBootApplication
@Controller
public class EduLinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduLinkApplication.class, args);
    }

    @GetMapping("/")
    public String index(HttpSession session) {
        return "thymeleaf/index";
    }
}
