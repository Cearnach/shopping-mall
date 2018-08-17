package com.xmut.osm.dfs.controller;

import com.xmut.osm.common.bean.ResultVO;
import com.xmut.osm.dfs.util.FastDFSWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 阮胜
 * @date 2018/8/16 18:29
 */
@RestController
@Slf4j
public class FileController {

    private static final String HTTP = "http://";
    private final FastDFSWrapper fastDFSWrapper;

    public FileController(FastDFSWrapper fastDFSWrapper) {
        this.fastDFSWrapper = fastDFSWrapper;
    }

    @GetMapping("/upload/{str}")
    public String uploadStr(@PathVariable String str) {
        return fastDFSWrapper.uploadFile(str, "txt");
    }

    @PostMapping("/upload")
    public ResultVO<String> upload(MultipartFile file) {
        ResultVO<String> resultVO = new ResultVO<>();
        try {
            String filePath = fastDFSWrapper.uploadFile(file);
            resultVO.setSuccess(true);
            resultVO.setData(HTTP + filePath);

        } catch (IOException e) {
            log.error(e.getMessage());
            resultVO.setSuccess(false);
            resultVO.setMessage(e.getMessage());
        }
        return resultVO;
    }
}
