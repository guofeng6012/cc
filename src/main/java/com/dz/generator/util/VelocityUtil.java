package com.dz.generator.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.dz.generator.constant.JdbcTypeEnum;
import com.dz.generator.constant.TplEnum;
import com.dz.generator.dto.GeneratorDto;
import com.dz.generator.model.ColumnDo;
import com.google.common.base.CaseFormat;
import com.google.common.base.Strings;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class VelocityUtil {


    public static String createModel(GeneratorDto dto) {
        VelocityUtil util = new VelocityUtil();
        VelocityEngine ve = util.initVe();
        String[] tpls = dto.getTpls().split("\\.");
        VelocityContext ctx = util.buildCtx(dto);
        String outFile = "";
        String tplStr = tpls[0];
        TplEnum tplEm = TplEnum.parseOf(tplStr);
        String tempValue = tplEm.getValue();
        tempValue = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, tempValue);
        String dirStr = "";
        String fileName = "";

        if (tplEm.equals(TplEnum.DTO) || tplEm.equals(TplEnum.VO)) {
            fileName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, dto.getName());
            dirStr = dto.getPackageDir().replaceAll("\\.", "\\\\") + "\\" + tplStr + "\\";
            outFile = dto.getOutputDir() + "\\" + dirStr + fileName + tempValue.substring(0, tempValue.lastIndexOf(".vm"));
        } else if (tplEm.equals(TplEnum.LISTVUE) || tplEm.equals(TplEnum.TREEVUE)) {
            fileName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_CAMEL, dto.getName());
            outFile = dto.getOutputDir() + "\\" + dirStr + fileName + ".vue";
        }

        Template tpl = ve.getTemplate("/templates/" + tplEm.getValue());
        System.out.println("outFile = " + outFile);
        util.merge(tpl, ctx, outFile);
        System.out.println("success...");
        return outFile;
    }


    private VelocityEngine initVe() {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.setProperty("input.encoding", "UTF-8");
        ve.setProperty("output.encoding", "UTF-8");
        ve.init();
        return ve;
    }


    private VelocityContext buildCtx(GeneratorDto dto) {

        String jsonStr = dto.getJsonStr();

        if (Strings.isNullOrEmpty(jsonStr)) {
            return null;
        }

        List<ColumnDo> list = JSONArray.parseArray(jsonStr, ColumnDo.class);

        Set<String> pkg = new HashSet<>();

        List<Map<String, String>> listmap = new ArrayList<>();
        for (ColumnDo columnDo : list) {


            Map<String, String> map = new HashMap<>();
            String fieldName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnDo.getName());
            map.put("fieldName", fieldName);
            String methodName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, columnDo.getName());
            map.put("methodName", methodName);
            map.put("comment", columnDo.getComment());

            JdbcTypeEnum jdem = JdbcTypeEnum.parseOf(columnDo.getJdbcType());
            map.put("javaType", jdem.getName());
            listmap.add(map);

            pkg.add(jdem.getJavaType());//导入包
        }

        VelocityContext ctx = new VelocityContext();

        ctx.put("package", dto.getPackageDir() + "." + dto.getTpls());
        ctx.put("importPackages", pkg);
        ctx.put("comment", dto.getComments());
        ctx.put("author", dto.getAuthor());

        Date nowTime = new Date();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ctx.put("date", time.format(nowTime));

        String modelname = dto.getName();
        modelname = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, modelname);
        ctx.put("entity", modelname);
        ctx.put("fieldList", listmap);

        return ctx;
    }


    private void merge(Template template, VelocityContext ctx, String outputFile) {
        try {
            File file = new File(outputFile);
            if (!file.getParentFile().exists()) {
                // 如果文件所在的目录不存在，则创建目录
                if (!file.getParentFile().mkdirs()) {
                    return;
                }
            }
            FileOutputStream fos = new FileOutputStream(outputFile);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, ConstVal.UTF8));
            template.merge(ctx, writer);
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        String jsonStr = "[{\"comment\":\"课程体系id\",\"javaType\":\"java.lang.String\",\"jdbcType\":\"VARCHAR2\",\"name\":\"CATEGORY_ID\",\"tableName\":\"COURSE_CATEGORY\"},{\"comment\":\"课程体系名称\",\"javaType\":\"java.lang.String\",\"jdbcType\":\"VARCHAR2\",\"name\":\"CATEGORY_NAME\",\"tableName\":\"COURSE_CATEGORY\"},{\"comment\":\"创建时间\",\"javaType\":\"java.util.Date\",\"jdbcType\":\"DATE\",\"name\":\"CREATE_TIME\",\"tableName\":\"COURSE_CATEGORY\"},{\"comment\":\"sdfsfsfsf\",\"name\":\"COURSE_LEVEL\",\"javaType\":\"java.lang.Integer\"}]";
//
//        String modelname = "user";
//        modelname = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL,modelname);
//
//
//
//        List<ColumnDo> list = JSONArray.parseArray(jsonStr,ColumnDo.class);
//
//        Set<String> pkg = new HashSet<>();
//
//        List<Map<String,String>> listmap = new ArrayList<>();
//        for (ColumnDo dto : list) {
//            System.out.println("columnDo.toString() = " + dto.toString());
//            pkg.add(dto.getJavaType());//导入包
//
//            Map<String,String> map = new HashMap<>();
//            String fieldName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,dto.getName());
////            System.out.println("fieldName = " + fieldName);
//            map.put("fieldName",fieldName);
//            String methodName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,dto.getName());
//            map.put("methodName",methodName);
//            map.put("comment",dto.getComment());
//            map.put("javaType",dto.getJavaType());
//            listmap.add(map);
//        }

        String a = "com.aa";
        String bb = a.replaceAll("\\.", "\\\\");
        System.out.println("bb = " + bb);


    }
}
