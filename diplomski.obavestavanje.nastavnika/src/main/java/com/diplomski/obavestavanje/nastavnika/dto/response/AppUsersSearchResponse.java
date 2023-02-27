package com.diplomski.obavestavanje.nastavnika.dto.response;

import com.diplomski.obavestavanje.nastavnika.dto.AppUserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
@Builder
public class AppUsersSearchResponse implements java.io.Serializable{
    List<AppUserDTO> appUsers;
    int total;
}
