package com.project.neobis_back_neotour.services.booking;

import com.project.neobis_back_neotour.entities.Booking;
import com.project.neobis_back_neotour.exceptions.BookingNotFoundException;
import com.project.neobis_back_neotour.models.BookingDto;
import com.project.neobis_back_neotour.repositories.BookingRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingServiceImpl implements BookingService {

    BookingRepository bookingRepository;

    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        Booking review = Booking.builder()
                .phone_number(bookingDto.getPhone_number())
                .tour(bookingDto.getTour())
                .number_of_tourists(bookingDto.getNumber_of_tourists())
                .total_price(bookingDto.getTotal_price())
                .comment(bookingDto.getComment())
                .build();
        Booking savedBooking = bookingRepository.save(review);
        return convertToBookingDto(savedBooking);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        List<Booking> bookingList = bookingRepository.findAll();
        return bookingList.stream().map(this::convertToBookingDto).toList();
    }

    @Override
    public BookingDto getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id, HttpStatus.NOT_FOUND.value()));
        return convertToBookingDto(booking);
    }

    @Override
    public BookingDto updateBooking(Long id, BookingDto bookingDto) {

        BookingDto booking = getBookingById(id);

        if (booking == null) {
            throw new BookingNotFoundException("Booking not found with id: " + id, HttpStatus.NOT_FOUND.value());
        }

        booking.setTour(bookingDto.getTour());
        booking.setPhone_number(bookingDto.getPhone_number());
        booking.setNumber_of_tourists(booking.getNumber_of_tourists());
        booking.setTotal_price(booking.getTotal_price());

        Booking updatedBooking = bookingRepository.save(convertToBookingEntity(booking));
        return convertToBookingDto(updatedBooking);
    }

    @Override
    public void deleteBooking(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);

        if (optionalBooking.isPresent()) {
            bookingRepository.deleteById(id);
        } else {
            throw new BookingNotFoundException("Booking not found with id: " + id, HttpStatus.NOT_FOUND.value());
        }
    }

    public BookingDto convertToBookingDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .tour(booking.getTour())
                .booking_date(booking.getBooking_date())
                .total_price(booking.getTotal_price())
                .phone_number(booking.getPhone_number())
                .number_of_tourists(booking.getNumber_of_tourists())
                .created_at(booking.getCreated_at())
                .updated_at(booking.getUpdated_at())
                .build();
    }

    public Booking convertToBookingEntity(BookingDto booking) {
        return Booking.builder()
                .id(booking.getId())
                .phone_number(booking.getPhone_number())
                .tour(booking.getTour())
                .booking_date(booking.getBooking_date())
                .total_price(booking.getTotal_price())
                .number_of_tourists(booking.getNumber_of_tourists())
                .created_at(booking.getCreated_at())
                .updated_at(booking.getUpdated_at())
                .build();
    }
}