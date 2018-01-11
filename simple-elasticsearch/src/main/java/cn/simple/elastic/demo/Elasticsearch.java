package cn.simple.elastic.demo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

public class Elasticsearch {
    private Client client;
    private IndicesAdminClient adminClient;

    /**
     * 集群配置初始化方法
     * @throws Exception
     */
    private void init() throws Exception {
        Properties properties = readElasticsearchConfig();
        String clusterName = properties.getProperty("clusterName");
        String[] inetAddresses = properties.getProperty("hosts").split(",");
        Settings settings = Settings.settingsBuilder().build();
              //  Settings.settingsBuilder().put("cluster.name", clusterName).build();
        client = TransportClient.builder().settings(settings)
                .build();
        for (int i = 0; i < inetAddresses.length; i++) {
            String[] tmpArray = inetAddresses[i].split(":");
            String ip = tmpArray[0];
            int port = Integer.valueOf(tmpArray[1]);
            client = ((TransportClient) client).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), port));
        }
    }

    public TransportClient getAdminClient() throws Exception {
        Settings settings = Settings.settingsBuilder().build();
        //  Settings.settingsBuilder().put("cluster.name", clusterName).build();

       return ((TransportClient) client).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9200));


    }

    /**
     * 构造方法
     */
    public Elasticsearch() {
        try {
            init();
        } catch (Exception e) {
            System.out.println("init() exception!");
            e.printStackTrace();
        }
        adminClient = client.admin().indices();
    }

    /**
     * 判断集群中{index}是否存在
     *
     * @param index
     * @return 存在（true）、不存在（false）
     */
    public boolean indexExists(String index) {
        IndicesExistsRequest request = new IndicesExistsRequest(index);
        IndicesExistsResponse response = adminClient.exists(request).actionGet();
        if (response.isExists()) {
            return true;
        }
        return false;
    }

    /**
     * 读取es配置信息
     *
     * @return
     */
    private Properties readElasticsearchConfig() {
        Properties properties = new Properties();
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("elasticsearch.properties");

            properties.load(new InputStreamReader(is, "UTF-8"));
        } catch (IOException e) {
            System.out.println("readEsConfig exception!");
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * 读取json配置文件
     *
     * @return JSONArray
     * @throws IOException
     */
    public JSONArray readJosnFile() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("index.json");
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuffer sb = new StringBuffer();
        String tmp = null;
        while ((tmp = br.readLine()) != null) {
            sb.append(tmp);
        }
        JSONArray result = JSONArray.parseArray(sb.toString());
        return result;
    }

    /**
     * 获取要创建的index列表
     *
     * @param client
     * @return List<Index>
     */
    public List<Index> getIndexList() {

        List<Index> result = new ArrayList<Index>();
        JSONArray jsonArray = null;
        try {
            jsonArray = readJosnFile();
        } catch (IOException e) {
            System.out.println("readJsonFile exception!");
            e.printStackTrace();
        }
        if (jsonArray == null || jsonArray.size() == 0) {
            return null;
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Index indexObject = new Index();
            String index = jsonObject.getString("index");
            String type = jsonObject.getString("type");
            Integer number_of_shards = jsonObject.getInteger("number_of_shards");
            Integer number_of_replicas = jsonObject.getInteger("number_of_replicas");
            String fieldRType = jsonObject.get("fieldType").toString();
            indexObject.setIndex(index);
            indexObject.setType(type);
            indexObject.setFieldJson(fieldRType);
            indexObject.setNumber_of_shards(number_of_shards);
            indexObject.setNumber_of_replicas(number_of_replicas);
            result.add(indexObject);
        }
        return result;
    }

    /**
     * 创建Index
     *
     * @param
     */
    public void CreateIndex() {
        int i = 0;
        List<Index> list = getIndexList();
        IndicesAdminClient adminClient = client.admin().indices();
        for (Index index : list) {
//            if (indexExists(index.getIndex())) {
//                continue;
//            }

            adminClient.prepareCreate(index.getIndex())
                    .setSettings(Settings.builder().put("index.number_of_shards", index.getNumber_of_shards()).put("index.number_of_replicas", index.getNumber_of_replicas()))
                    .addMapping(index.getType(), index.getFieldJson())
                    .get();
            i++;
        }
        System.out.println("index creation success! create " + i + " index");

    }

    public static void main(String[] args) throws Exception{
        Elasticsearch es = new Elasticsearch();
//        es.CreateIndex();
        Client client = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9200));
        IndexResponse response = client.prepareIndex("blog", "article").setSource("git简介").get();
        if (response.isCreated()) {
            System.out.println("创建成功!");
        }
    }
//
//    public static List<String> getInitJsonData() {
//        List<String> list = new ArrayList<String>();
//        String data1 = JsonUtil.model2Json(new Blog(1, "git简介", "2016-06-19", "SVN与Git最主要的区别..."));
//        String data2 = JsonUtil.model2Json(new Blog(2, "Java中泛型的介绍与简单使用", "2016-06-19", "学习目标 掌握泛型的产生意义..."));
//        String data3 = JsonUtil.model2Json(new Blog(3, "SQL基本操作", "2016-06-19", "基本操作：CRUD ..."));
//        String data4 = JsonUtil.model2Json(new Blog(4, "Hibernate框架基础", "2016-06-19", "Hibernate框架基础..."));
//        String data5 = JsonUtil.model2Json(new Blog(5, "Shell基本知识", "2016-06-19", "Shell是什么..."));
//        list.add(data1);
//        list.add(data2);
//        list.add(data3);
//        list.add(data4);
//        list.add(data5);
//        return list;
//    }
}
