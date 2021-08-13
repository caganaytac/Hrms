package hrms.core.utilities.uploaders.image;

import org.springframework.web.multipart.MultipartFile;
import hrms.core.utilities.results.DataResult;

import java.util.Map;

public interface ImageService {
    DataResult<Map<String, String>> upload(MultipartFile multipartFile);

    DataResult<Map<String, String>> delete (String publicId);
}