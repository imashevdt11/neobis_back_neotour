package kg.neobis.neobis_back_neotour.entities;

import jakarta.persistence.*;
import kg.neobis.neobis_back_neotour.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tour_id", nullable = false)
    Tour tour;

    @Column(name = "number_of_tourists", nullable = false)
    Integer number_of_tourists;

    @Column(name = "phone_number", nullable = false)
    String phone_number;

    @Column(name = "comment")
    String comment;
}