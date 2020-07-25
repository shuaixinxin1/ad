package com.itcanteen.sponsor.service;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.vo.CreaticeRequest;
import com.itcanteen.sponsor.vo.CreativeResponse;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 21:25
 */
public interface ICreativeService {
    /**
     * 创建创意
     */
    CreativeResponse create(CreaticeRequest request) throws AdException;
}
