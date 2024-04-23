package kg.neobis.neobis_back_neotour.services.booking;


import kg.neobis.neobis_back_neotour.models.BookingDto;

import java.util.List;

public interface BookingService {

    BookingDto bookTour(BookingDto bookingDto);

    List<BookingDto> getAllBookings();

    BookingDto getBookingById(Long id);

    void deleteBooking(Long id);
}