package com.wq.activity7_workflow.test;

import com.wq.activity7_workflow.SecurityUtil;
import org.activiti.api.model.shared.model.VariableInstance;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Part8_ProcessRuntime {

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private SecurityUtil securityUtil;


    // 获取流程实例
    @Test
    public void getProcessInstance(){
        securityUtil.logInAs("bajie");
        Page<ProcessInstance> processInstancePage = processRuntime.processInstances(Pageable.of(0, 100));
        System.out.println("流程实例数量：" + processInstancePage.getTotalItems());
        List<ProcessInstance> processInstanceList = processInstancePage.getContent();
        for (ProcessInstance pi : processInstanceList){
            System.out.println("ID:" + pi.getId());
            System.out.println("Name:" + pi.getName());
            System.out.println("StartDate:" + pi.getStartDate());
            System.out.println("Status:" + pi.getStatus());
            System.out.println("ProcessDefinitionId:" + pi.getProcessDefinitionId());
            System.out.println("ProcessDefinitionKey:" + pi.getProcessDefinitionKey());
        }
    }

    //启动流程实例
    @Test
    public void startProcessInstance() {
        securityUtil.logInAs("bajie");
        ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder
                .start()
                .withProcessDefinitionKey("myProcess_ProcessRuntime")
                .withName("第一个流程实例名称")
                //.withVariable("","")
                .withBusinessKey("自定义bKey")
                .build()
        );
        System.out.println("实例ID: " + processInstance.getProcessDefinitionId());
    }

    //删除流程实例
    @Test
    public void delProcessInstance() {
        securityUtil.logInAs("bajie");
        ProcessInstance processInstance = processRuntime.delete(ProcessPayloadBuilder
                .delete()
                .withProcessInstanceId("f2482de3-09c4-11ed-908a-005056c00001")
                .build()
        );
    }

    //挂起流程实例
    @Test
    public void suspendProcessInstance() {
        securityUtil.logInAs("bajie");
        ProcessInstance processInstance = processRuntime.suspend(ProcessPayloadBuilder
                .suspend()
                .withProcessInstanceId("c1708fbd-09c5-11ed-b596-005056c00001")
                .build()
        );
    }

    //激活流程实例
    @Test
    public void resumeProcessInstance() {
        securityUtil.logInAs("bajie");
        ProcessInstance processInstance = processRuntime.resume(ProcessPayloadBuilder
                .resume()
                .withProcessInstanceId("c1708fbd-09c5-11ed-b596-005056c00001")
                .build()
        );
    }

    //流程实例参数
    @Test
    public void getVariables() {
        securityUtil.logInAs("bajie");
        List<VariableInstance> list = processRuntime.variables(ProcessPayloadBuilder
                .variables()
                .withProcessInstanceId("262da1b4-09c7-11ed-9ec4-005056c00001")
                .build()
        );
        for(VariableInstance vi : list){
            System.out.println("-------------------");
            System.out.println("getName：" + vi.getName());
            System.out.println("getValue：" + vi.getValue());
            System.out.println("getTaskId：" + vi.getTaskId());
            System.out.println("getProcessInstanceId：" + vi.getProcessInstanceId());
        }
    }
}
