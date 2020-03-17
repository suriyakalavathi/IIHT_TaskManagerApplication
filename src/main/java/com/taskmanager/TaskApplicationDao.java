package com.taskmanager;

import java.util.List;

public interface TaskApplicationDao {

    void addOrUpdateTask(TaskApplicationPojo task);

    List<TaskApplicationPojo> getAllTask();

}
