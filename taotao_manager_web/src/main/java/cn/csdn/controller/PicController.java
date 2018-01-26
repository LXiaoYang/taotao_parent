package cn.csdn.controller;

import cn.csdn.common.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PicController {

//    @Value("${TAOTAO_IMAGE_URL}")
    
    private String IMAGE_URL = "http://192.168.25.133/";


    //上传图片......
    @RequestMapping("/pic/upload")
    @ResponseBody
    public Map<String,Object> sendPic(MultipartFile uploadFile){

        //获取真实姓名
        String realName = uploadFile.getOriginalFilename();
        String proxx = realName.substring(realName.lastIndexOf(".") + 1);
        try {
            //        创建一个服务端
            FastDFSClient client = new FastDFSClient("classpath:fastdfs.conf");//配置文件的路径
            byte[] bytes = uploadFile.getBytes();//获取字节

            String picPath = client.uploadFile(bytes, proxx);

            //封装参数
            Map result = new HashMap<>();
            result.put("error", 0);
            result.put("url", IMAGE_URL+picPath);

            return result;
        } catch (Exception e) {
            e.printStackTrace();

            Map result = new HashMap<>();
            result.put("error", 1);
            result.put("message", "图片上传失败");

            return result;
        }

    }

}
