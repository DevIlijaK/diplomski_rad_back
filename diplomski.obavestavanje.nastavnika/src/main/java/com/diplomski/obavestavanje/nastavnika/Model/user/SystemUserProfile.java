package com.diplomski.obavestavanje.nastavnika.Model.user;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.Optional;

@Value
@Builder
public class SystemUserProfile {

    @Builder.Default
    Long id = 0L;

    String uuid;
    Optional<byte[]> image;

    @Builder.Default
    Long userId = 0L;
    Optional<SystemUser> user;
}
