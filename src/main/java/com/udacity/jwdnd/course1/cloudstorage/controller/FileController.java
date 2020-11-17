package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.entity.AppUserFile;
import com.udacity.jwdnd.course1.cloudstorage.exception.FileStorageException;
import com.udacity.jwdnd.course1.cloudstorage.service.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;
    private final AppUserService appUserService;

    public FileController(FileService fileService, AppUserService appUserService) {
        this.fileService = fileService;
        this.appUserService = appUserService;
    }

    @GetMapping("{id}")
    public void downloadFile(@PathVariable Long id, HttpServletResponse response) {
        MultipartFile file = fileService.getFile(id);
        response.setContentType(file.getContentType());
        response.setContentLength((int)file.getSize());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        try {
            FileCopyUtils.copy(file.getInputStream(), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }


    @PostMapping()
    public String uploadFile(Authentication auth, MultipartFile fileUpload, Model model) throws FileStorageException {
        String username = auth.getPrincipal().toString();
        AppUser user = appUserService.loadUserByUsername(username);
        Long userId = user.getId();
        try {
            if (!fileUpload.isEmpty()) {
                fileService.store(fileUpload, userId);
            } else {
                throw new FileStorageException("No file was uploaded. Please make sure to choose file first.");
            }
        }
//        List<AppUserFile> list = fileService.getUserFileList(userId);
//        model.addAttribute("appUserFiles", list);
//        model.addAttribute("activeTab", "file");
        catch(FileStorageException ex) {
            model.addAttribute("errorMsg", ex.getErrorMsg());
        }
        return "result";
    }

    @DeleteMapping()
    public String deleteFile(@RequestParam Long id,Model model) {

        fileService.deleteFileById(id);
        model.addAttribute("resultMsg", "SUCCESS");
        return "result";
    }

}


