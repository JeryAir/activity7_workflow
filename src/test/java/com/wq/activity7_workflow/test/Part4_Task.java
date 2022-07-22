package com.wq.activity7_workflow.test;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Part4_Task {

    @Autowired
    private TaskService taskService;

    // 查询所有的代办任务
    @Test
    public void getTasks(){
        List<Task> list = taskService.createTaskQuery().list();
        for (Task tk : list){
            System.out.println("ID:" + tk.getId());
            System.out.println("Name:" + tk.getName());
            System.out.println("Assignee:" + tk.getAssignee());
        }
    }

    // 查询自己的代办任务
    @Test
    public void getTaskByAssignee(){
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee("wukong")
                .list();
        for (Task tk : list){
            System.out.println("ID:" + tk.getId());
            System.out.println("Name:" + tk.getName());
            System.out.println("Assignee:" + tk.getAssignee());
        }
    }

    // 执行任务
    @Test
    public void completeTask(){
        taskService.complete("89ea9ba8-0979-11ed-b9bb-005056c00001");
        System.out.println("------ Task任务执行成功 ------");
    }

    // 拾取任务
    @Test
    public void claimTask(){
        Task task = taskService.createTaskQuery().taskId("40283ecb-097b-11ed-8029-005056c00001").singleResult();
        taskService.claim("40283ecb-097b-11ed-8029-005056c00001", "bajie");
    }

    // 归还与交办任务
    @Test
    public void setTaskAssignee(){
        Task task = taskService.createTaskQuery().taskId("40283ecb-097b-11ed-8029-005056c00001").singleResult();
        taskService.setAssignee("40283ecb-097b-11ed-8029-005056c00001", "null"); // 归还候选任务
        taskService.setAssignee("40283ecb-097b-11ed-8029-005056c00001", "wukong"); // 交办任务
    }
}
