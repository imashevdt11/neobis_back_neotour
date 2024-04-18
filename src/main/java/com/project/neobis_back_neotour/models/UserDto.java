package com.project.neobis_back_neotour.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 30)
    String username;

    @NotBlank(message = "Image is required")
    String image_url;

    LocalDateTime created_at;

    LocalDateTime updated_at;
}