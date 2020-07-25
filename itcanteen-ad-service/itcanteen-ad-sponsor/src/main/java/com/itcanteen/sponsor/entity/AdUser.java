package com.itcanteen.sponsor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 08:01
 */
@Table(name="ad_user")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUser {
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "user_status",nullable = false)
    private Integer userStatus;

    @Column(name = "token",nullable = false)
    private String token;

    @Column(name = "create_time",nullable = false)
    private Date createTime;

    @Column(name = "update_time",nullable = false)
    private Date updateTime;

    public AdUser(String username,String token){
        this.username=username;
        this.token=token;
        this.createTime=new Date();
        this.updateTime=new Date();
    }
}
