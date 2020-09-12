package com.finra.rest.Service;

import com.finra.rest.DAO.MetaDAO;
import com.finra.rest.Entity.MetaInfo;
import com.finra.rest.Utility.MetaConverter;
import com.finra.rest.VO.MetaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadServiceImpl implements UploadService {
    private final MetaDAO metaDAO;

    @Value("${com.finra.rest.path}")
    private String path;

    @Autowired
    public UploadServiceImpl(MetaDAO metaDAO) {
        this.metaDAO = metaDAO;
    }

    @Override
    public boolean saveFile(MultipartFile file) throws IOException {
        String fullPath = path +file.getOriginalFilename();
        boolean exists = new File(fullPath).exists();

        if(exists)
            return false;
        file.transferTo(new File(fullPath));
        return true;
    }

    @Override
    @Transactional
    public Integer saveMeta(MetaVO metaVO){
        MetaInfo meta = MetaConverter.convertVOtoDomain(metaVO);
        metaDAO.save(meta);

        return meta.getId();
    }

}
