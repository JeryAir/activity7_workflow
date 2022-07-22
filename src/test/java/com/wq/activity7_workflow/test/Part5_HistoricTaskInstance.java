package com.wq.activity7_workflow.test;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Part5_HistoricTaskInstance {

    @Autowired
    private HistoryService historyService;

    // 根据用户名查询历史数据
    @Test
    public void getHistoricTaskInstanceByUser(){
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .orderByHistoricTaskInstanceEndTime()
                .asc()
                .taskAssignee("bajie")
                .list();
        for (HistoricTaskInstance hti : list){
            System.out.println("ID:" + hti.getId());
            System.out.println("ProcessInstanceId:" + hti.getProcessInstanceId());
            System.out.println("Name:" + hti.getName());
        }
    }

    // 根据流程实例ID查询历史数据
    @Test
    public void getHistoricTaskInstanceByPiID(){
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .orderByHistoricTaskInstanceEndTime()
                .asc()
                .processInstanceId("8067d597-0977-11ed-b2b4-005056c00001")
                .list();
        for (HistoricTaskInstance hti : list){
            System.out.println("ID:" + hti.getId());
            System.out.println("ProcessInstanceId:" + hti.getProcessInstanceId());
            System.out.println("Name:" + hti.getName());
        }
    }
}
