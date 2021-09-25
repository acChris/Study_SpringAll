package com.weng.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Author: acChris
 * @Date: 2021/9/24 19:34
 * @Description:
 */
@Controller
public class UploadController {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UploadController.class);

    /*@GetMapping("/upload")
    public String upload(){
        return "upload";
    }*/

    /**
     *
     * @param file （此处 MultipartFile 是 Spring 类型，代表 HTML 的 FORM DATA 提交方式，含二进制文件 + 文件名）
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()){
            return "上传失败，请选择文件！";
        }

        // 获取上传文件的初始名
        String fileName = file.getOriginalFilename();
        String filePath = "E:\\Users\\Administrator\\Desktop\\毕设\\vueServer\\src\\main\\resources\\upload\\";
        // 路径
        File dest = new File(filePath + fileName);
        try{
            // 生成文件
            file.transferTo(dest);
            LOGGER.info("上传成功！");
            return "上传成功";
        }catch(Exception e){
            // 出现异常，记录日志
            LOGGER.error(e.toString(), e);
        }
        return "上传失败";
    }

}
