package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.AppUserFile;
import com.udacity.jwdnd.course1.cloudstorage.exception.FileStorageException;
import com.udacity.jwdnd.course1.cloudstorage.repository.FileRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Service
public class FileService {
    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }



    public void store(MultipartFile file,Long userId) throws FileStorageException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            // check if the file already exists
            if(!fileRepository.findByName(fileName).isEmpty()) {
                throw new FileStorageException("A file with the same name already exists. Please check." );
            };

            AppUserFile appUserFile = new AppUserFile(fileName, file.getContentType(), file.getBytes(),userId);
            fileRepository.save(appUserFile);
        } catch (IOException e) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
        }
    }

    public List<AppUserFile> getUserFileList(Long userId) {

        return fileRepository.getFilelistByUserId(userId);
    }

    public AppUserFile getFile(Long id) {
        return fileRepository.findById(id).get();
    }

    public void deleteFileById(Long id) {
        fileRepository.deleteById(id);
    }
}
