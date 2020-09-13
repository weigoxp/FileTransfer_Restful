package com.finra.rest.Controller;

import com.finra.rest.Utility.UploadFileException;
import com.finra.rest.Utility.UploadFileResponse;
import com.finra.rest.Utility.FileNotExistException;
import com.finra.rest.Utility.FileNotExistResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String error(Exception e) {
        logger.error("Exception ", e);
        return "redirect:/server_error";
    }


    @ExceptionHandler
    public ResponseEntity<UploadFileResponse> handleDuplicateFileException(UploadFileException e) {
        UploadFileResponse response = new UploadFileResponse();

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setMessage(e.getMessage());
        response.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<FileNotExistResponse> handleFileNotFoundException(FileNotExistException e) {
        FileNotExistResponse response = new FileNotExistResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(e.getMessage());
        response.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}