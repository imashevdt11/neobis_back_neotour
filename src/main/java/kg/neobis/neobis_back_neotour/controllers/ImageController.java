package kg.neobis.neobis_back_neotour.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;

import kg.neobis.neobis_back_neotour.models.ImageDto;
import kg.neobis.neobis_back_neotour.commons.EndpointConstants;
import kg.neobis.neobis_back_neotour.services.image.ImageService;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "Image")
@RequestMapping(EndpointConstants.IMAGES_ENDPOINT)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImageController {

    ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Map> upload(@ModelAttribute ImageDto image) {
        return imageService.uploadImage(image);
    }
}