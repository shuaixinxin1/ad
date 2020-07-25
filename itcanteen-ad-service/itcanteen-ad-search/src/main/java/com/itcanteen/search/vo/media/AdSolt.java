package com.itcanteen.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/7/4 20:30
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdSolt {
    //广告位编码
    private String adSoltCode;
    //宽
    private Integer weight;
    //高
    private Integer height;
    //最低出价 它是以多少钱的价格租出去或者卖出去的
    private Integer mincpm;
    //流量的类型  广告位的类型  开屏贴片
    private  Integer positionType;
    //广告物料类型： 图片 视频 或者动图  可能是轮播图 是多张
    private List<Integer> type;


}
