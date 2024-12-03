package com.capstone.officeresourcebooking.services;

import com.capstone.officeresourcebooking.models.*;
import com.capstone.officeresourcebooking.repositories.ReservationRepository;
import com.capstone.officeresourcebooking.repositories.ResourceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ResourceRepository resourceRepository;

    public ReservationService(ReservationRepository reservationRepository, ResourceRepository resourceRepository) {
        this.reservationRepository = reservationRepository;
        this.resourceRepository = resourceRepository;
    }

    public BookingResponse checkAvailability(BookingRequest bookingRequest) {
        String roomName = bookingRequest.getRoomName();
        LocalDateTime startTime = bookingRequest.getStartTime();
        LocalDateTime endTime = bookingRequest.getEndTime();

        startTime = convertUTCToLocal(startTime);
        endTime = convertUTCToLocal(endTime);

        BookingResponse response = new BookingResponse();
        try {
            if (reservationRepository.findReservationsByResourceAndTime(roomName, startTime, endTime).isEmpty()) {
                response.setResponseStatus(BookingResponse.RequestStatus.AVAILABLE);
            }
            else {
                response.setResponseStatus(BookingResponse.RequestStatus.UNAVAILABLE);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.setResponseStatus(BookingResponse.RequestStatus.ERROR);
        }
        return response;
    }

    public BookingResponse makeReservation(BookingRequest newReservation, int resourceId, int userId) {
        Reservation reservation = new Reservation(convertUTCToLocal(newReservation.getStartTime()), convertUTCToLocal(newReservation.getEndTime()), newReservation.getRoomName(), resourceId, userId);
        BookingResponse response = new BookingResponse();

        try {
            if (reservationRepository.findReservationsByResourceAndTime(newReservation.getRoomName(), newReservation.getStartTime(), newReservation.getEndTime()).isEmpty()) {
                reservationRepository.save(reservation);
                response.setResponseStatus(BookingResponse.RequestStatus.SUCCESS);
            }
            else {
                response.setResponseStatus(BookingResponse.RequestStatus.UNAVAILABLE);
            }
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.setResponseStatus(BookingResponse.RequestStatus.ERROR);
            return response;
        }
    }

    public List<Event> getUserEvents(Long userId) {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);

        // Fetch reservations
        List<Reservation> reservations = reservationRepository.findReservationsForUserOnDate(userId, startOfDay, endOfDay);

        List<Event> events = new java.util.ArrayList<>(List.of());
        for (Reservation reservation : reservations) {
            String color;
            if (reservation.getStatus() == Reservation.ReservationStatus.CONFIRMED) {
                color = "#4caf50";
            }
            else if (reservation.getStatus() == Reservation.ReservationStatus.PENDING) {
                color = "#ff9800";
            }
            else {
                color = "#ff0000";
            }
            System.out.println("getting resource");
            Resource resource = resourceRepository.findResourceById((long)reservation.getResourceId()); // resource for fields
            System.out.println(resource);
            Event event = new Event(resourceRepository.findResourceNameById((long) reservation.getResourceId()), reservation.getStartTime().toString(), color, String.valueOf(resource.getCapacity()), resource.getDescription());
            events.add(event);
        }
        return events;
    }

    public List<Event> getCalendarView(Long userId) {
        LocalDateTime startOfMonth = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime endOfMonth = startOfMonth.plusMonths(1).minusSeconds(1);

        // Fetch reservations
        List<Reservation> reservations = reservationRepository.findReservationsForUserOnDate(userId, startOfMonth, endOfMonth);

        List<Event> events = new java.util.ArrayList<>(List.of());
        for (Reservation reservation : reservations) {
            String color;
            if (reservation.getStatus() == Reservation.ReservationStatus.CONFIRMED) {
                color = "#4caf50";
            }
            else if (reservation.getStatus() == Reservation.ReservationStatus.PENDING) {
                color = "#ff9800";
            }
            else {
                color = "#ff0000";
            }
            String eventDate = reservation.getStartTime().toLocalDate().toString();
            Resource resource = resourceRepository.findResourceById((long)reservation.getReservationId()); // resource for fields
            Event event = new Event(resourceRepository.findResourceNameById((long) reservation.getResourceId()), eventDate, color);
            events.add(event);
        }
        return events;
    }

    public static LocalDateTime convertUTCToLocal(LocalDateTime utcDateTime) {
        // Convert to the local timezone
        ZoneId localZoneId = ZoneId.of("America/Chicago");
        ZonedDateTime utcZonedDateTime = utcDateTime.atZone(ZoneId.of("UTC"));
        ZonedDateTime localZonedDateTime = utcZonedDateTime.withZoneSameInstant(localZoneId);
        return localZonedDateTime.toLocalDateTime();
    }
}
