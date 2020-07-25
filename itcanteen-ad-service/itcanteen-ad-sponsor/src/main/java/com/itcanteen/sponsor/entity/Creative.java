package com.itcanteen.sponsor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 21:16
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ad_creative")
public class Creative {
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type",nullable = false)
    private Integer type;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "material_Type",nullable = false)
    private Integer materialType;
    @Column(name = "height",nullable = false)
    private Integer height;
    @Column(name = "width",nullable = false)
    private Integer width;
    @Column(name = "size",nullable = false)
    private Integer size;
    @Column(name = "duration",nullable = false)
    private Integer duration;
    @Column(name = "audit_status",nullable = false)
    private Integer auditStatus;
    @Column(name = "user_id",nullable = false)
    private Long userId;
    @Column(name = "url",nullable = false)
    private String url;
    @Column(name = "create_time",nullable = false)
    private Date createTime;

    @Column(name = "update_time",nullable = false)
    private Date updateTime;

}
