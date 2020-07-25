package com.itcanteen.search.vo.feature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/7/4 21:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictFeature {


    private List<ProvinceAndCity> districts;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProvinceAndCity{
        //市
        private String city;
        //省
        private String province;
    }
}
