package com.finra.rest.DAO;

import com.finra.rest.Entity.MetaInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetaDAO extends JpaRepository<MetaInfo,Integer> {

    MetaInfo findMetaInfoById(Integer id);
    List<MetaInfo> findIdBySizeGreaterThan(Long size);
}
