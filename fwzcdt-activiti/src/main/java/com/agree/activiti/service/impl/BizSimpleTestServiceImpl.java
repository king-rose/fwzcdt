package com.agree.activiti.service.impl;

import java.util.List;
import com.agree.common.utils.DateUtils;
import com.agree.common.utils.SecurityUtils;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.agree.activiti.mapper.BizSimpleTestMapper;
import com.agree.activiti.domain.BizSimpleTest;
import com.agree.activiti.service.IBizSimpleTestService;

/**
 * 请假测试Service业务层处理
 * 
 * @author luoxu
 * @date 2022-04-24
 */
@Service
public class BizSimpleTestServiceImpl implements IBizSimpleTestService 
{
    @Autowired
    private BizSimpleTestMapper bizSimpleTestMapper;

    /**
     * 查询请假测试
     * 
     * @param id 请假测试ID
     * @return 请假测试
     */
    @Override
    public BizSimpleTest selectBizSimpleTestById(Long id)
    {
        return bizSimpleTestMapper.selectBizSimpleTestById(id);
    }

    /**
     * 查询请假测试列表
     * 
     * @param bizSimpleTest 请假测试
     * @return 请假测试
     */
    @Override
    public List<BizSimpleTest> selectBizSimpleTestList(BizSimpleTest bizSimpleTest)
    {
        return bizSimpleTestMapper.selectBizSimpleTestList(bizSimpleTest);
    }

    /**
     * 新增请假测试
     * 
     * @param bizSimpleTest 请假测试
     * @return 结果
     */
    @Override
    public int insertBizSimpleTest(BizSimpleTest bizSimpleTest)
    {
        bizSimpleTest.setCreateTime(DateUtils.getNowDate());
        return bizSimpleTestMapper.insertBizSimpleTest(bizSimpleTest);
    }

    /**
     * 修改请假测试
     * 
     * @param bizSimpleTest 请假测试
     * @return 结果
     */
    @Override
    public int updateBizSimpleTest(BizSimpleTest bizSimpleTest)
    {
        bizSimpleTest.setUpdateBy(SecurityUtils.getUsername());
        bizSimpleTest.setUpdateTime(DateUtils.getNowDate());
        return bizSimpleTestMapper.updateBizSimpleTest(bizSimpleTest);
    }

    /**
     * 批量删除请假测试
     * 
     * @param ids 需要删除的请假测试ID
     * @return 结果
     */
    @Override
    public int deleteBizSimpleTestByIds(Long[] ids)
    {
        return bizSimpleTestMapper.deleteBizSimpleTestByIds(ids);
    }

    /**
     * 删除请假测试信息
     * 
     * @param id 请假测试ID
     * @return 结果
     */
    @Override
    public int deleteBizSimpleTestById(Long id)
    {
        return bizSimpleTestMapper.deleteBizSimpleTestById(id);
    }
}
