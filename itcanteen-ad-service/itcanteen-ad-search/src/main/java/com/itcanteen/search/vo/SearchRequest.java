package com.itcanteen.search.vo;

import com.itcanteen.search.vo.feature.DistrictFeature;
import com.itcanteen.search.vo.feature.FeatureRelation;
import com.itcanteen.search.vo.feature.ItFeature;
import com.itcanteen.search.vo.feature.KeyWordsFeature;
import com.itcanteen.search.vo.media.AdSolt;
import com.itcanteen.search.vo.media.App;
import com.itcanteen.search.vo.media.Device;
import com.itcanteen.search.vo.media.Geo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/7/4 20:26
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchRequest {

    //媒体方标识
    private String mediaId;
    //请求的基本信息
    private RequestInfo requestInfo;
    //请求匹配信息
    private FeatureInfo featureInfo;


    /**
     * 请求的基本信息
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static  class RequestInfo{

        //唯一的请求id
        private String requestId;
        //可能有多个广告位
        private List<AdSolt> adSolts;
        //终端信息
        private App app;
        //地域信息
        private Geo geo;
        //设备信息
        private Device device;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class FeatureInfo{

        //关键词
        private KeyWordsFeature keyWordsFeature;
        //地区
        private DistrictFeature districtFeature;
        //兴趣的标签
        private ItFeature itFeature;

        //设置默认值 默认取交集 and
        private FeatureRelation featureRelation=FeatureRelation.AND;

    }
}
