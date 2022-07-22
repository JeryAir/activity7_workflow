package com.wq.activity7_workflow.test;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Part2_ProcessDefinition {

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void getDefinitions(){
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition pd: list){
            System.out.println("------ 流程定义 ------");
            System.out.println("Name: " + pd.getName());
            System.out.println("Key: " + pd.getKey());
            System.out.println("ResourceName: " + pd.getResourceName());
            System.out.println("DeploymentId: " + pd.getDeploymentId());
            System.out.println("Version: " + pd.getVersion());
        }
    }

    @Test
    public void delDefinitions(){
        String pdID = "4c1f8d58-08f1-11ed-ab6f-005056c00001";
        repositoryService.deleteDeployment(pdID, true);
        System.out.println("删除流程定义成功！");
    }
}
