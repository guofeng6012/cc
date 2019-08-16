package com.dz.generator.util;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.dz.generator.dto.GeneratorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@EnableConfigurationProperties(DataSourceProperties.class)
public class MybatisPlusGenerator {


    @Autowired
    private DataSourceProperties dataSourceProperties;

    public void generatorTpl(GeneratorDto generatorDto){


//
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        gc.setOutputDir(generatorDto.getOutputDir());//这里写你自己的java目录
//        gc.setFileOverride(true);//是否覆盖
//        gc.setActiveRecord(true);
//        gc.setEnableCache(false);// XML 二级缓存
//        gc.setBaseResultMap(true);// XML ResultMap
//        gc.setBaseColumnList(true);// XML columList
//        gc.setAuthor(generatorDto.getAuthor());
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setDbType(DbType.MYSQL);
//        dsc.setTypeConvert(new MySqlTypeConvert() {
//            // 自定义数据库表字段类型转换【可选】
//            @Override
//            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
//                return super.processTypeConvert(globalConfig,fieldType);
//            }
//        });
//        dsc.setDriverName(dataSourceProperties.getDriverClassName());
//        dsc.setUsername(dataSourceProperties.getUsername());
//        dsc.setPassword(dataSourceProperties.getPassword());
//        dsc.setUrl(dataSourceProperties.getUrl());
//        mpg.setDataSource(dsc);
//
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setTablePrefix(new String[]{"T_","C_"});// 此处可以修改为您的表前缀
//        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
//        String[] tables = generatorDto.getTableNames().split("\\.");
//        strategy.setInclude(tables);
//        // 自定义 service 父类
////        strategy.setSuperServiceClass("com.hantek.tbbl.core.component.service.IService");
//        // 自定义 service 实现类父类
////        strategy.setSuperServiceImplClass("com.hantek.tbbl.core.component.service.ServiceImpl");
//        mpg.setStrategy(strategy);
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setParent(null);
//        String parantPkg = generatorDto.getPackageDir();
//        pc.setEntity(parantPkg + ".model");
//        pc.setMapper(parantPkg + ".mapper");
//        pc.setXml(parantPkg + ".mapper.xml");
//        pc.setService(parantPkg + ".service");
//        pc.setServiceImpl(parantPkg + ".service.impl");
//        pc.setController(parantPkg + ".controller");
//        mpg.setPackageInfo(pc);
//
//        // 关闭默认 xml 生成，调整生成 至 根目录
//        TemplateConfig tc = new TemplateConfig();
//        tc.setController(null);//模块如果设置 空 OR Null 将不生成该模块。
//        tc.setMapper(null);
//        tc.setService(null);
//        tc.setServiceImpl(null);
//        tc.setXml(null);
//        tc.setEntity(null);
//        String[] tpls = generatorDto.getTpls().split("\\.");
//        for (String tpl : tpls) {
//            TplEnum tplem = TplEnum.parseOf(tpl);
//            switch (tplem){
//                case MAPPER:
//                    tc.setMapper("/templates/mapper.java.vm");
//                    break;
//                case MAPPER_XML:
//                    tc.setXml("/templates/mapper.xml.vm");
//                    break;
//                case SERIVCE:
//                    tc.setService("/templates/service.java.vm");
//                    break;
//                case SERIVCE_IMPL:
//                    tc.setServiceImpl("/templates/serviceImpl.java.vm");
//                    break;
//                case ENTITY:
//                    tc.setEntity("/templates/entity.java.vm");
//                    break;
//                case CONTROLLER:
//                    tc.setController("/templates/controller.java.vm");
//                    break;
//            }
//
//        }
//        mpg.setTemplate(tc);
//
//        // 执行生成
//        mpg.execute();


        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(generatorDto.getOutputDir());//这里写你自己的java目录
        gc.setFileOverride(true);//是否覆盖
        gc.setActiveRecord(true);
        gc.setAuthor(generatorDto.getAuthor());
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName(dataSourceProperties.getDriverClassName());
        dsc.setUsername(dataSourceProperties.getUsername());
        dsc.setPassword(dataSourceProperties.getPassword());
        dsc.setUrl(dataSourceProperties.getUrl());
        mpg.setDataSource(dsc);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setParent(null);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
         String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return generatorDto.getOutputDir() + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        //        // templateConfig.setService();
        //        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);



        strategy.setSuperEntityClass("com.xsungroup.tms.core.supper.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        strategy.setSuperControllerClass("com.xsungroup.tms.core.supper.SuperController");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        String[] tables = generatorDto.getTableNames().split("\\.");
        strategy.setInclude(tables);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("T_","C_","ZC_","EXT_","APP_");// 此处可以修改为您的表前缀
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.execute();



    }


}
