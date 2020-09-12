package com.finra.rest.Service;

import com.finra.rest.Entity.MetaInfo;

import java.io.File;

public interface DownloadService {
     MetaInfo findMetaByID(Integer id);
     File getFileByName(String fileName);
}