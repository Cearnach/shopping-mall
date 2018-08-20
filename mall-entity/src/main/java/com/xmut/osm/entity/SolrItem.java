package com.xmut.osm.entity;

import lombok.Data;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Id;

/**
 * @author 阮胜
 * @date 2018/8/20 17:06
 */
@Data
@SolrDocument(solrCoreName = "new_core")
public class SolrItem {
    @Id
    @Indexed(name = "item_solr_id", type = "string")
    private String id;

    @Indexed(name = "item_solr_name", type = "string")
    private String name;
}
