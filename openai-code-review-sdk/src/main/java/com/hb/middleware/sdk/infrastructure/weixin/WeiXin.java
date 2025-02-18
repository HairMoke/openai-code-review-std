package com.hb.middleware.sdk.infrastructure.weixin;

import com.alibaba.fastjson2.JSON;
import com.hb.middleware.sdk.infrastructure.weixin.dto.TemplateMessageDto;
import com.hb.middleware.sdk.types.utils.WXAccessTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;


public class WeiXin {

    private final Logger logger = LoggerFactory.getLogger(WeiXin.class);

    private final String appid;
    private final String secret;
    private final String touser;
    private final String template_id;

    public WeiXin(String appid, String secret, String touser, String template_id) {
        this.appid = appid;
        this.secret = secret;
        this.touser = touser;
        this.template_id = template_id;
    }

    public void sendTemplateMessage(String logUrl, Map<String, Map<String, String>> data) throws Exception {
        String accessToken = WXAccessTokenUtils.getAccessToken(appid, secret);

        TemplateMessageDto templateMessageDto = new TemplateMessageDto(touser, template_id);
        templateMessageDto.setUrl(logUrl);
        templateMessageDto.setData(data);

        URL url = new URL("https://open.bigmodel.cn/api/paas/v4/chat/completions");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        connection.setDoOutput(true);

        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = JSON.toJSONString(templateMessageDto).getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        try(Scanner scanner = new Scanner(connection.getInputStream(), StandardCharsets.UTF_8.name())) {
            String response = scanner.useDelimiter("\\A").next();
            logger.info("openai-code-review weixin template message response: {}", response);
        }
    }


}
