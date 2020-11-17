package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import java.io.*;
import java.util.Date;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor

public class AppUserFile extends BaseEntity implements Serializable,MultipartFile {

    private String name;
    private String type;
    private byte[] data;
    private Long userId;

    @Builder
    public AppUserFile(Long id, String name, String type, Date createdDt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.setCreatedDt(createdDt);
    }

    @Override
    public String getOriginalFilename() {
        return name;
    }

    @Override
    public String getContentType() {
        return type;
    }

    @Override
    public boolean isEmpty() {
        return data.length == 0;
    }

    @Override
    public long getSize() {
        return data.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return data;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(data);
    }

    @Override
    public void transferTo(File destFile) throws IOException, IllegalStateException {
        new FileOutputStream(destFile).write(data);
    }
}
