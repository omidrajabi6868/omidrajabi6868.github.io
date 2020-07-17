package project.assistant.Services.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import project.assistant.ExceptionHandler.StorageException;
import project.assistant.ExceptionHandler.StorageFileNotFoundException;
import project.assistant.Repositories.IStorageRepository;
import project.assistant.Services.Utility.StorageProperties;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class StorageService implements IStorageRepository {

    private final Path rootLocation;

    @Autowired
    public StorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            String username = getUserName();
            if (!Files.isDirectory(this.rootLocation.resolve(username)))
                Files.createDirectory(this.rootLocation.resolve(username));
            Files.copy(file.getInputStream(), this.rootLocation.resolve(username + "\\" + file.getOriginalFilename()));

        }catch(FileAlreadyExistsException ex) {
            throw new StorageException("File already exists");

        }catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            String username = getUserName();
            Path rootLocation = this.rootLocation.resolve(username);
            return Files.walk(rootLocation, 1)
                    .filter(path -> !path.equals(rootLocation))
                    .map(path -> rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(getUserName() + "\\" + filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            if (!Files.isDirectory(rootLocation))
                Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    private String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return userDetail.getUsername();
        } else {
            return "Anonymous";
        }
    }

}
