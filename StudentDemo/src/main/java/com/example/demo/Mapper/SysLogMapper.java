package com.example.demo.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Mapper
@Repository
public interface SysLogMapper {
    @Insert("insert into sys_log(name,operation,time,method,params,ip,createTime) values(#{name},#{operation},#{time},#{method},#{params},#{ip},#{createTime})")
    void insertLog(@Param("name") String name, @Param("operation") String operation, @Param("time") int time, @Param("method") String method, @Param("params") String params, @Param("ip") String ip, @Param("createTime") Date createTime);
}
