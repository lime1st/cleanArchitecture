package lime1st.example.clean.user.adapter.in.web.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class SampleController {

    @GetMapping("/")
    public String sampleMessageToLoki(HttpServletResponse response) throws IOException {
        log.info("Attempted access to / endpoint resulted in 403 Forbidden");
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
        return null;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
