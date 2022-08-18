package com.example.searchhot.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.searchhot.mapper.HotDetailMapper;
import com.example.searchhot.mapper.HotMapper;
import com.example.searchhot.pojo.Hot;
import com.example.searchhot.pojo.HotDetail;
import com.example.searchhot.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO
 *
 * @author yi_sao
 * @date 2022/8/17
 */
@Component
public class SearchHotTask {

    @Autowired
    private HotDetailMapper hotDetailMapper;

    @Autowired
    private HotMapper hotMapper;

    @Autowired
    private IMailService mailService;

    private static Map<String, HotDetail> AFTER_HOT_DETAIL = Collections.synchronizedMap(new HashMap<>());
    private static Map<String, Hot> AFTER_HOT = Collections.synchronizedMap(new HashMap<>());

    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";// 中文正则

    private String url = "https://api.codelife.cc/api/top/list";

    private String version = "1.3.15";

    @Scheduled(cron ="0 0/1 * * * ?")
    public void searchWeiBo() {
        String json = "{\"id\":\"KqndgxeLl9\"}";
        String body = HttpRequest.post(url)
                .header("version", version)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36")
                .header("signaturekey", "U2FsdGVkX18VAXlqa6xN1dQjt042m7erdS74/NUeKWc=")
                .body(json)
                .execute().body();
        insert(body, "微博");
    }

    @Scheduled(cron ="30 0/1 * * * ?")
    public void searchZhiHu() {
        String json = "{\"id\":\"mproPpoq6O\"}";
        String body = HttpRequest.post(url)
                .header("version", version)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36")
                .header("signaturekey", "U2FsdGVkX199xA3xjS3HISejR8glxzE0+9S18eLqE3M=")
                .body(json)
                .execute().body();
        insert(body, "知乎");
    }

    private void insert(String body, String type){
        JSONObject entries = JSONUtil.parseObj(body);
        if(entries.get("code").toString().equals("200")){
            List data = entries.get("data", List.class);
            Set<String> titleSet = new HashSet<>();
            data.forEach(e -> {
                JSONObject obj = JSONUtil.parseObj(e);
                Double value = new Double(0);
                Pattern pat = Pattern.compile(REGEX_CHINESE);
                String hotValue = obj.get("hotValue").toString();
                Matcher mat = pat.matcher(hotValue);
                if(hotValue.contains("万")){
                    value = Double.valueOf(mat.replaceAll("").replaceAll(" ", ""));
                } else {
                    String replace = mat.replaceAll("").replaceAll(" ", "");
                    if(StrUtil.isEmpty(replace)){
                        return;
                    }
                    value = Double.valueOf(replace)/10000;
                }
                String title = obj.get("title").toString();
                HotDetail hotDetail = new HotDetail();
                hotDetail.setId(UUID.randomUUID(true).toString());
                hotDetail.setTitle(title);
                hotDetail.setRanking(Integer.valueOf(obj.get("index").toString()));
                hotDetail.setHotTime(new Date());
                hotDetail.setSearchType(type);
                hotDetail.setHotValue(value);

                String key = type + title;
                titleSet.add(key);

                if(AFTER_HOT.containsKey(key)){
                    Hot hot = AFTER_HOT.get(key);
                    hotDetail.setHotId(hot.getId());
                    HotDetail detail = AFTER_HOT_DETAIL.get(key);
                    if(!detail.getRanking().equals(hotDetail.getRanking())){
                        hotDetailMapper.insert(hotDetail);
                        AFTER_HOT_DETAIL.put(key, hotDetail);
                    }

                    hot.setEndTime(new Date());
                    if(hot.getMaxHotValue() < hotDetail.getHotValue()){
                        hot.setMaxHotValue(hotDetail.getHotValue());
                    }
                    if(hot.getMaxRanking() > hotDetail.getRanking()){
                        hot.setMaxRanking(hotDetail.getRanking());
                    }
                    hotMapper.updateById(hot);
                    AFTER_HOT.put(key, hot);
                } else {
                    Hot hot = new Hot();
                    Date date = new Date();
                    hot.setSearchType(type);
                    hot.setId(UUID.randomUUID(true).toString());
                    hot.setStartTime(date);
                    hot.setTitle(hotDetail.getTitle());
                    hot.setMaxHotValue(hotDetail.getHotValue());
                    hot.setEndTime(date);
                    hot.setMaxRanking(hotDetail.getRanking());
                    hot.setLink(obj.get("link").toString());
                    hotMapper.insert(hot);
                    AFTER_HOT.put(key, hot);
                    hotDetail.setHotId(hot.getId());
                    hotDetailMapper.insert(hotDetail);
                    AFTER_HOT_DETAIL.put(key, hotDetail);
                }
            });
            Set<String> keySet = AFTER_HOT.keySet();
            Collection<String> subtract = CollUtil.subtract(new HashSet<>(keySet), titleSet);
            subtract.forEach(e -> {
                if(e.startsWith(type)){
                    AFTER_HOT.remove(e);
                    AFTER_HOT_DETAIL.remove(e);
                }
            });
        } else {
            try {
                throw new Exception(body);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mailService.sendSimpleMail("yi_sao@foxmail.com", "热搜接口异常", body);
        }
    }
}
