package com.diplomski.obavestavanje.nastavnika.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAppUsersRequest implements java.io.Serializable {

    private static final long serialVersionUID = 260220231837L;
    private int page;
    private int size;
    private String sort;
}
