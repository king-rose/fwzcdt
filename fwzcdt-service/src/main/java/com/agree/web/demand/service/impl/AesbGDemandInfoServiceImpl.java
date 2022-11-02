package com.agree.web.demand.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.agree.common.core.domain.entity.SysRole;
import com.agree.common.utils.Arith;
import com.agree.common.utils.DateUtils;
import com.agree.common.utils.SecurityUtils;
import com.agree.web.demand.domain.AesbGDemandInfo;
import com.agree.web.demand.domain.AesbSystemInterfaceDemand;
import com.agree.web.demand.mapper.AesbGDemandInfoMapper;
import com.agree.web.demand.service.IAesbGDemandInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 需求信息Service业务层处理
 * 
 * @author luoxu
 * @date 2022-08-15
 */
@Service
@AllArgsConstructor
public class AesbGDemandInfoServiceImpl implements IAesbGDemandInfoService
{
    private AesbGDemandInfoMapper aesbGDemandInfoMapper;

    /**
     * 查询需求信息
     * 
     * @param demandId 需求信息ID
     * @return 需求信息
     */
    @Override
    public AesbGDemandInfo selectAesbGDemandInfoById(String demandId) {

        AesbGDemandInfo demandInfo = aesbGDemandInfoMapper.selectAesbGDemandInfoById(demandId);

        List<SysRole> roles = SecurityUtils.getLoginUser().getUser().getRoles();
        //是否包含审批角色
        for (SysRole role : roles) {
            if (role.getRoleId()==6 || role.getRoleId()==1){
                List<AesbSystemInterfaceDemand> list = aesbGDemandInfoMapper.selectInterfaceDemandById(demandId,"");
                if (!list.isEmpty()){
                    demandInfo.setList(list);
                }
                return demandInfo;
            }
        }
        List<AesbSystemInterfaceDemand> list = aesbGDemandInfoMapper.selectInterfaceDemandById(demandId,SecurityUtils.getUsername());
        if (!list.isEmpty()){
            demandInfo.setList(list);
        }

        return demandInfo;
    }

    /**
     * 查询需求信息列表
     * 
     * @param aesbGDemandInfo 需求信息
     * @return 需求信息
     */
    @Override
    public List<AesbGDemandInfo> selectAesbGDemandInfoList(AesbGDemandInfo aesbGDemandInfo)
    {
        if (!SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUser().getUserId())){
            //查询关联接口信息
            List<AesbGDemandInfo> gDemandInfoList = aesbGDemandInfoMapper.selectFaceDemandListByPrincipal(SecurityUtils.getUsername());
            aesbGDemandInfo.setApplyUserName(SecurityUtils.getUsername());
            aesbGDemandInfo.setDemandState("1");
            aesbGDemandInfo.setTaskState("0");
            List<AesbGDemandInfo> list = aesbGDemandInfoMapper.selectAesbGDemandInfoList(aesbGDemandInfo);
            list.addAll(gDemandInfoList);
            return  list;
        }
        List<AesbGDemandInfo> list = aesbGDemandInfoMapper.selectAesbGDemandInfoList(aesbGDemandInfo);
       /* if (!SecurityUtils.isAdmin(SecurityUtils.getLoginUser().getUser().getUserId())){
            for (int i = 0; i < list.size(); i++) {
                List<AesbSystemInterfaceDemand> interfaceDemands = aesbGDemandInfoMapper.selectInterfaceDemandById(list.get(i).getDemandId(), SecurityUtils.getUsername());
                if (interfaceDemands.isEmpty()){
                    list.remove(list.get(i));
                    i-=1;
                }
            }
        }*/
        return  list;
    }

    /**
     * 新增需求信息
     * @param aesbGDemandInfo 需求信息
     * @return 结果 1:已插入
     */
    public int insertAesbGDemandInfo(AesbGDemandInfo aesbGDemandInfo)
    {
        aesbGDemandInfo.setApplyUserName(SecurityUtils.getUsername());
        return aesbGDemandInfoMapper.insertAesbGDemandInfo(aesbGDemandInfo);
    }

    /**
     * 修改需求信息
     * 
     * @param aesbGDemandInfo 需求信息
     * @return 结果
     */
    @Override
    public int updateAesbGDemandInfo(AesbGDemandInfo aesbGDemandInfo)
    {
        return aesbGDemandInfoMapper.updateAesbGDemandInfo(aesbGDemandInfo);
    }

    /**
     * 批量删除需求信息
     * 
     * @param demandIds 需要删除的需求信息ID
     * @return 结果
     */
    @Override
    public int deleteAesbGDemandInfoByIds(String demandIds)
    {
        return aesbGDemandInfoMapper.deleteAesbGDemandInfoByIds(demandIds);
    }

    /**
     * 删除需求信息信息
     * 
     * @param demandId 需求信息ID
     * @return 结果
     */
    public int deleteAesbGDemandInfoById(String demandId)
    {
        return aesbGDemandInfoMapper.deleteAesbGDemandInfoById(demandId);
    }

    public int insertAssociationDemand(AesbSystemInterfaceDemand systemInterfaceDemand) {
        systemInterfaceDemand.setId(Long.parseLong(DateUtils.dateTimeNow()));
        systemInterfaceDemand.setCreateBy(SecurityUtils.getUsername());
        systemInterfaceDemand.setCreateTime(DateUtils.getNowDate());
        //新增关联数据
        return aesbGDemandInfoMapper.insertAssociationDemand(systemInterfaceDemand);
    }

    public int deleteAssociationDemand(String demandId) {
        return aesbGDemandInfoMapper.deleteAssociationDemand(demandId);
    }

    public PageInfo<AesbGDemandInfo> selectMyTaskList(AesbGDemandInfo demandInfo,Integer pageNum,Integer pageSize ) {

        PageInfo<AesbGDemandInfo> StudentVoList= PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(()->{
            aesbGDemandInfoMapper.selectMyTaskList(demandInfo);
        });
        // 需要转换的对象
        PageInfo<AesbGDemandInfo> target = new PageInfo<>();
        // 对查询的list进行下一步操作，比如类型转换后
        List<AesbGDemandInfo > collect = StudentVoList.getList().stream().collect(Collectors.toList());
        List<AesbGDemandInfo> newList = new ArrayList<>();
        for (AesbGDemandInfo aesbGDemandInfo : collect) {
            List<AesbSystemInterfaceDemand> interfaceDemands = aesbGDemandInfoMapper.selectInterfaceDemandById( aesbGDemandInfo.getDemandId(),SecurityUtils.getUsername());
            if (interfaceDemands.isEmpty()) {
                continue;
            }
            newList.add(aesbGDemandInfo);
        }
        // 加工后的数据放入新的pageinfo
        target.setList(newList);
        target.setTotal(newList.size());
        target.setSize(newList.size());
        target.setPageNum(pageNum);
        target.setSize(pageSize);
        return target;
    }

    public int updateSystemInterFaceInfo(AesbSystemInterfaceDemand aesbSystemInterfaceDemand) {
        return aesbGDemandInfoMapper.updateSystemInterFaceInfo(aesbSystemInterfaceDemand);
    }

    public List<AesbGDemandInfo> selectDemandAcceptList(AesbGDemandInfo aesbGDemandInfo) {
        List<AesbGDemandInfo> list = aesbGDemandInfoMapper.selectDemandAcceptList(aesbGDemandInfo);

        for (int i = 0; i < list.size(); i++) {
            //查询关联列表
            List<AesbSystemInterfaceDemand> interfaceDemands = aesbGDemandInfoMapper.selectInterfaceDemandById( list.get(i).getDemandId(),"");
            if (interfaceDemands.isEmpty()) {
                list.remove(list.get(i));
                i-=1;
            }
        }
        return list;
    }

    public List<Map<String, Object>> getTaskProcess(AesbGDemandInfo demandInfo, Integer pageNum,Integer pageSize) {
        List<Map<String,Object>> datas = new ArrayList<>();
        demandInfo.setParams((Map<String, Object>) new HashMap<>().put("orderBy","ASC"));
        PageInfo<AesbGDemandInfo> StudentVoList= PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(()->{
            aesbGDemandInfoMapper.selectMyTaskList(demandInfo);
        });
        // 对查询的list进行下一步操作，比如类型转换后
        List<AesbGDemandInfo > collect = StudentVoList.getList().stream().collect(Collectors.toList());
        List<AesbGDemandInfo> newList = new ArrayList<>();
        for (AesbGDemandInfo aesbGDemandInfo : collect) {
            List<AesbSystemInterfaceDemand> interfaceDemands = aesbGDemandInfoMapper.selectInterfaceDemandById( aesbGDemandInfo.getDemandId(),SecurityUtils.getUsername());
            if (interfaceDemands.isEmpty()) {
                continue;
            }
            newList.add(aesbGDemandInfo);
        }
        for (AesbGDemandInfo aesbGDemandInfo : newList) {
            Map<String,Object> map = new HashMap<>();
            int num = 0;
            List<AesbSystemInterfaceDemand> interfaceDemands = aesbGDemandInfoMapper.selectInterfaceDemandById( aesbGDemandInfo.getDemandId(),SecurityUtils.getUsername());
            for (AesbSystemInterfaceDemand interfaceDemand : interfaceDemands) {
                if (interfaceDemand.getIsAccept().equals("1")){
                    num++;
                }
            }
            String demandName = aesbGDemandInfo.getDemandName();
            map.put("xqmc", demandName);
            if (aesbGDemandInfo.getTaskState().equals("1")||aesbGDemandInfo.getTaskState().equals("4")){//已派送
                String percentStr = Arith.getPercentStr(num, interfaceDemands.size());
                map.put("pswsl", 100);
                map.put("psysl", percentStr);
            }
            if (aesbGDemandInfo.getTaskState().equals("2") && aesbGDemandInfo.getDemandState().equals("1")){//已受理
                map.put("pswsl", 100);
                map.put("psysl", 100);
                map.put("psz", 60);
            }
            if (aesbGDemandInfo.getTaskState().equals("3") && aesbGDemandInfo.getDemandState().equals("2")){//开发中
                map.put("pswsl", 100);
                map.put("psysl", 100);
                map.put("psz", 100);
                //受理时间到当前时间的天数
                int day = DateUtils.getDays(interfaceDemands.get(0).getAcceptTime(),DateUtils.getNowDate());
                String maxWorkDay = aesbGDemandInfoMapper.getMaxWorkDay(aesbGDemandInfo.getDemandId(), SecurityUtils.getUsername());
                String percentStr = Arith.getPercentStr(day,Integer.parseInt(maxWorkDay));
                map.put("kfz", percentStr);
            }
            datas.add(map);
        }
        return datas;
    }

    @Override
    public int downSystemInterFaceInfo(AesbSystemInterfaceDemand aesbSystemInterfaceDemand) {
        return aesbGDemandInfoMapper.downSystemInterFaceInfo(aesbSystemInterfaceDemand);
    }

}
