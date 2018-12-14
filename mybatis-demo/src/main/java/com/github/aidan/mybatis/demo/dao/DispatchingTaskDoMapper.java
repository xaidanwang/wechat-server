package com.github.aidan.mybatis.demo.dao;



import com.github.aidan.mybatis.demo.dto.DispatchingTaskDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DispatchingTaskDoMapper {


    List<DispatchingTaskDTO> getDispatchingTaskDTOList();
}