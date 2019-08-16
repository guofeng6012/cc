package com.dz.generator.model;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 表信息
 * </p>
 *
 * @author lether
 * @since 2017-11-16
 */

@Data
public class ColumnDo implements Serializable{

    private static final long serialVersionUID = 1L;


    private String name;
    private String humpName;
    private String comment;
    private String jdbcType;
    private String javaType;
    private String tableName;
}
