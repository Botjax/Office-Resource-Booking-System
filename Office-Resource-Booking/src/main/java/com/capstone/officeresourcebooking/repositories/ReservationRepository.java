package com.capstone.officeresourcebooking.repositories;

import com.capstone.officeresourcebooking.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(value = "SELECT r FROM Reservation r " +
            "JOIN Resource res ON r.resourceId = res.id " +
            "WHERE res.resourceName = :resourceName " +
            "AND r.startTime < :endTime " +
            "AND r.endTime > :startTime")
    List<Reservation> findReservationsByResourceAndTime(@Param("resourceName") String resourceName,
                                                        @Param("startTime") LocalDateTime startTime,
                                                        @Param("endTime") LocalDateTime endTime);

    @Query(value = "SELECT * FROM reservations " +
            "WHERE user_id = :userId " +
            "AND start_time >= :startTime " +
            "AND start_time <= :endTime", nativeQuery = true)
    List<Reservation> findReservationsForUserOnDate(@Param("userId") long userId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
