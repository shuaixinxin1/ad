package com.itcanteen.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/18 11:05
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdUnitItRequest {
    public List<UnitIt> unitItList;

    public boolean validate(){
        return !CollectionUtils.isEmpty(unitItList);
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class UnitIt{
        private Long unitId;
        private String itTag;
    }
}
