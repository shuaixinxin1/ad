package com.itcanteen.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 10:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateAdUserResponse {

    private Long id;

    private String username;

    private String token;

    private Date createTime;

    private Date updateTime;
}
