package com.polarisdigitech.backendchallenge.services;

import com.polarisdigitech.backendchallenge.model.book.Book;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    CompletableFuture<Book> store(MultipartFile multipartFile);

    CompletableFuture<Stream<Path>> loadAll();

    Path load(String fileName);

    Resource loadAsResource(String fileName);

    void deleteAll();
}
