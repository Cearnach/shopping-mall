package com.xmut.osm.dfs.controller;

import com.xmut.osm.dfs.util.FastDFSWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 阮胜
 * @date 2018/8/16 18:29
 */
@RestController
public class FileController {
    private final FastDFSWrapper fastDFSWrapper;

    public FileController(FastDFSWrapper fastDFSWrapper) {
        this.fastDFSWrapper = fastDFSWrapper;
    }

    @GetMapping("/upload/{str}")
    public String uploadStr(@PathVariable String str) {
        return fastDFSWrapper.uploadFile(str, "txt");
    }
}
