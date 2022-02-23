package com.polarisdigitech.backendchallenge.controllers;

import com.polarisdigitech.backendchallenge.repository.book.BookRepository;
import com.polarisdigitech.backendchallenge.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("")
@Slf4j
public class FileUploadController {

    private final StorageService storageService;

    @Value("${storage.directory}")
    private String storageLocation;



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

    @Bean
    private String getStorageLocation(){
        return storageLocation;
    }
}
