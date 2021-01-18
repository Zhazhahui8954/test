package com.shanxijuzhi.juzhi.mapper;

import com.shanxijuzhi.juzhi.model.TestDataInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestDataInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestDataInfo record);

    int insertSelective(TestDataInfo record);

    TestDataInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestDataInfo record);

    int updateByPrimaryKey(TestDataInfo record);

    int save(List<List<Object>> list);
}