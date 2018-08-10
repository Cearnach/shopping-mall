package com.xmut.osm.dto;

import lombok.Data;

import java.util.List;

/**
 * @author 阮胜
 * @date 2018/8/10 17:11
 */
@Data
public class TypeTemplateDTO {
    private Integer id;

    private String name;

    private List<Integer> specificationIdList;

    private List<Integer> brandIdList;

    private String customAttribute;
}
