package com.itcanteen.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 10:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdUserRequest  {

    String username;


    public boolean valisdate(){
        return !StringUtils.isNotEmpty(username);
    }
    public boolean validate(){
        return !StringUtils.isEmpty(username);
    }

}
