package com.project.neobis_back_neotour.entities;

import jakarta.persistence.*;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tours")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, length = 50)
    String name;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "image_url", nullable = false)
    String image_url;

    @Column(name = "city", nullable = false, length = 30)
    String city;

    @Column(name = "country", nullable = false, length = 30)
    String country;

    @Column(name = "continent", nullable = false, length = 30)
    String continent;

    @Column(name = "season", nullable = false, length = 30)
    String season;

    @Column(name = "views")
    Integer views;

    @Column(name = "created_at", nullable = false)
    LocalDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    LocalDateTime updated_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_at = LocalDateTime.now();
    }
}