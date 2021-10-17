package com.endregas.warriors.unitytesting.model.dto;

import com.endregas.warriors.unitytesting.model.database.BugReport;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class BugReportDTO {

    @NotBlank(message = "Build id cannot be blank or null")
    private String buildId;
    @NotBlank(message = "Run id cannot be blank or null")
    private String runId;
    @NotNull(message = "Time cannot be null")
    private Long time;
    @Size(max = 1000, message = "Notes cannot contain more than 1000 characters")
    private String notes;

    public BugReport createBugReport(){
        return BugReport.builder()
                .runId(runId)
                .buildId(buildId)
                .timestamp(LocalDate.now())
                .time(time)
                .notes(notes)
                .build();
    }

}
