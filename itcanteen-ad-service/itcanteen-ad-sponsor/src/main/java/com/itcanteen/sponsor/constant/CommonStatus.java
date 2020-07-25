package com.itcanteen.sponsor.constant;

import lombok.Getter;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 16:48
 */
@Getter
public enum  CommonStatus {
    VALID(1,"有效状态"),
    INVALID(0,"无效状态");
    private Integer status;
    private String desc;

    CommonStatus(Integer status,String desc){
        this.status=status;
        this.desc=desc;
    }
}
