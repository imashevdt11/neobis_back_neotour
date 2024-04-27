package kg.neobis.neobis_back_neotour.services.impl;

import jakarta.annotation.Resource;
import kg.neobis.neobis_back_neotour.services.CloudinaryService;
import org.springframework.stereotype.Service;
import com.cloudinary.Cloudinary;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    @Resource
    private Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile file, String folderName) throws IOException {
        Map<String, Object> options = new HashMap<>();
        options.put("folder", folderName);
        Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), options);
        return result.get("url").toString();
    }
}