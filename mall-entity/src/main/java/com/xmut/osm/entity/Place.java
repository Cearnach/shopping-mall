package com.xmut.osm.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 地方表
 *
 * @author 阮胜
 * @date 2018/7/22 10:19
 */
@Data
@Entity
@DynamicInsert
public class Place implements Serializable {
    private static final long serialVersionUID = 1309791036493161622L;

    @Id
    @Column(name = "place_id")
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private State state;

    @ManyToOne
    private Province province;

    @ManyToOne
    private City city;

    @ManyToOne
    private District district;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private Date createDate;


    public Place() {
    }

    public Place(State state, Province province, City city, District district) {
        this.state = state;
        this.province = province;
        this.city = city;
        this.district = district;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(state.getName());
            sb.append(" ");
            sb.append(province.getName());
            sb.append(" ");
            sb.append(city.getName());
            sb.append(" ");
            sb.append(district.getName());
        } catch (Exception e) {
            //ignore
        }
        return sb.toString();
    }
}