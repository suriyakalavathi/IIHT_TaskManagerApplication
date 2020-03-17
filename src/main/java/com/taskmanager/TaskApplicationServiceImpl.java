package com.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TaskApplicationService")

public class TaskApplicationServiceImpl implements TaskApplicationService {

    @Autowired
    TaskApplicationDao taskDaoImpl;

    public String addOrUpdateTask(TaskApplicationPojo taskData) {
        taskDaoImpl.addOrUpdateTask(taskData);
        return "Success";
    }

    public List<TaskApplicationPojo> getTaskList() {
        List<TaskApplicationPojo> taskList;
        taskList = taskDaoImpl.getAllTask();
        return taskList;
    }

}