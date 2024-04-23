package kg.neobis.neobis_back_neotour.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tour_id", nullable = false)
    Tour tour;

    @Column(name = "booking_date", nullable = false)
    LocalDateTime booking_date;

    @Column(name = "number_of_tourists", nullable = false)
    Integer number_of_tourists;

    @Column(name = "phone_number", nullable = false)
    String phone_number;

    @Column(name = "comment")
    String comment;

    @Column(name = "created_at", nullable = false)
    LocalDateTime created_at;

    @Column(name = "updated_at", nullable = false)
    LocalDateTime updated_at;

    @PrePersist
    protected void onCreate() {
        this.booking_date = LocalDateTime.now();
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_at = LocalDateTime.now();
    }
}