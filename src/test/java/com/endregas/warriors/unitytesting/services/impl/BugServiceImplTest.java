package com.endregas.warriors.unitytesting.services.impl;

import com.endregas.warriors.unitytesting.model.database.BugReport;
import com.endregas.warriors.unitytesting.model.dto.BugReportDTO;
import com.endregas.warriors.unitytesting.repositories.BugRepository;
import com.endregas.warriors.unitytesting.services.BugService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BugServiceImplTest {

    @Mock
    BugRepository bugRepository;

    BugService bugService = new BugServiceImpl(bugRepository);

    @Test
    void reportABug() {
        BugReportDTO bugReport = new BugReportDTO("1", "1", 1L, "Notes");
        verify(bugRepository.save(any()), times(1));
        assertEquals(bugReport, bugService.reportABug(bugReport));
    }

    @Test
    void getBugsForARun() {
        BugReport bugReport = BugReport.builder()
                .timestamp(LocalDate.now())
                .buildId("1")
                .runId("1")
                .time(1L)
                .notes("Notes")
                .build();
        when(bugRepository.findAllByRunId(anyString())).thenReturn(List.of(bugReport));
        assertEquals(List.of(bugReport.convertToDTO()), bugService.getBugsForARun("1"));
    }
}