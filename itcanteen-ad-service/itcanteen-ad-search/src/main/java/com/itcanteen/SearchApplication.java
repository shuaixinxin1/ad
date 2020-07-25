package com.itcanteen;

import com.itcanteen.search.vo.SearchRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/7/5 10:28
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class);
    }
}
