package pl.dk.soa.apply.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dk.soa.apply.service.ApplyService;
import pl.dk.soa.apply.store.StoredApplication;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/v1/job-applications", produces = APPLICATION_JSON_VALUE)
@Api(description = "job applications")
class ApplyController {

    private ApplyService applyService;

    ApplyController(ApplyService applyService) {
        this.applyService = applyService;
    }

    @PostMapping()
    @ApiOperation(value = "apply for job")
    ResponseEntity<AppIdResponse> applyForJob(@RequestBody Application application) {
        StoredApplication storedApplication = applyService.apply(application);
        storedApplication.accepted();
        return new ResponseEntity<>(new AppIdResponse(storedApplication.getId(),
                storedApplication.getStatus().toString(), storedApplication.getPriority().toString()),
                ACCEPTED);
    }

    @GetMapping()
    @ApiOperation(value = "show Applications")
    List<StoredApplication> showApplications() {
        return applyService.findAll();
    }

}
