package com.vict.elastics.test;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.network.InetAddresses;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * @author
 * @create 2018-12-19 10:42
 */
public class Elstest {
    private static TransportClient transPort = null;
    private static String esClusterName="gf-cluster";//集群名
    private static String esServerIps="47.96.93.75";//集群服务IP集合
    private static Integer esServerPort=9300;//ES集群端口

    public static void main(String [] args){
        getTransPortClient();
    }


    /**
     *  ES TransPortClient 客户端连接<br>
     *  在elasticsearch平台中,可以执行创建索引,获取索引,删除索引,搜索索引等操作
     * @return
     */
    public static  TransportClient getTransPortClient() {
        try {
            if (transPort == null) {

                if(esServerIps == null || "".equals(esServerIps.trim())){
                    return  null;
                }

                Settings settings = Settings.builder()
//                      .put("cluster.name", esClusterName)// 集群名
                        .put("client.transport.sniff", true)
                        // 自动把集群下的机器添加到列表中

                        .build();
                transPort  = new PreBuiltTransportClient(settings);
                String esIps[] = esServerIps.split(",");
                for (String esIp : esIps) {//添加集群IP列表
                    TransportAddress transportAddress =  new InetSocketTransportAddress(InetAddresses.forString(esIp),9300);
                    transPort.addTransportAddresses(transportAddress);
                }
                return transPort;
            } else {
                return transPort;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (transPort != null) {
                transPort.close();
            }
            return null;
        }
    }
}
