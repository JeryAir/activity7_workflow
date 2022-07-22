package com.wq.activity7_workflow.test;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class Part7_Gateway {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Test
    public void initParallelProcessInstanceWithArgs(){
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_Parallel", "bKey010");
        System.out.println("流程实例ID:" + processInstance.getProcessDefinitionId());
    }

    @Test
    public void completeParallelTesk(){
        taskService.complete("07bb2258-09ad-11ed-943e-005056c00001");
        System.out.println("------ Task任务执行成功 ------");
    }

    @Test
    public void initExclusiveProcessInstanceWithArgs(){
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_Exclusive", "bKey011");
        System.out.println("流程实例ID:" + processInstance.getProcessDefinitionId());
    }

    @Test
    public void completeExclusiveTesk(){
        Map<String, Object> variable = new HashMap<>();
        variable.put("day", "100");
        taskService.complete("721ca571-09af-11ed-b88f-005056c00001", variable);
        System.out.println("------ Task任务执行成功 ------");
    }

    @Test
    public void initInclusiveProcessInstanceWithArgs(){
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_Inclusive", "bKey012");
        System.out.println("流程实例ID:" + processInstance.getProcessDefinitionId());
    }

    @Test
    public void completeInclusiveTesk(){
        Map<String, Object> variable = new HashMap<>();
        variable.put("day", "2");
        taskService.complete("5dc77f9c-09b1-11ed-94a4-005056c00001", variable);
        System.out.println("------ Task任务执行成功 ------");
    }
}
