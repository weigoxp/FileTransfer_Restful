package com.finra.rest;

import com.finra.rest.Service.DownloadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DownloadServiceTest {

    DownloadService downloadService;

    @Autowired
    public DownloadServiceTest(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    @Test
    void testDownloadService1() {
        File file = downloadService.getFileByName("");
        assertTrue(file != null);
    }
    @Test
    void testDownloadService2() {
        assertTrue (downloadService.findMetaByID(1) == null);
    }


}
