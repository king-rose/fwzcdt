package com.agree.activiti.service;

import com.github.pagehelper.Page;
import com.agree.activiti.domain.ProcessDefinition;

/**
 * @author 一只闲鹿
 */
public interface IProcessDefinitionService {

    Page<ProcessDefinition> listProcessDefinition(ProcessDefinition processDefinition);
    void deployProcessDefinition(String filePath);
    int deleteProcessDeploymentByIds(String deploymentIds) throws Exception;
    void suspendOrActiveDefinition(String id, String suspendState);

}
