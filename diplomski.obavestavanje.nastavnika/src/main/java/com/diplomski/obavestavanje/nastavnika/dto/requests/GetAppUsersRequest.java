package com.diplomski.obavestavanje.nastavnika.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetAppUsersRequest implements java.io.Serializable {

    private static final long serialVersionUID = 3L;
    private int page;
    private int size;
    private String sort;
}
