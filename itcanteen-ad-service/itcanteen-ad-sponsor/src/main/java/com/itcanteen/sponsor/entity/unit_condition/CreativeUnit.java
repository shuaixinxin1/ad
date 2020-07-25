package com.itcanteen.sponsor.entity.unit_condition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/18 16:19
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "creative_unit")
public class CreativeUnit {
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unit_id",nullable = false)
    private Long unitId;

    @Column(name = "creative_id",nullable = false)
    private Long creativeId;

    public CreativeUnit(Long unitId, Long creativeId) {
        this.unitId = unitId;
        this.creativeId = creativeId;
    }
}
