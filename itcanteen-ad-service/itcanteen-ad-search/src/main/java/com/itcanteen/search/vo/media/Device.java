package com.itcanteen.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/7/4 21:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Device {
    //设备id
    private String deviceCode;
    //mac
    private String mac;
    //ip
    private String ip;
    //机型编码
    private String model;
}
