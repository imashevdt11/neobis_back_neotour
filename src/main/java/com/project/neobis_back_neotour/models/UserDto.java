package com.project.neobis_back_neotour.models;

import com.project.neobis_back_neotour.base.BaseDto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto extends BaseDto {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 30)
    String username;

    @NotBlank(message = "Image is required")
    String image_url;

    @Builder
    public UserDto(Long id, LocalDateTime created_at, LocalDateTime updated_at, String username, String image_url) {
        super(id, created_at, updated_at);
        this.username = username;
        this.image_url = image_url;
    }
}