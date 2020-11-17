package com.udacity.jwdnd.course1.cloudstorage.exception;

import lombok.Getter;
import lombok.Setter;

public class FileStorageException extends Throwable {

    @Getter
    @Setter
    private String errorMsg;

    public FileStorageException(String s) {
        this.errorMsg = s;
    }
}
