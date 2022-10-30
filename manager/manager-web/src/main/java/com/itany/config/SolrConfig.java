package com.itany.config;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SolrConfig.
 *
 * @author Thou
 * @date 2022/10/26
 */
@Configuration
public class SolrConfig {

    @Bean
    public HttpSolrClient solrClient() {
        return new HttpSolrClient.Builder("http://localhost:8080/solr/itanylife")
                .withConnectionTimeout(10000)
                .withSocketTimeout(10000)
                .build();
    }
}
