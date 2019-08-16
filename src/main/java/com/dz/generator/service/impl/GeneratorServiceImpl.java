package com.dz.generator.service.impl;

import com.dz.generator.constant.JdbcTypeEnum;
import com.dz.generator.mapper.GeneratorMapper;
import com.dz.generator.model.ColumnDo;
import com.dz.generator.service.IGeneratorService;
import com.dz.generator.util.HumpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author lether
 * @since 2017-11-17
 */
@Service
public class GeneratorServiceImpl implements IGeneratorService {

    @Autowired
    private GeneratorMapper mapper;

    @Override
    public List<Map<String,String>> findListTable() {
        return mapper.findListTable();
    }

    @Override
    public List<ColumnDo> findListColumn(String tableName) {

        String[] tables = tableName.split("\\.");

        List<ColumnDo> listCol = mapper.findListColumn(tables);
        for (int i = 0; i < listCol.size(); i++) {
            ColumnDo columnDo =  listCol.get(i);

            columnDo.setHumpName(HumpUtil.lineToHump(columnDo.getName()));

            JdbcTypeEnum em = JdbcTypeEnum.parseOf(columnDo.getJdbcType());
            if(null != em){
                columnDo.setJavaType(em.getName());
            }
        }
        return listCol;
    }
}
