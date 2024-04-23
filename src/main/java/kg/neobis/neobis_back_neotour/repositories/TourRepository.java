package kg.neobis.neobis_back_neotour.repositories;

import kg.neobis.neobis_back_neotour.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    @Query("SELECT t, COUNT(b.id) AS bookings_count " +
            "FROM Tour t " +
            "LEFT JOIN Booking b ON t.id = b.tour.id " +
            "GROUP BY t.id " +
            "ORDER BY bookings_count DESC " +
            "LIMIT 5")
    List<Tour> findPopularTours();

    @Query("SELECT t, AVG(r.rating) AS avgRating " +
            "FROM Tour t " +
            "JOIN Review r ON t.id = r.tour.id " +
            "GROUP BY t.id " +
            "ORDER BY avgRating DESC " +
            "LIMIT 5")
    List<Tour> findToursWithHighestAverageRating();

    @Query("SELECT t FROM Tour t ORDER BY t.views DESC LIMIT 5")
    List<Tour> findMostVisitedTours();

    List<Tour> findByContinent(String continent);

    List<Tour> findBySeason(String season);
}