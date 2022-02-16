package com.example.fileuploader.controller;

import com.example.fileuploader.entity.File;
import com.example.fileuploader.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class FileUploader {
    @Autowired
    private FileUploadService fileUploadService;


    @GetMapping("/home")
    public String goToHomePage(Model model){
        List<File> fileList = fileUploadService.getFiles();
        model.addAttribute("docs", fileList);
        return "home";
    }


    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("files") MultipartFile files){
            fileUploadService.uploadFile(files);
        return "redirect:/home";
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
        File file = fileUploadService.getFile(fileId).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\""+file.getDocName()+"\"")
                .body(new ByteArrayResource(file.getData()));
    }

    @DeleteMapping("/deleteFile/{fileId}")
    public String deleteFile(@PathVariable Integer fileId){

        fileUploadService.deleteFiles(fileId);

        return "redirect:/home";
    }


}
