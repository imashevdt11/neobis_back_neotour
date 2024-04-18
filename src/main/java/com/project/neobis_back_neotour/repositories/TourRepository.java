package com.project.neobis_back_neotour.repositories;

import com.project.neobis_back_neotour.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    @Query("SELECT t FROM Tour t WHERE t.continent = :continent")
    List<Tour> findToursByContinent(@Param("continent") String continent);

//    @Query("SELECT t, COUNT(b.id) AS bookings_count " +
//            "FROM Tour t " +
//            "LEFT JOIN Booking b " +
//            "GROUP BY t.id " +
//            "ORDER BY bookings_count DESC " +
//            "LIMIT 5")
//    List<Tour> findTourByPopularity();
}