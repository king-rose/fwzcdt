package com.agree.web.dsource.mapper;

import com.agree.web.dsource.domain.AesbGStdDictionaryKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * 标准数据元字典键Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
@Mapper
public interface AesbGStdDictionaryKeyMapper 
{
    /**
     * 查询标准数据元字典键
     * 
     * @param stdDictId 标准数据元字典键ID
     * @return 标准数据元字典键
     */
    public AesbGStdDictionaryKey selectAesbGStdDictionaryKeyById(String stdDictId);

    /**
     * 查询标准数据元字典键列表
     * 
     * @param aesbGStdDictionaryKey 标准数据元字典键
     * @return 标准数据元字典键集合
     */
    public List<AesbGStdDictionaryKey> selectAesbGStdDictionaryKeyList(AesbGStdDictionaryKey aesbGStdDictionaryKey);

    /**
     * 新增标准数据元字典键
     * 
     * @param aesbGStdDictionaryKey 标准数据元字典键
     * @return 结果
     */
    public int insertAesbGStdDictionaryKey(AesbGStdDictionaryKey aesbGStdDictionaryKey);

    /**
     * 修改标准数据元字典键
     * 
     * @param aesbGStdDictionaryKey 标准数据元字典键
     * @return 结果
     */
    public int updateAesbGStdDictionaryKey(AesbGStdDictionaryKey aesbGStdDictionaryKey);

    /**
     * 删除标准数据元字典键
     * 
     * @param stdDictId 标准数据元字典键ID
     * @return 结果
     */
    public int deleteAesbGStdDictionaryKeyById(String stdDictId);

    /**
     * 批量删除标准数据元字典键
     * 
     * @param stdDictIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAesbGStdDictionaryKeyByIds(String[] stdDictIds);
    /**
     * 批新增标准数据元字典键
     */
    public  int foreachAddAesbGStdDictionaryKey(List<Map<String,Object>> list);
}
