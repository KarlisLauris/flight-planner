package io.codelex.flightplanner.test;

import io.codelex.flightplanner.services.CommonService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/testing-api")
public class TestController {
    private final CommonService service;

    public TestController(CommonService service) {
        this.service = service;
    }

    @PostMapping("/clear")
    void clear() {
        service.clear();
    }
}
