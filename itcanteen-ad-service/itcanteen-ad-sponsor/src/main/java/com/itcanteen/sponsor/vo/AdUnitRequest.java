package com.itcanteen.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 20:35
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdUnitRequest {
    private Long planId;
    private String unitName;
    private Integer positionType;
    private Long budget;

    public boolean createValidate(){
        return null!=planId
                && ! StringUtils.isEmpty(unitName)
                && null != positionType
                && null != budget;
    }
}
