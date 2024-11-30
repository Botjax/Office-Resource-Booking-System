package com.capstone.officeresourcebooking.services;

import com.capstone.officeresourcebooking.models.BookingRequest;
import com.capstone.officeresourcebooking.models.BookingResponse;
import com.capstone.officeresourcebooking.models.Reservation;
import com.capstone.officeresourcebooking.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public BookingResponse checkAvailability(BookingRequest bookingRequest) {
        String roomName = bookingRequest.getRoomName();
        LocalDateTime startTime = bookingRequest.getStartTime();
        LocalDateTime endTime = bookingRequest.getEndTime();
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
        Reservation reservation = new Reservation(newReservation.getStartTime(), newReservation.getEndTime(), newReservation.getRoomName(), resourceId, userId);
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
}
