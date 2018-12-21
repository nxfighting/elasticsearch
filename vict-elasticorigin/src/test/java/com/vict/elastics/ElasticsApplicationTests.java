package com.vict.elastics;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ElasticsApplicationTests {

    public static void main(String[] args) throws UnknownHostException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("cluster.name", "gf-cluster");
        map.put("name", "master");
        Settings settings = Settings.settingsBuilder().put(map).build();
        Client client = TransportClient.builder().settings(settings)
                .build().addTransportAddress(
                        new InetSocketTransportAddress(
                                InetAddress.getByName("47.96.93.75"), 9300));
        indexGet(client);
    }
    public static void indexGet(Client client ) {
        SearchResponse res = null;
        res = client.prepareSearch("searchkk")
                .setTypes("articles").get();
       //System.out.println(res);
        // on shutdown
        client.close();
    }
}

