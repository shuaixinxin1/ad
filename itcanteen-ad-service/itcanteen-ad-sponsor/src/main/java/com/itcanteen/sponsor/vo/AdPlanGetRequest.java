package com.itcanteen.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 18:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlanGetRequest {

    public Long userId;
    public List<Long> ids;

    public boolean validate(){
        return null!=userId && !CollectionUtils.isEmpty(ids);
    }

 }
