package pl.dk.soa.apply.resourcev4;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dk.soa.apply.resource.Application;
import pl.dk.soa.apply.service.ApplyService;
import pl.dk.soa.apply.store.StoredApplication;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/job-applications", produces = APPLICATION_JSON_VALUE, consumes = "application/vnd.apply.v4+json; charset=UTF-8")
@Api(description = "job applications v4")
class ApplyControllerV4 {

    private ApplyService applyService;
    private final ApplicationApprover applicationApprover;

    ApplyControllerV4(ApplyService applyService, ApplicationApprover applicationApprover) {
        this.applyService = applyService;
        this.applicationApprover = applicationApprover;
    }

    @PostMapping
    @ApiOperation(value = "apply for job")
    ResponseEntity<AppIdResponseV4> applyForJob(@RequestBody Application application) throws InterruptedException {
        StoredApplication storedApplication = applyService.apply(application);
        applicationApprover.approve(storedApplication);
        return new ResponseEntity<>(new AppIdResponseV4(storedApplication.getId(),
                storedApplication.getPriority().toString()),
                ACCEPTED);
    }

    @GetMapping("{applicationId}")
    @ApiOperation(value = "show Application")
    ResponseEntity<StoredApplication> showApplication(@PathVariable String applicationId) {
        return applyService.find(applicationId)
                .map(data -> new ResponseEntity(data, OK))
                .orElse(new ResponseEntity(NOT_FOUND));
    }

    @GetMapping
    @ApiOperation(value = "show Applications")
    List<StoredApplication> showApplications() {
        return applyService.findAll();
    }

}
