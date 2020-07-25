package com.itcanteen.search.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/7/5 09:05
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearcResponse {
   //返回对象位 广告位 和创意
   private Map<String, List<Creative>> adSolt2Ads=new HashMap();

   @AllArgsConstructor
   @NoArgsConstructor
   @Data
   public  static class Creative{
      private Long adId;
      private String adUrl;
      private Integer type;
      private Integer materialType;
      private Integer height;
      private Integer width;
      //图片地址
      private String url;
      //计费系统 如果有人点击通过回调来显示 进行计费
      //媒体方向平台要钱  平台会有一个反作弊系统  接口地址  展示检测url
      private List<String> showUrl= Arrays.asList("www.bbb.com/show","www.ccc.com/show");

      //点击检测url
      private List<String> clientMonitorUrl=Arrays.asList("www.bbb.com/show","www.ccc.com/show");

   }
}
