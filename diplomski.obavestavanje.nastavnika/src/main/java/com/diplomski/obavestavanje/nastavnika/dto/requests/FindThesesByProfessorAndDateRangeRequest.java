package com.diplomski.obavestavanje.nastavnika.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindThesesByProfessorAndDateRangeRequest {
    private String email;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
