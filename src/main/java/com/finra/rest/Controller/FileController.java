package com.finra.rest.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finra.rest.Entity.MetaInfo;
import com.finra.rest.Service.DownloadService;
import com.finra.rest.Service.UploadService;
import com.finra.rest.Utility.FileNotExistException;
import com.finra.rest.Utility.UploadFileException;
import com.finra.rest.VO.MetaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController

public class FileController {

    private final UploadService uploadService;
    private final ObjectMapper objectMapper;
    private final DownloadService downloadService;

    @Autowired
    public FileController(UploadService uploadService, ObjectMapper objectMapper, DownloadService downloadService) {
        this.uploadService = uploadService;
        this.objectMapper = objectMapper;
        this.downloadService = downloadService;
    }
    // upload file and metadata
    @PostMapping("/file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                     @RequestParam("meta")String meta) throws IOException {

        if(file.isEmpty()) throw new UploadFileException("file must not be empty");

        if(!uploadService.saveFile(file)) throw new UploadFileException("file with same name aleady exists");

        MetaVO metaVO = objectMapper.readValue(meta, MetaVO.class);
        Integer fileID = uploadService.saveMeta(metaVO);
        return new ResponseEntity<>("file upload success, file id: " + fileID,HttpStatus.OK);

    }

    // get metadata by id
    @GetMapping("meta/{fileId}")
    public ResponseEntity<MetaInfo> getMetaData(@PathVariable("fileId") Integer id) {
        MetaInfo metaInfo = downloadService.findMetaByID(id);
        if(metaInfo ==null)
            throw new FileNotExistException("Requested meta info not found");

        return new ResponseEntity<>(metaInfo ,HttpStatus.OK);
    }

    // get file content by id
    @GetMapping("file/{fileId}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable("fileId") Integer id) {
        MetaInfo metaInfo = downloadService.findMetaByID(id);
        if(metaInfo ==null)
            throw new FileNotExistException("Requested file not found");

        File file = downloadService.getFileByName(metaInfo.getFileName());

        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new FileNotExistException("Requested file not found");
        }

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+metaInfo.getFileName());

        return ResponseEntity.ok()
                .headers(header)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    // get file ids which has size greater than **
    @GetMapping("file")
    public ResponseEntity<List<Integer>> getFileIdsGreaterThan(@RequestParam("sizeGreater") Long size) {

        List<MetaInfo> list = downloadService.getFilesGreaterThan(size);

        return new ResponseEntity<>(list.stream().map(i->i.getId()).collect(Collectors.toList()), HttpStatus.OK);
    }
}