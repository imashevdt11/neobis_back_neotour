package kg.neobis.neobis_back_neotour.entities;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;

import kg.neobis.neobis_back_neotour.base.BaseEntity;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tours")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tour extends BaseEntity {

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
}