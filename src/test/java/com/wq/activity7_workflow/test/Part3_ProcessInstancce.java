package com.wq.activity7_workflow.test;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Part3_ProcessInstancce {

    @Autowired
    private RuntimeService runtimeService;

    @Test
    public void initProcessInstance(){
        // 1、获取页面表单填报的内容，请假时间，请见是由，String fromData
        // 2、 fromData 写入业务表，返回业务表主键ID == businessKey
        // 3、把业务数据与Activiti7流程数据关联
        //ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_Part1", "bKey001");
        //ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_claim", "bKey003");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_UEL_V2", "bKey006");
        System.out.println("流程实例ID:" + processInstance.getProcessInstanceId());
    }

    @Test
    public void getProcessInstance(){

        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();
        for (ProcessInstance pi : list){
            System.out.println("------ 流程实例 ------");
            System.out.println("ProcessInstanceId:" + pi.getProcessInstanceId());
            System.out.println("ProcessDefinitionId:" + pi.getProcessDefinitionId());
            System.out.println("isEnded:" + pi.isEnded());
            System.out.println("isSuspended:" + pi.isSuspended());
        }
    }

    // 暂停与激活流程实例
    @Test
    public void activitieProcessInstance(){
        // 挂起流程实例
        runtimeService.suspendProcessInstanceById("02ab3b4a-0960-11ed-bbea-005056c00001");
        System.out.println("----- 挂起流程实例 -----");
        // 激活流程实例
        runtimeService.activateProcessInstanceById("02ab3b4a-0960-11ed-bbea-005056c00001");
        System.out.println("----- 激活流程实例 -----");
    }

    @Test
    public void deleteProcessInstance(){
        runtimeService.deleteProcessInstance("8067d597-0977-11ed-b2b4-005056c00001", "测试删除...");
        System.out.println("----- 删除流程实例 -----");
    }
}
