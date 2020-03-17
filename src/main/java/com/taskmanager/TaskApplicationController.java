package com.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/tasks")
public class TaskApplicationController {

    @Autowired
    private TaskApplicationServiceImpl taskManagerService;

    private String status = null;

    @RequestMapping(method = RequestMethod.POST)
    public String createTask(@RequestBody TaskApplicationJSONObj taskJSONData) {
        TaskApplicationPojo taskData = this.updateTaskFromTaskJSON(taskJSONData);
        taskData.setStatus("Active");
        status = taskManagerService.addOrUpdateTask(taskData);
        return status;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TaskApplicationJSONObj> getTaskList(){
        List<TaskApplicationJSONObj> taskManageJSONList = new ArrayList<TaskApplicationJSONObj>();
        List<TaskApplicationPojo> listOfTask = taskManagerService.getTaskList();

        for (TaskApplicationPojo taskData: listOfTask){
            TaskApplicationJSONObj taskJSONData = new TaskApplicationJSONObj();
            taskJSONData.setTaskId(taskData.getTaskId());
            taskJSONData.setTaskName(taskData.getTaskName());
            taskJSONData.setPriority(taskData.getPriority());
            taskJSONData.setStartDate(taskData.getStartDate());
            taskJSONData.setEndDate(taskData.getEndDate());
            taskJSONData.setParentTaskName(taskData.getParentTask());
            taskJSONData.setStatus(taskData.getStatus());
            taskManageJSONList.add(taskJSONData);
        }
        return taskManageJSONList;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String updateTask(@RequestBody TaskApplicationJSONObj taskJSONData) {
       TaskApplicationPojo taskData = this.updateTaskFromTaskJSON(taskJSONData);
        if (taskJSONData.getEndTask() != null && taskJSONData.getEndTask().equalsIgnoreCase("yes")) {
            taskData.setStatus("InActive");
        }
        else {
            taskData.setStatus("Active");
        }
        status = taskManagerService.addOrUpdateTask(taskData);
        return status;
    }

    private TaskApplicationPojo updateTaskFromTaskJSON(TaskApplicationJSONObj taskJSONData) {
        TaskApplicationPojo taskObj = new TaskApplicationPojo();
        taskObj.setTaskId(taskJSONData.getTaskId());
        taskObj.setTaskName(taskJSONData.getTaskName());
        taskObj.setParentTask(taskJSONData.getParentTaskName());
        java.sql.Date sqlStartDate = new java.sql.Date(taskJSONData.getStartDate().getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(taskJSONData.getEndDate().getTime());
        taskObj.setStartDate(sqlStartDate);
        taskObj.setEndDate(sqlEndDate);
        taskObj.setPriority(taskJSONData.getPriority());
        return taskObj;
    }
}