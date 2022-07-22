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
public class Part6_UEL {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    // 启动流程实例参数，执行执行人
    @Test
    public void initProcessInstanceWithArgs(){
        // 流程变量
        Map<String, Object> variables = new HashMap<>();
        variables.put("ZhiXingRen", "wukong");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_UEL_V1", "bKey004", variables);
        System.out.println("流程实例ID:" + processInstance.getProcessInstanceId());
    }

    // 完成任务带参数
    @Test
    public void completeTaskWithArgs(){
        // 流程变量
        Map<String, Object> variables = new HashMap<>();
        variables.put("pay", "101");
        taskService.complete("f07f379f-0986-11ed-8de0-005056c00001", variables);
        System.out.println("------ 任务完成 ------");
    }

    // 启动流程实例带参数 使用实体类
    @Test
    public void initProcessInstanceWithClasssArgs(){
        UEL_POJO uel_pojo = new UEL_POJO("bajie");

        Map<String, Object> variables = new HashMap<>();
        variables.put("uelpojo", uel_pojo);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_UEL_V3", "bKey007", variables);
        System.out.println("流程实例ID:" + processInstance.getProcessInstanceId());

    }

    // 启动流程实例带参数 指定多个候选人
    @Test
    public void initProcessInstanceWithCandiDateArgs(){
        // 流程变量
        Map<String, Object> variables = new HashMap<>();
        variables.put("houxuanren", "wukong,tangseng");
        taskService.complete("55ae0291-0989-11ed-92ba-005056c00001", variables);
        System.out.println("------ 任务完成 ------");
    }

    // 直接指定流程变量
    @Test
    public void otherArgs(){
        runtimeService.setVariable("f07f379f-0986-11ed-8de0-005056c00001", "pay", "101");
        //runtimeService.setVariables("", Map);
        //taskService.setVariable();
        //taskService.setVariables();
    }

    // 局部变量
    @Test
    public void otherLocalArgs(){
        runtimeService.setVariableLocal("f07f379f-0986-11ed-8de0-005056c00001", "pay", "101");
        //runtimeService.setVariablesLocal();
        //taskService.setVariableLocal();
        //taskService.setVariablesLocal();
    }
}
