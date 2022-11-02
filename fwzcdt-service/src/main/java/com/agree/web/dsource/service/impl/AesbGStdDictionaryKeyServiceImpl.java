package com.agree.web.dsource.service.impl;

import com.agree.common.utils.DateUtils;
import com.agree.common.utils.uuid.IdUtils;
import com.agree.web.dsource.domain.AesbGStdDictionaryKey;
import com.agree.web.dsource.mapper.AesbGStdDictionaryKeyMapper;
import com.agree.web.dsource.service.IAesbGStdDictionaryKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 标准数据元字典键Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
@Service
public class AesbGStdDictionaryKeyServiceImpl implements IAesbGStdDictionaryKeyService
{
    @Autowired
    private AesbGStdDictionaryKeyMapper aesbGStdDictionaryKeyMapper;

    /**
     * 查询标准数据元字典键
     * 
     * @param stdDictId 标准数据元字典键ID
     * @return 标准数据元字典键
     */
    @Override
    public AesbGStdDictionaryKey selectAesbGStdDictionaryKeyById(String stdDictId)
    {
        return aesbGStdDictionaryKeyMapper.selectAesbGStdDictionaryKeyById(stdDictId);
    }

    /**
     * 查询标准数据元字典键列表
     * 
     * @param aesbGStdDictionaryKey 标准数据元字典键
     * @return 标准数据元字典键
     */
    @Override
    public List<AesbGStdDictionaryKey> selectAesbGStdDictionaryKeyList(AesbGStdDictionaryKey aesbGStdDictionaryKey)
    {
        return aesbGStdDictionaryKeyMapper.selectAesbGStdDictionaryKeyList(aesbGStdDictionaryKey);
    }

    /**
     * 新增标准数据元字典键
     * 
     * @param aesbGStdDictionaryKey 标准数据元字典键
     * @return 结果
     */
    @Override
    public int insertAesbGStdDictionaryKey(AesbGStdDictionaryKey aesbGStdDictionaryKey)
    {
        aesbGStdDictionaryKey.setStdDictId(IdUtils.fastUUID());
        aesbGStdDictionaryKey.setCrtTime(DateUtils.getNowDate());
        return aesbGStdDictionaryKeyMapper.insertAesbGStdDictionaryKey(aesbGStdDictionaryKey);
    }

    /**
     * 修改标准数据元字典键
     * 
     * @param aesbGStdDictionaryKey 标准数据元字典键
     * @return 结果
     */
    @Override
    public int updateAesbGStdDictionaryKey(AesbGStdDictionaryKey aesbGStdDictionaryKey)
    {
        return aesbGStdDictionaryKeyMapper.updateAesbGStdDictionaryKey(aesbGStdDictionaryKey);
    }

    /**
     * 批量删除标准数据元字典键
     * 
     * @param stdDictIds 需要删除的标准数据元字典键ID
     * @return 结果
     */
    @Override
    public int deleteAesbGStdDictionaryKeyByIds(String[] stdDictIds)
    {
        return aesbGStdDictionaryKeyMapper.deleteAesbGStdDictionaryKeyByIds(stdDictIds);
    }

    /**
     * 删除标准数据元字典键信息
     * 
     * @param stdDictId 标准数据元字典键ID
     * @return 结果
     */
    @Override
    public int deleteAesbGStdDictionaryKeyById(String stdDictId)
    {
        return aesbGStdDictionaryKeyMapper.deleteAesbGStdDictionaryKeyById(stdDictId);
    }

    @Override
    public int foreachAddAesbGStdDictionaryKey(List<Map<String,Object>> list) {
        int row = aesbGStdDictionaryKeyMapper.foreachAddAesbGStdDictionaryKey(list);
        if (row==-1){
            return 1;
        }
        return 0;
    }
}
