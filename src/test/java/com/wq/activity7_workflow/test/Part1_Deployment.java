package com.wq.activity7_workflow.test;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

@SpringBootTest
public class Part1_Deployment {

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void initDeploymentBPMN(){
        //String fileName = "BPMN/Part1_Deployment.bpmn";
        //String pngName = "BPMN/Part1_Deployment.png";
        // String fileName = "BPMN/Part4_Task.bpmn";
        //String fileName = "BPMN/Part4_Task_Claim.bpmn";
        //String fileName = "BPMN/Part6_UEL_V1.bpmn";
        //String fileName = "BPMN/Part6_UEL_V2.bpmn";
        /*String fileName = "BPMN/Part6_UEL_V3.bpmn";*/
        //String fileName = "BPMN/Part7_Parallel.bpmn";
        //String fileName = "BPMN/Part7_Exclusive.bpmn";
        //String fileName = "BPMN/Part7_Inclusive.bpmn";
        String fileName = "BPMN/Part8_ProcessRuntime.bpmn";
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(fileName)
                //.addClasspathResource(pngName) //添加图片 activiti7以前的版本
                .name("ProcessRuntime任务流程")
                .deploy();

        System.out.println(deployment.getName());
    }

    @Test
    public void initDeploymentZip(){
        // String fileName = "BPMN/Part1_DeploymentV2.zip";
        InputStream fileInputStream = this.getClass().getClassLoader().getResourceAsStream("BPMN/Part1_DeploymentV2.zip");
        ZipInputStream zip = new ZipInputStream(fileInputStream);
        Deployment deployment = repositoryService.createDeployment()
                .addZipInputStream(zip)
                .name("流程部署测试ZIP")
                .deploy();
        System.out.println(deployment.getName());
    }

    @Test
    public void getDeployment(){
        List<Deployment> list = repositoryService.createDeploymentQuery().list();
        for(Deployment dep : list){
            System.out.println("Id:" + dep.getId());
            System.out.println("name:" + dep.getName());
            System.out.println("DeploymentTime:" + dep.getDeploymentTime());
            System.out.println("key:" + dep.getKey());
        }
    }
}
