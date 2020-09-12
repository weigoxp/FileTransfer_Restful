package com.finra.rest.Service;

import com.finra.rest.VO.MetaVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
     boolean saveFile(MultipartFile file) throws IOException;
     Integer saveMeta(MetaVO metaVO) throws IOException;

}
