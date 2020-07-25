package com.itcanteen.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/18 15:06
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdUnitDistrictResquest {
    public List<UnitDistrict> unitDistrictList;

    public boolean validate(){
      return !  CollectionUtils.isEmpty(unitDistrictList);
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class UnitDistrict{
        private Long unitId;
        private String province;
        private String city;
    }
}
