package kg.neobis.neobis_back_neotour.repositories;

import kg.neobis.neobis_back_neotour.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {}