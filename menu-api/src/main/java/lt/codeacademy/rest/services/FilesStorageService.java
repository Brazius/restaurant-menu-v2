package lt.codeacademy.rest.services;

import lt.codeacademy.rest.services.exceptions.FileStorageException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FilesStorageService {

    private final Path storageLocation;


    public FilesStorageService() {
        this.storageLocation = Paths.get("./storage").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.storageLocation);
        } catch (IOException e) {
            throw new FileStorageException("Unable to create file storage");
        }
    }
        public void storeFile (MultipartFile file){
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            System.out.println(fileName);

            if (fileName.contains("..")) {
                throw  new FileStorageException("File name is invalid");
            }

            Path targetLocation = this.storageLocation.resolve(fileName);
            try {
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {
                e.printStackTrace();
                throw new FileStorageException("Coud not store file");
            }
                sentToDb(fileName, file.getContentType());
        }

        public void sentToDb(String fileName, String contentType) {
            try {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            System.out.println(date);
            String connectionUrl = "jdbc:h2:mem:eshopdb";


             Connection con = DriverManager.getConnection(connectionUrl, "sa", "pass");
                    Statement stmt = con.createStatement();
                System.out.println("connectio to db success");
                String sql = "INSERT INTO Pictures (File_Name, Content_type, Create_date) VALUES('"
                        +fileName+"', '" + contentType+"', '"+date + "' )";

                System.out.println(sql);
                stmt.execute(sql);

            }catch (SQLException e){
                e.printStackTrace();
            }
}

        public Resource getFile (String fileName) {
            Path fileLocation = this.storageLocation.resolve(fileName);
            try {
                Resource resource = new UrlResource(fileLocation.toUri());
                if (resource.exists()) {
                    return resource;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new FileStorageException("Unable to resolve URL for file" + fileName);
            }
            return null;
        }
    }

