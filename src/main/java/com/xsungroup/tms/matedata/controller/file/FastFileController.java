package com.xsungroup.tms.matedata.controller.file;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.supper.ResponseInfo;
import org.aabss.utils.file.FileUtils;
import org.aabss.utils.web.WebUtil;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

//import com.google.gson.JsonParseException;

/**
 * @author : Lilei
 * @Description : 文件
 * @Date : 2019/4/21
 */
@RestController
@RequestMapping(value = "file")
public class FastFileController {

    @Autowired
    FastFileStorageClient fastFileStorageClient;
    @Autowired
    ThreadPoolTaskExecutor executor;

    @RequestMapping(path = "fastUpload",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseInfo uploadFileByDfs(@RequestParam("file") MultipartFile file) throws IOException {
        if (file==null || file.isEmpty()){//            WebUtil.write(new Gson().toJson(ResultUtil.setFailedResult("上传文件不能为空！")),response);
            return  ResponseUtil.success("上传文件不能为空！");
        }
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
        return  ResponseUtil.success(storePath.getGroup()+"/"+storePath.getPath());
    }

    @RequestMapping(path = "fastUpload/base64",method = RequestMethod.POST)
    public ResponseInfo uploadFileByDfs(HttpServletRequest request,HttpServletResponse response) throws DecoderException {
        Map<String, Object> map = WebUtil.request2Map(request);
        InputStream inputStream = FileUtils.convertBase64DataToInputStream((String) map.get("file"));
        StorePath storePath = fastFileStorageClient.uploadFile(inputStream,Integer.parseInt((String)map.get("fileSize")),
                FilenameUtils.getExtension((String) map.get("fileName")),null);
       return  ResponseUtil.success(storePath.getGroup()+"/"+storePath.getPath());
    }
}
