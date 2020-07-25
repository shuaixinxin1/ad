package com.itcanteen.sponsor.entity;

import com.itcanteen.sponsor.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 15:51
 */
@Entity
@Table(name = "ad_plan")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlan {
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id",nullable = false)
    private Long userId;

    @Column(name = "plan_name",nullable = false)
    private String planName;

    @Column(name = "plan_status",nullable = false)
    private Integer planStatus;


    @Column(name = "start_date",nullable = false)
    private Date startDate;

    @Column(name = "end_date",nullable = false)
    private Date endDate;

    @Column(name = "create_time",nullable = false)
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    public AdPlan(Long userId,String planName,
                  Date startDate,Date endDate){
        this.userId = userId;

        this.planName = planName;
        this.startDate = startDate;
        this.planStatus= CommonStatus.VALID.getStatus();
        this.endDate = endDate;
        this.updateTime = new Date();
        this.createTime = new Date();
    }

}
