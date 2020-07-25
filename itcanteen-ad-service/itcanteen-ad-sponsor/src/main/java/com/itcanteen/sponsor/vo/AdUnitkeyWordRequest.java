package com.itcanteen.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/17 20:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitkeyWordRequest {
    List<UnitKeyWord> unitKeyWordList;

    public boolean validate(){
        return !CollectionUtils.isEmpty(unitKeyWordList);
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnitKeyWord{

        private Long unitId;
        private String keyWord;
    }
}
