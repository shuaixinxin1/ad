package com.itcanteen.sponsor.entity.unit_condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/16 23:02
 */
@Entity
@Table(name = "ad_unit_keyword")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitkeyWord {

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unit_id",nullable = false)
    private Long unitId;

    @Column(name = "keyword",nullable = false)
    private String keyWord;
    public AdUnitkeyWord(Long unitId, String keyWord) {
        this.unitId = unitId;
        this.keyWord=keyWord;
    }

}
