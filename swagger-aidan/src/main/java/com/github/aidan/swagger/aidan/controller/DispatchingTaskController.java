package com.github.aidan.swagger.aidan.controller;


import com.github.aidan.swagger.aidan.bean.DispatchingTaskRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
public class DispatchingTaskController {


    @ApiOperation(value = "添加或更新布控任务", httpMethod = "POST", notes = "添加或更新布控任务")
    @RequestMapping(value = "/dispatching/task", method = RequestMethod.POST)
    public String postDispatchingTask(@RequestParam(value = "taskuuid",required = false) String taskuuid, @RequestBody DispatchingTaskRequest dispatchingTaskRequest) {
        return null;
    }
}
