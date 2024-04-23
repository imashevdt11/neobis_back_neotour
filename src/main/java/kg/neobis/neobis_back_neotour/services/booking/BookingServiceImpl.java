package kg.neobis.neobis_back_neotour.services.booking;

import kg.neobis.neobis_back_neotour.entities.Booking;
import kg.neobis.neobis_back_neotour.entities.Tour;
import kg.neobis.neobis_back_neotour.exceptions.BookingNotFoundException;
import kg.neobis.neobis_back_neotour.exceptions.TourNotFoundException;
import kg.neobis.neobis_back_neotour.mappers.BookingMapper;
import kg.neobis.neobis_back_neotour.models.BookingDto;
import kg.neobis.neobis_back_neotour.repositories.BookingRepository;
import kg.neobis.neobis_back_neotour.repositories.TourRepository;
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
    TourRepository tourRepository;

    @Override
    public BookingDto bookTour(BookingDto bookingDto) {
        Tour tour = tourRepository.findById(bookingDto.getTour().getId())
                .orElseThrow(() -> new TourNotFoundException("Tour not found with id: " + bookingDto.getTour().getId(), HttpStatus.NOT_FOUND.value()));
        Booking review = Booking.builder()
                .tour(tour)
                .phone_number(bookingDto.getPhone_number())
                .number_of_tourists(bookingDto.getNumber_of_tourists())
                .comment(bookingDto.getComment())
                .build();
        Booking savedBooking = bookingRepository.save(review);
        return BookingMapper.toDto(savedBooking);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        List<Booking> bookingList = bookingRepository.findAll();
        return bookingList.stream().map(BookingMapper::toDto).toList();
    }

    @Override
    public BookingDto getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id, HttpStatus.NOT_FOUND.value()));
        return BookingMapper.toDto(booking);
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
}