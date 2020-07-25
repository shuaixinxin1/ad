package com.itcanteen.search.vo.media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/7/4 20:51
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class App {
    private String appCode;
    private String appName;
    //通过安卓的app时候  有包名的  www.baidu.com
    //安卓的activity
    private String packageName;
    private String activityName;

}
