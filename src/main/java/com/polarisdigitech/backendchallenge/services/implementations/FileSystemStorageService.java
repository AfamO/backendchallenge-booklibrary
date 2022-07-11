package com.polarisdigitech.backendchallenge.services.implementations;

import com.polarisdigitech.backendchallenge.helpers.StorageException;
import com.polarisdigitech.backendchallenge.helpers.StorageProperties;
import com.polarisdigitech.backendchallenge.model.book.Book;
import com.polarisdigitech.backendchallenge.repository.book.BookRepository;
import com.polarisdigitech.backendchallenge.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
@Slf4j
public class FileSystemStorageService implements StorageService {

    private Path rootLocation;

    private BookRepository bookRepository;


    public FileSystemStorageService(StorageProperties storageProperties, BookRepository bookRepository){
        this.rootLocation = Paths.get(storageProperties.getLocation());
        this.bookRepository = bookRepository;
        log.info("storageLocation dir =={}",storageProperties.getLocation());
    }
    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException ioException){
            throw new StorageException("Oops!, can't create directories");
        }
    }

    @Override
    @Async(value = "afamTaskExecutor")
    public CompletableFuture<Book> store(MultipartFile multipartFile) {
        final long start = System.currentTimeMillis();
        log.info("CurrentTime in Millis-> {}",start);

        if(multipartFile ==null || multipartFile.isEmpty()){
            throw  new StorageException("Multipart can't be null/empty");
        }
        Path destinationFile = rootLocation.resolve(Paths.get(multipartFile.getOriginalFilename()))
                                            .normalize().toAbsolutePath();
        if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())){
            throw new StorageException("Must be stored in the same folder");
        }
        try (InputStream inputStream = multipartFile.getInputStream()){
            Files.copy(inputStream,destinationFile, StandardCopyOption.REPLACE_EXISTING);
            Optional<Book> book = bookRepository.findById("012-123-4567");
            File renamedFile = new File(LocalDateTime.now().toString()+"_"+destinationFile.toFile().getName());
            destinationFile.toFile().renameTo(renamedFile);
            book.get().setPhoto(renamedFile.getName());
            log.info("Saving the book->{}",book);
            Book savedBook = bookRepository.save(book.get());
            log.info("Elapsed Time ->{}",System.currentTimeMillis()-start);
            return CompletableFuture.completedFuture(savedBook);
        }
        catch (MaxUploadSizeExceededException sizeLimitExceededException){
            throw new StorageException("Ooops!, File size limit Exceeded. The file size can't be more than 128KB. Full Message::"+sizeLimitExceededException.getMessage());
        }
        catch(IOException ioException){
            throw new StorageException("Failed to store file");
        }

    }

    @Override
    @Async(value = "afamTaskExecutor")
    public CompletableFuture<Stream<Path>> loadAll() {
        log.info("Request to get a list of file paths");
        try {
            Stream<Path> pathStream = Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(rootLocation))
                    .map(this.rootLocation::relativize);

            return CompletableFuture.completedFuture(pathStream);
        }
        catch (IOException ioException){
            throw new StorageException("Failed to read and retrieve stored files");
        }
    }

    @Override
    public Path load(String filename) {
        return this.rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            }
            else {
                throw new StorageException("Could not read as resource file: "+fileName);
            }
        }
        catch (MalformedURLException urlException){
            throw new StorageException("Could not read file: "+fileName+" because of "+urlException.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(this.rootLocation.toFile());
    }
}
