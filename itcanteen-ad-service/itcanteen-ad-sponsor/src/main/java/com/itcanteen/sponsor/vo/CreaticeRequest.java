package com.itcanteen.sponsor.vo;

import com.itcanteen.sponsor.constant.CommonStatus;
import com.itcanteen.sponsor.entity.Creative;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 21:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreaticeRequest {

    private Long id;
    private Integer type;
    private String name;
    private Integer materialType;
    private Integer height;
    private Integer width;
    private Integer size;
    private Integer duration;
    private Integer auditStatus;
    private Long userId;
    private String url;
    private Date createTime;
    private Date updateTime;

    public boolean createValidate(){
        return !StringUtils.isEmpty(name)
                && null!=type
                && null!=materialType;
    }
    public Creative toEntity(){
        Creative creative = new Creative();
        creative.setName(name);
        creative.setWidth(width);
        creative.setUrl(url);
        creative.setType(type);
        creative.setMaterialType(materialType);
        creative.setHeight(height);
        creative.setDuration(duration);
        creative.setAuditStatus(CommonStatus.VALID.getStatus());
        creative.setCreateTime(new Date());
        return creative;
    }
}
