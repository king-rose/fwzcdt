package com.agree.activiti.service;

import java.util.List;
import com.agree.activiti.domain.BizSimpleTest;

/**
 * 请假测试Service接口
 * 
 * @author luoxu
 * @date 2022-04-24
 */
public interface IBizSimpleTestService 
{
    /**
     * 查询请假测试
     * 
     * @param id 请假测试ID
     * @return 请假测试
     */
    public BizSimpleTest selectBizSimpleTestById(Long id);

    /**
     * 查询请假测试列表
     * 
     * @param bizSimpleTest 请假测试
     * @return 请假测试集合
     */
    public List<BizSimpleTest> selectBizSimpleTestList(BizSimpleTest bizSimpleTest);

    /**
     * 新增请假测试
     * 
     * @param bizSimpleTest 请假测试
     * @return 结果
     */
    public int insertBizSimpleTest(BizSimpleTest bizSimpleTest);

    /**
     * 修改请假测试
     * 
     * @param bizSimpleTest 请假测试
     * @return 结果
     */
    public int updateBizSimpleTest(BizSimpleTest bizSimpleTest);

    /**
     * 批量删除请假测试
     * 
     * @param ids 需要删除的请假测试ID
     * @return 结果
     */
    public int deleteBizSimpleTestByIds(Long[] ids);

    /**
     * 删除请假测试信息
     * 
     * @param id 请假测试ID
     * @return 结果
     */
    public int deleteBizSimpleTestById(Long id);
}
