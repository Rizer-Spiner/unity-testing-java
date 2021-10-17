package com.endregas.warriors.unitytesting.controllers;

import com.endregas.warriors.unitytesting.model.dto.BugReportDTO;
import com.endregas.warriors.unitytesting.services.BugService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BugController {

    private final BugService bugService;

    @PostMapping(value = "/bug", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BugReportDTO> saveVideo(@Valid @RequestBody BugReportDTO report) {
        return ResponseEntity.ok(bugService.reportABug(report));
    }

    @GetMapping(value = "/bug")
    public ResponseEntity<List<BugReportDTO>> getRunBugs(@RequestParam(name = "runId") String runId){
        return ResponseEntity.ok(bugService.getBugsForARun(runId));
    }

}
