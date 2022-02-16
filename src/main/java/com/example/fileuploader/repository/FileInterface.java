package com.example.fileuploader.repository;

import com.example.fileuploader.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.print.Doc;
import java.util.Optional;

public interface FileInterface extends JpaRepository<File, Integer> {
    File deleteFileById(Integer fileId);
}
