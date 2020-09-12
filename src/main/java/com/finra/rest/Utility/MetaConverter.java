package com.finra.rest.Utility;

import com.finra.rest.Entity.MetaInfo;
import com.finra.rest.VO.MetaVO;

public class MetaConverter {
    private MetaConverter() {
    }

    public static MetaInfo convertVOtoDomain(MetaVO vo) {
        MetaInfo metaInfo = new MetaInfo();
        metaInfo.setFileName(vo.getName());
        metaInfo.setCreated(vo.getCreated());
        metaInfo.setSize(vo.getSize());

        return metaInfo;
    }

}
