package com.github.aidan.mybatis.demo.dao;



import com.github.aidan.mybatis.demo.dto.DispatchingTaskDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface DispatchingTaskDoMapper {

    List<DispatchingTaskDTO> getDispatchingTaskDTOList();
}