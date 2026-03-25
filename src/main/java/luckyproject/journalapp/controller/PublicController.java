package luckyproject.journalapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }
}