package com.itcanteen.sponsor.service;

import com.itcanteen.common.exception.AdException;
import com.itcanteen.sponsor.vo.*;


/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 20:42
 */
public interface  IUnitService {
    /**
     * 创建推广单元
     */
    AdUnitResponse createAdUnit(AdUnitRequest request) throws AdException;

    /**
     * 增加推广单元关键词的限制
     */
    AdUnitkeyWordResponse createUnitKeyWords (AdUnitkeyWordRequest request) throws AdException;

    /**
     * 增加推广单元兴趣的限制
     */

    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException;

    /**
     *郑家推广单元地域限制
     * @param resquest
     * @return
     * @throws AdException
     */

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictResquest resquest)throws AdException;

    /**
     * 中间件的添加
     * @param request
     * @return
     * @throws AdException
     */
    public CreativeUnitResponse creativeUnit(CreativeUnitRequest request)throws AdException;
}
