package tech.viacom.arc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // TODO remove me after aws switch to /application/info
@RequestMapping("/actuator/info")
class LegacyInfoController {

    @GetMapping
    String info() {
        return "working";
    }

}
