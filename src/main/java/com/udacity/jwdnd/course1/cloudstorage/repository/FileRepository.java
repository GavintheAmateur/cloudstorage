package com.udacity.jwdnd.course1.cloudstorage.repository;

import com.udacity.jwdnd.course1.cloudstorage.entity.AppUserFile;
import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface FileRepository extends CrudRepository<AppUserFile,Long> {
    @Query("select new AppUserFile(id, name, type,createdDt) from AppUserFile where userId= ?1")
    public List<AppUserFile> getFilelistByUserId(Long userId);

    @Query("select new AppUserFile(id, name, type,createdDt) from AppUserFile where name= ?1")
    public List<AppUserFile> findByName(String filename);
}
