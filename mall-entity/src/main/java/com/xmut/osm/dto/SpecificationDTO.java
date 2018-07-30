package com.xmut.osm.dto;

import com.xmut.osm.entity.SpecificationOption;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * @author 阮胜
 * @date 2018/7/31 0:10
 */
@Data
public class SpecificationDTO implements Serializable {
    private Integer id = 0;
    @NotBlank
    private String name;
    List<SpecificationOption> specificationOptionList;

}
