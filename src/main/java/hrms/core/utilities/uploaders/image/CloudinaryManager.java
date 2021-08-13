package hrms.core.utilities.uploaders.image;

import com.cloudinary.Cloudinary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import hrms.core.utilities.results.DataResult;
import hrms.core.utilities.results.ErrorDataResult;
import hrms.core.utilities.results.SuccessDataResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryManager implements ImageService {
    Cloudinary cloudinary;

    @Autowired
    public CloudinaryManager(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public DataResult<Map<String, String>> upload(MultipartFile multipartFile) {
        Map<String, Object> options = new HashMap<String, Object>();
        List<String> allowedFormats = Arrays.asList("png", "jpg", "jpeg");
        options.put("allowed_formats", allowedFormats);
        File file = convert(multipartFile);
        try {
            @SuppressWarnings("unchecked")
            Map<String, String> result = this.cloudinary.uploader().upload(file, options);
            file.delete();
            return new SuccessDataResult<Map<String, String>>(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ErrorDataResult<Map<String, String>>(null, "Cannot added !");
    }

    public DataResult<Map<String, String>> delete(String publicId) {
        Map<String, Boolean> options = new HashMap<String, Boolean>();
        options.put("invalidate", true);
        try {
            @SuppressWarnings("unchecked")
            Map<String, String> result = cloudinary.uploader().destroy(publicId, options);
            return new SuccessDataResult<Map<String, String>>(result);
        } catch (IOException e) {
            return new ErrorDataResult<Map<String, String>>(null, e.getMessage());
        }
    }

    private File convert(MultipartFile multipartFile) {
        File file = new File(multipartFile.getOriginalFilename());
        try {
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(multipartFile.getBytes());
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}