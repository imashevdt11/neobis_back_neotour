package com.project.neobis_back_neotour.base;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseDto {

    Long id;
    LocalDateTime created_at;
    LocalDateTime updated_at;

    public BaseDto(Long id, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}