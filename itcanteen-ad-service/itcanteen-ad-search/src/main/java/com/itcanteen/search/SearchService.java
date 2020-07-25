package com.itcanteen.search;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/7/5 11:17
 * 调用es进行搜索
 */
@FeignClient(name = "ITCANTEEN-AD-KAFKA-CONSUMER")
public interface SearchService {

    @RequestMapping("/search/searchByKeyWord")
    public List<Map<String, Object>> searchByKeyWord(@RequestParam("keyword") String keyword);

    @RequestMapping("/search/searchByProvinceAndCity")
    public List<Map<String,Object>> searchByProvinceAndCity(@RequestParam("province") String province,
                                                            @RequestParam("city") String city);

    @RequestMapping("/search/searchByItTag")
    public List<Map<String,Object>> searchByItTag(@RequestParam("itTag")String itTag);

    /**
     * 流量类型
     * @param positionType
     * @return
     */
    @RequestMapping("/search/searchByPositionType")
    public List<Map<String,Object>> searchByPositionType(@RequestParam("positionType")Integer positionType);

    /**
     * 为了查询状态 是否可以使用 查询表为 id_unit表
     * @param unitId
     * @return
     */
    @RequestMapping("/search/searchByUnitId")
    public List<Map<String,Object>> searchByUnitId(@RequestParam("unitId")Long unitId);

    @RequestMapping("/searc/searchCreativeByUnitId")
    public List<Map<String,Object>> searchCreativeByUnitId(@RequestParam("unitId")Long unitId);

    @RequestMapping("/searc/searchCreativeBycreativeId")
    public List<Map<String,Object>> searchCreativeBycreativeId(@RequestParam("creativeId")Long creativeId);
}
