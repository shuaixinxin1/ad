package com.itcanteen.sponsor.entity.unit_condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/18 11:21
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ad_unit_it")
public class AdUnitIt {


    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unit_id",nullable = false)
    private Long unitId;

    @Column(name = "it_tag",nullable = false)
    private String itTag;


    public AdUnitIt(Long unitId, String itTag) {
        this.unitId = unitId;
        this.itTag = itTag;
    }
}
