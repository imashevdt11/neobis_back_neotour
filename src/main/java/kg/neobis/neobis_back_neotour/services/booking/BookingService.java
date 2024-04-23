package kg.neobis.neobis_back_neotour.services.booking;


import kg.neobis.neobis_back_neotour.models.BookingDto;

import java.util.List;

public interface BookingService {

    BookingDto createBooking(BookingDto bookingDto);

    List<BookingDto> getAllBookings();

    BookingDto getBookingById(Long id);

    BookingDto updateBooking(Long id, BookingDto bookingDto);

    void deleteBooking(Long id);
}
