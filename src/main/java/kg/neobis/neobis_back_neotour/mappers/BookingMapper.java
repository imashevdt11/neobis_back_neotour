package kg.neobis.neobis_back_neotour.mappers;

import kg.neobis.neobis_back_neotour.entities.Booking;
import kg.neobis.neobis_back_neotour.models.BookingDto;

public class BookingMapper {
    public static BookingDto toDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .tour(booking.getTour())
                .phone_number(booking.getPhone_number())
                .number_of_tourists(booking.getNumber_of_tourists())
                .comment(booking.getComment())
                .created_at(booking.getCreated_at())
                .updated_at(booking.getUpdated_at())
                .build();
    }
}