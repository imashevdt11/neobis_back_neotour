package kg.neobis.neobis_back_neotour.models;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import org.springframework.web.multipart.MultipartFile;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageDto {
    String name;
    MultipartFile file;
}
