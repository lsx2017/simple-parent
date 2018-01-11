package cn.simple.elastic;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;



public class ElasticsearchClient {

    private TransportClient client;

    public ElasticsearchClient(String ipAddress, Integer port){
        Settings settings = Settings.settingsBuilder().build();
        try {
            this.client = TransportClient.builder().settings(settings).build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ipAddress), port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void createIndex(){
//        IndexResponse response = client.prepareIndex("twitter", "tweet", "1")
//                .setSource(jsonBuilder()
//                        .startObject()
//                        .field("user", "kimchy")
//                        .field("postDate", new Date())
//                        .field("message", "trying out Elasticsearch")
//                        .endObject()
//                )
//                .get();
        client.admin().indices().create(new CreateIndexRequest("")).actionGet();
    }

    public void deleteIndex(){
        IndicesExistsResponse indicesExistsResponse = client.admin().indices().exists(new IndicesExistsRequest(new String[]{})).actionGet();
        if (indicesExistsResponse.isExists()){
            client.admin().indices().delete(new DeleteIndexRequest("")).actionGet();
        }
    }

    public void deleteType() {
        client.prepareDelete().setIndex("").setType("").execute().actionGet();
    }

    public void defineIndexTypeMapping(){

    }
}
