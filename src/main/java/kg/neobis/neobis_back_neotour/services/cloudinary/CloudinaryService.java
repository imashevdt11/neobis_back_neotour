package kg.neobis.neobis_back_neotour.services.cloudinary;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    String uploadFile(MultipartFile file, String folderName) throws IOException;
}