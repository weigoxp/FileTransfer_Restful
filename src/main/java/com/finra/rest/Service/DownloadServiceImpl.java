package com.finra.rest.Service;

import com.finra.rest.DAO.MetaDAO;
import com.finra.rest.Entity.MetaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;

@Service
public class DownloadServiceImpl implements DownloadService {

    private final MetaDAO metaDAO;

    @Value("${com.finra.rest.path}")
    private String path;

    @Autowired
    public DownloadServiceImpl(MetaDAO metaDAO) {
        this.metaDAO = metaDAO;
    }

    @Override
    @Transactional
    public MetaInfo findMetaByID(Integer id){
        MetaInfo meta = metaDAO.findMetaInfoById(id);

        return meta;
    }

    @Override
    public File getFileByName(String fileName) {
        String fullPath = path + fileName;

        return new File(fullPath);
    }
}
