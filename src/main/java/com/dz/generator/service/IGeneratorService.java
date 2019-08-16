package com.dz.generator.service;

import com.dz.generator.model.ColumnDo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author lether
 * @since 2017-11-17
 */
public interface IGeneratorService {

    public List<Map<String,String>> findListTable();
    public List<ColumnDo> findListColumn(String tableName);

}
