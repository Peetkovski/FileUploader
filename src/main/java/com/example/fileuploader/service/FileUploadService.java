package com.example.fileuploader.service;

import com.example.fileuploader.entity.File;
import com.example.fileuploader.repository.FileInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FileUploadService {

    @Autowired
    private FileInterface fileInterface;


    public com.example.fileuploader.entity.File uploadFile(MultipartFile file){
        String docname = file.getOriginalFilename();

        try {

            File file1 = new File(docname, file.getContentType(),file.getBytes());
            if(docname.equals("")){

                log.warn("No file Found!");
            }
            else {

                log.info("File saved");
                return fileInterface.save(file1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Optional<File> getFile(Integer fileId){
        return fileInterface.findById(fileId);
    }

    public List<File> getFiles(){
        return fileInterface.findAll();
    }

    public String deleteFiles(Integer fileId){

        fileInterface.deleteFileById(fileId);

        return "Deleted";
    }

}
