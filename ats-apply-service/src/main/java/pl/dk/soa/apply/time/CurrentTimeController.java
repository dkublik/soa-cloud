package pl.dk.soa.apply.time;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/time", produces = APPLICATION_JSON_VALUE)
@Api(description = "current time")
class CurrentTimeController {

    @GetMapping
    @ApiOperation(value = "current time")
    CurrentTime currentTime() {
        Calendar calendar = Calendar.getInstance();
        return new CurrentTime(calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
    }

}
