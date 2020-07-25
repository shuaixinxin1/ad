package com.itcanteen.search.Contoller;

import com.itcanteen.search.SearchService;
import com.itcanteen.search.vo.SearcResponse;
import com.itcanteen.search.vo.SearchRequest;
import com.itcanteen.search.vo.feature.DistrictFeature;
import com.itcanteen.search.vo.feature.FeatureRelation;
import com.itcanteen.search.vo.feature.ItFeature;
import com.itcanteen.search.vo.feature.KeyWordsFeature;
import com.itcanteen.search.vo.media.AdSolt;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/7/5 09:41
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService searchService;
    /**
     * 广告检索系统
     * @param request
     * @return
     */
    @RequestMapping("")
    public SearcResponse search(@RequestBody SearchRequest request){
        //广告位的信息
        List<AdSolt> adSolts = request.getRequestInfo().getAdSolts();
        FeatureRelation featureRelation = request.getFeatureInfo().getFeatureRelation();
        ArrayList<SearcResponse.Creative> searchResponseList = new ArrayList<>();
        SearcResponse searcResponse = new SearcResponse();
        Map<String, List<SearcResponse.Creative>> adSolt2Ads = searcResponse.getAdSolt2Ads();
        //去找单元  关键词
        //关键词限制的信息  获取查询到关键字的id
        KeyWordsFeature keyWordsFeature = request.getFeatureInfo().getKeyWordsFeature();
        List<String> keywords = keyWordsFeature.getKeywords();

        ArrayList<Long> keywordUnitiIds = new ArrayList<>();

        keywords.forEach(i->{
            List<Map<String, Object>> maps = searchService.searchByKeyWord(i);
            for (int j=0; j<maps.size();j++){
                //等于获取字段里的 ad_unit_keyword  的unit_id  关键字
                Long unit_id = (Long) maps.get(j).get("unit_id");
                keywordUnitiIds.add(unit_id);
            }
        });
        //地域信息的过滤
        DistrictFeature districtFeature = request.getFeatureInfo().getDistrictFeature();
        //省和市 通过数据库 ad——unit_district
        List<DistrictFeature.ProvinceAndCity> districts = districtFeature.getDistricts();
        ArrayList<Long> districtUnitIds = new ArrayList<>();
        districts.forEach(i->{
            String province = i.getProvince();
            String city = i.getCity();
            List<Map<String, Object>> maps = searchService.searchByProvinceAndCity(province, city);
            for (int k=0; k<maps.size();k++ ){
                Long unit_id = (Long) maps.get(k).get("unit_id");
                districtUnitIds.add(unit_id);
            }
        });
        //兴趣的过滤
        ItFeature itFeature = request.getFeatureInfo().getItFeature();
        List<String> its = itFeature.getIts();
        ArrayList<Long> itUnitIds = new ArrayList<>();
        //id_Unit_it 去进行匹配
        its.forEach(i->{
            List<Map<String, Object>> maps = searchService.searchByItTag(i);
            for (int q=0;q<maps.size();q++){
                Long unit_id = (Long) maps.get(q).get("unit_id");
                itUnitIds.add(unit_id);
            }
        });
        for (AdSolt adSolt:adSolts){
            Integer positionType = adSolt.getPositionType();
            //为了拿到ad_unit id  因为这张表是unit 表所以id 就是unitid
            List<Map<String, Object>> adsoltUnitIds = searchService.searchByPositionType(positionType);
            ArrayList<Long> unitIds = new ArrayList<>();

            adsoltUnitIds.forEach(i->{
                Long unitId = (Long) i.get("id");
                unitIds.add(unitId);
            });
            HashSet<Long> unitIdSets = new HashSet<>(unitIds);
            HashSet<Long> targetUnitIds;
            //属于交集
            if(featureRelation==FeatureRelation.AND){
                //工具包提供了取交集的
                Collection<Long> intersection = CollectionUtils.intersection(keywordUnitiIds, districtUnitIds);
                Collection<Long> intersection1 = CollectionUtils.intersection(itUnitIds, unitIds);
                Collection<Long> intersection2 = CollectionUtils.intersection(intersection, intersection1);
                targetUnitIds = new HashSet<>(intersection2);
            }else{//并集
                Collection<Long> intersection = CollectionUtils.union(keywordUnitiIds, districtUnitIds);
                Collection<Long> intersection1 = CollectionUtils.union(itUnitIds, unitIds);
                Collection<Long> intersection2 = CollectionUtils.union(intersection, intersection1);
                targetUnitIds = new HashSet<>(intersection2);
            }
            ArrayList<Map<String, Object>> arrayList = new ArrayList<>();

            //只要遍历是否是有效的
            targetUnitIds.forEach(i->{
                List<Map<String, Object>> maps = searchService.searchByUnitId(i);
                for (int a=0;a<maps.size();a++){
                    Integer unit_status = (Integer) maps.get(a).get("unit_status");
                    if(unit_status==1){//状态有效
                        arrayList.add(maps.get(a));
                    }
                }
            });
            //推广和创意单元的中间表 creative_unit
            ArrayList<Map<String,Object>> creativeUnitList = new ArrayList<>();
            arrayList.forEach(j->{
                Long unitId = (Long) j.get("id");
                List<Map<String, Object>> maps = searchService.searchCreativeByUnitId(unitId);
                for (int g=0;g<maps.size();g++){
                    creativeUnitList.add(maps.get(g));
                }
                
            });
            creativeUnitList.forEach(i->{
                Long creative_id = (Long) i.get("creative_id");
                List<Map<String, Object>> creativeList = searchService.searchCreativeBycreativeId(creative_id);
                for (int m=0; m<creativeList.size();m++){
                    SearcResponse.Creative creative = new SearcResponse.Creative();
                    creative.setAdUrl((String) creativeList.get(m).get("url"));
                    creative.setType((Integer) creativeList.get(m).get("type"));
                    creative.setAdId((Long) creativeList.get(m).get("id"));
                    creative.setMaterialType((Integer) creativeList.get(m).get("material_type"));
                    creative.setWidth((Integer)creativeList.get(m).get("width"));
                    creative.setHeight((Integer) creativeList.get(m).get("height"));
                    searchResponseList.add(creative);
                }
            });
            //媒体方传过来的广告位
            adSolt2Ads.put(adSolt.getAdSoltCode(),searchResponseList);
            searcResponse.setAdSolt2Ads(adSolt2Ads);

        }

        //去除重复
        return searcResponse;
    }

    @RequestMapping("/test")
    public SearcResponse searchTest(){
        SearcResponse searchResponse = new SearcResponse();
        Map<String, List<SearcResponse.Creative>> adSolt2Ads = searchResponse.getAdSolt2Ads();
        ArrayList<SearcResponse.Creative> arrayList = new ArrayList<>();

        SearcResponse.Creative creative1 = new SearcResponse.Creative();
        creative1.setHeight(1);
        creative1.setWidth(2);
        creative1.setMaterialType(1);
        creative1.setAdId(10L);
        creative1.setAdUrl("https://baimugudu.oss-cn-beijing.aliyuncs.com/data/QQ20190923-0.jpg");
        creative1.setType(1);

        SearcResponse.Creative creative2 = new SearcResponse.Creative();
        creative2.setHeight(100);
        creative2.setWidth(200);
        creative2.setMaterialType(4);
        creative2.setAdId(11L);
        creative2.setAdUrl("http://inews.gtimg.com/newsapp_ls/0/12031734386_294195/0");
        creative2.setType(1);

        arrayList.add(creative1);
        arrayList.add(creative2);

        adSolt2Ads.put("001",arrayList);

        searchResponse.setAdSolt2Ads(adSolt2Ads);
        return searchResponse;


    }


}
