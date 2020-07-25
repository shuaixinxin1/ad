package com.itcanteen.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlanRequest {



    private Long id;

    private Long userId;

    private String planName;

    private String startDate;

    private String endDate;

    public boolean validate(){
        return null !=userId
                &&!StringUtils.isEmpty(planName)
                &&!StringUtils.isEmpty(startDate)
                &&!StringUtils.isEmpty(endDate);
    }

    public boolean updateValidate(){
        return null!= userId && null!=id;
    }

    public boolean delValidate(){
        return null!= userId && null!=id;
    }
}
