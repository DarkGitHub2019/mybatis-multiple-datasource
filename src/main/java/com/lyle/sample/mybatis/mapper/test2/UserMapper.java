package com.lyle.sample.mybatis.mapper.test2;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 查询记录条数
     *
     * @return 记录条数
     */
    Long queryTotalCount();
}
