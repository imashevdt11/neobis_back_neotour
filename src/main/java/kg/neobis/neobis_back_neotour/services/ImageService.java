package kg.neobis.neobis_back_neotour.services;

import org.springframework.http.ResponseEntity;
import kg.neobis.neobis_back_neotour.models.ImageDto;

import java.util.Map;

public interface ImageService {

    ResponseEntity<Map> uploadImage(ImageDto imageDto);
}