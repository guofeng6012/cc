package com.dz.generator.mapper;

import com.dz.generator.model.ColumnDo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 用户信息表 Mapper 接口
 * </p>
 *
 * @author lether
 * @since 2017-11-17
 */
public interface GeneratorMapper {

    public List<Map<String,String>> findListTable();

    public List<ColumnDo> findListColumn(String[] tableNames);

}