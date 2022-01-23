package com.chufinfo.sorting.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * yml工具类
 *
 * @author kenyon
 */
@Slf4j
public class YmlUtils {

    private final static DumperOptions OPTIONS = new DumperOptions();

    static {
        //将默认读取的方式设置为块状读取
        OPTIONS.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
    }


    /**
     * 在目标文件中添加新的配置信息
     *
     * @param filePath    需要添加信息的目标yml文件
     * @param newFilePath 新的yml文件
     * @param keyValueMap 添加的key值 添加的对象
     * @title addIntoYml
     * @author zhouyb
     * @date 2019/7/29 20:52
     */
    public static void addIntoYml(String filePath, String newFilePath, Map<String, Object> keyValueMap) throws Exception {
        Yaml yaml = new Yaml(OPTIONS);
        //载入文件
        LinkedHashMap<String, Object> dataMap = yaml.load(new FileReader(filePath));
        //写入数据
        Set<String> set = keyValueMap.keySet();
        for (String key : set) {
            Object value = keyValueMap.get(key);
            try {
                setYmlMapValue(dataMap, key, value);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
        ///写入数据
       /*
        keyValueMap.forEach((key, value) -> {
            try {
                setYmlMapValue(dataMap, key, value);
            } catch (Exception e) {
                log.error("赋值出错：" + e.getMessage());
                return;
            }
        });*/
        //将数据重新写回文件
        yaml.dump(dataMap, new FileWriter(newFilePath));
    }


    /**
     * 从目标yml文件中读取出所指定key的值
     *
     * @param source 获取yml信息的文件
     * @param key    需要获取信息的key值
     * @return java.lang.Object
     * @author zhouyb
     * @title addIntoYml
     * @date 2019/7/29 20:52
     */
    public static Object getFromYml(File source, String key) throws Exception {
        Yaml yaml = new Yaml(OPTIONS);
        //载入文件
        LinkedHashMap<String, Object> dataMap = yaml.load(new FileReader(source));
        //获取当前key下的值(如果存在多个节点,则value可能为map,自行判断)
        // 首先将key进行拆分
        String[] keys = key.split("[.]");
        // 将配置文件进行复制
        Map ymlInfo = dataMap;
        for (int i = 0; i < keys.length; i++) {
            Object value = ymlInfo.get(keys[i]);
            if (i < keys.length - 1) {
                ymlInfo = (Map) value;
            } else if (value == null) {
                throw new Exception("key不存在");
            } else {
                return value;
            }
        }
        throw new RuntimeException("不可能到这里的...");
    }


    /**
     * 递归往map中插入值
     *
     * @param dataMap yml map
     * @param key     插入的key 如server.tomcat.uri-encoding
     * @param value   值
     * @author zhouyb
     * @title addIntoYml
     * @date 2019/7/29 20:52
     */

    public static void setYmlMapValue(LinkedHashMap<String, Object> dataMap, String key, Object value) throws Exception {
        try {
            String splitStr = ".";
            if (key.contains(splitStr)) {
                //当前层级的key
                String nowKey = key.split("\\.")[0];
                //剩下的key值
                String lastKey = key.substring(key.indexOf(splitStr) + 1);
                if (dataMap.containsKey(nowKey)) {
                    //存在修改
                    setYmlMapValue((LinkedHashMap<String, Object>) dataMap.get(nowKey), lastKey, value);
                } else {
                    //不存在新增
                    dataMap.put(nowKey, new LinkedHashMap<String, Object>());
                    setYmlMapValue((LinkedHashMap<String, Object>) dataMap.get(nowKey), lastKey, value);
                }
            } else {
                //加值
                dataMap.put(key, value);
            }
        } catch (Exception e) {
            log.error("修改配置参数格式不正确" + e.getMessage(), e);
            throw new Exception("修改配置参数格式不正确" + e.getMessage(), e);
        }
    }


}
