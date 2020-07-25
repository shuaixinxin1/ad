package com.itcanteen.sponsor.entity.unit_condition;
import com.netflix.discovery.EurekaNamespace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/18 15:34
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "ad_unit_district")
public class AdUnitDistrict {


    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "unit_id",nullable = false)
    private Long unitId;

    @Column(name = "province",nullable = false)
    private String province;

    @Column(name = "city",nullable = false)
    private String city;


    public AdUnitDistrict(Long unitId, String province, String city) {
        this.unitId = unitId;
        this.province = province;
        this.city = city;
    }
}
