package com.itcanteen.sponsor.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;


import java.util.List;
/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/18 16:24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreativeUnitRequest {
    public List<CreativeUnitItem> creativeUnitItemList;

    public boolean validate(){
        return !CollectionUtils.isEmpty(creativeUnitItemList);
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class CreativeUnitItem{
        private Long unitId;
        private Long creativeId;
    }
}
