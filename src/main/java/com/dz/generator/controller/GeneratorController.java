package com.dz.generator.controller;


import com.dz.generator.constant.JdbcTypeEnum;
import com.dz.generator.constant.TplEnum;
import com.dz.generator.dto.*;
import com.dz.generator.model.ColumnDo;
import com.dz.generator.service.IGeneratorService;
import com.dz.generator.util.MybatisPlusGenerator;
import com.dz.generator.util.ResponseWrapper;
import com.dz.generator.util.VelocityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class GeneratorController {

    @Autowired
    private IGeneratorService iGeneratorService;

    @Autowired
    private MybatisPlusGenerator generator;

    @GetMapping("/table")
    public BusResult basetable(){
        return BusResult.build(BusCode.SUCCESS,iGeneratorService.findListTable());
    }

    @GetMapping("/filed")
    public BusResult tableCol(@RequestParam String names){
        List<ColumnDo> list = iGeneratorService.findListColumn(names);
        return BusResult.build(BusCode.SUCCESS,list);
    }

    @GetMapping("/template")
    public BusResult template(){
        return BusResult.build(BusCode.SUCCESS, TplEnum.getTplList("0"));
    }

    @GetMapping("/fieldtype")
    public BusResult fieldType(){
        return BusResult.build(BusCode.SUCCESS, JdbcTypeEnum.getList());
    }

    @PostMapping("/generator")
    public BusResult generator(@RequestBody GeneratorDto dto){
        generator.generatorTpl(dto);
        return BusResult.build(BusCode.SUCCESS);
    }


    @PostMapping("/generatorDtoOrVo")
    public BusResult generatorDtoOrVo(@RequestBody GeneratorDto dto){
        String filePath = VelocityUtil.createModel(dto);
        return BusResult.build(BusCode.SUCCESS,filePath);
    }

    @GetMapping("/download")
    public void download(@RequestParam String filePath , HttpServletResponse response) throws RuntimeException {
        //提供下载文件前进行压缩，即服务端生成压缩文件
        File file = new File(filePath);
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
        response.addHeader("Content-Disposition", "attachment;filename=" + file.getName());
        try {
            //获取文件输入流
            InputStream in = new FileInputStream(file);
            int len = 0;
            byte[] buffer = new byte[1024];
            OutputStream out = response.getOutputStream();
            while ((len = in.read(buffer)) > 0) {
                //将缓冲区的数据输出到客户端浏览器
                out.write(buffer, 0, len);
            }
            in.close();
        } catch (IOException e) {
            log.error("文件下载失败:{}", e);
        }
    }


    @PostMapping("/generatorHtml")
    public BusResult generatorHtml(@RequestBody GeneratorDto dto){
        VelocityUtil.createModel(dto);
        return BusResult.build(BusCode.SUCCESS);
    }



}
