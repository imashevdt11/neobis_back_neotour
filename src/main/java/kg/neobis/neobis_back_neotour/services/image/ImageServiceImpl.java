package kg.neobis.neobis_back_neotour.services.image;

import kg.neobis.neobis_back_neotour.entities.Image;
import kg.neobis.neobis_back_neotour.models.ImageDto;
import kg.neobis.neobis_back_neotour.repositories.ImageRepository;
import kg.neobis.neobis_back_neotour.services.cloudinary.CloudinaryService;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImageServiceImpl implements ImageService {

    CloudinaryService cloudinaryService;
    ImageRepository imageRepository;

    @Override
    public ResponseEntity<Map> uploadImage(ImageDto imageDto) {
        try {
            String url = cloudinaryService.uploadFile(imageDto.getFile(), "images");
            if (url != null) {
                Image image = new Image();
                image.setName(imageDto.getName());
                image.setUrl(url);
                imageRepository.save(image);
                return ResponseEntity.ok().body(Map.of("url", url));
            }
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to upload image"));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}