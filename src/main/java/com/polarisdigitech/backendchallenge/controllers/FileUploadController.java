package com.polarisdigitech.backendchallenge.controllers;

import com.polarisdigitech.backendchallenge.repository.book.BookRepository;
import com.polarisdigitech.backendchallenge.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("")
@Slf4j
public class FileUploadController {

    private final StorageService storageService;


    @Autowired
    public FileUploadController(StorageService storageService){
        this.storageService = storageService;
        this.storageService.init();

    }

    @PostMapping("/uploadForm")
    public String handleFileUpload(@RequestParam("file")MultipartFile multipartFile, RedirectAttributes redirectAttributes){
        log.info("Uploaded fileName::{}",multipartFile.getOriginalFilename());
        storageService.store(multipartFile);
        redirectAttributes.addFlashAttribute("message","You successfully uploaded " + multipartFile.getOriginalFilename() + "!");
        return "redirect:/uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename="+file.getFilename()).body(file);

    }
    @GetMapping("/list")
    public CompletableFuture<String> listUploadedFiles(Model model) throws IOException{

        List<String> fileLists = null;
        try {
            fileLists = storageService.loadAll().get()
                    .map((path)-> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                            "serveFile",
                            path.getFileName().toString()).build().toUri().toString())
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            log.info("Failed to read records::{}",e);
            return CompletableFuture.completedFuture( ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build().getBody().toString());

        } catch (ExecutionException e) {
            log.info("Failed to read records::{}",e);
            return CompletableFuture.completedFuture( ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build().getBody().toString());

        }
        model.addAttribute("files", fileLists);
        return  CompletableFuture.completedFuture("uploadForm");
    }





}
