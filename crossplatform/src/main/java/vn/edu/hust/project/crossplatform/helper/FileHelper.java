package vn.edu.hust.project.crossplatform.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.constant.UploadDirectory;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Slf4j
public class FileHelper {
    public static String storeFile(UploadDirectory uploadType, MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                throw new ApplicationException(ResponseCode.UPLOAD_FILE_FAILED, "File is empty or null");
            }
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            String uniqueFileName = UUID.randomUUID() + "_" + fileName;
            Path uploadDir = Paths.get(uploadType.getValue());
            if(!Files.exists(uploadDir)) Files.createDirectories(uploadDir);
            Path destination = uploadDir.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            return uniqueFileName;
        }
        catch (Exception e) {
            log.error("upload file error: {}", e.getMessage());
            throw new ApplicationException(ResponseCode.UPLOAD_FILE_FAILED , e.getMessage());
        }
    }

    public static File getFile(UploadDirectory uploadType, String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            log.error("File name is empty or null");
            throw new ApplicationException("File name is null or empty");
        }
        File file = new File(Paths.get(uploadType.getValue(), fileName).toString());
        if (!file.exists() || !file.isFile()) {
            log.error("File not exists or is not a file");
            throw new ApplicationException("File not found: " + fileName);
        }
        return file;
    }
}

