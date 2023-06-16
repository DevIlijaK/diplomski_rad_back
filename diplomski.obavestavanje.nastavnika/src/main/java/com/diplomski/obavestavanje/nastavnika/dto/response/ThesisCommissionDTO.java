package com.diplomski.obavestavanje.nastavnika.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThesisCommissionDTO {
    private ProfessorDTO professor;
    private String role;
}
