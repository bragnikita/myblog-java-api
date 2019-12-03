package jp.bragnikita.myblogapi.services;

import jp.bragnikita.myblogapi.utils.AppExceptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.EnumSet;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class FileStorageService {

    @Value("${app.filestorage.images.root}")
    private String imagesDirRoot;
    @Value("${app.filestorage.images.path}")
    private String imageDirRelativePath;

    public ResourceRef save(String domain, MultipartFile uploadedFile) {
        Path rel = Paths.get(domain, uploadedFile.getOriginalFilename());
        Path path = Paths.get(imagesDirRoot, rel.toString());
        if (Files.exists(path)) {
            String datetimeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd_A"));
            rel = Paths.get(domain, datetimeStr + "__" + uploadedFile.getOriginalFilename());
            path = Paths.get(imagesDirRoot, rel.toString());
        }
        try {
            Files.createDirectories(path.getParent());
            File f = new File(path.toString());
            f.createNewFile();
            Files.copy(uploadedFile.getInputStream(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new AppExceptions.ResourceStoringException();
        }
        return new ResourceRef(rel.toString());
    }

    public static class ResourceRef {
        private String location;

        public ResourceRef(String location) {
            this.location = location;
        }

        public String getLocation() {
            return this.location;
        }
    }
}
