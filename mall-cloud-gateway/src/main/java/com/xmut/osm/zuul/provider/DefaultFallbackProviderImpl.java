package com.xmut.osm.zuul.provider;

import com.alibaba.fastjson.JSON;
import com.xmut.osm.common.bean.ResultVO;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.AbstractClientHttpResponse;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author 阮胜
 * @date 2018/7/22 20:01
 */
@Component
public class DefaultFallbackProviderImpl implements FallbackProvider {
    @Override
    public String getRoute() {
        return null;
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new AbstractClientHttpResponse() {
            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.name();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                ResultVO<String> resultVO = new ResultVO<>();
                resultVO.setMessage("服务器正忙!");
                resultVO.setSuccess(false);
                return new ByteArrayInputStream(JSON.toJSONString(resultVO).getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return httpHeaders;
            }
        };
    }
}
