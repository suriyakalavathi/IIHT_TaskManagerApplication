package com.taskmanager;

import java.util.List;
/**
 * Service class for task manager
 * @author skalimuthu
 *
 */
public interface TaskApplicationService {
    String addOrUpdateTask(TaskApplicationPojo taskData);
    List<TaskApplicationPojo> getTaskList();
}
