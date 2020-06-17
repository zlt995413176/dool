package tech;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author zenglt
 * @date 2020/4/23 16:57
 */
@Slf4j
public class Main {

    public static void main(String[] args) throws Exception{

    }

    public static void demo1 () throws Exception{

        PenguinOuterClass.Penguin.Builder builder = PenguinOuterClass.Penguin.newBuilder();
        builder.setId(1);
        builder.setName("a");

        PenguinOuterClass.Penguin penguin = builder.build();
        byte[] bytes = penguin.toByteArray();

        PenguinOuterClass.Penguin penguinF = PenguinOuterClass.Penguin.parseFrom(bytes);
        System.err.println(penguinF.toString());
        System.err.println(Arrays.toString(bytes));
    }

    public static void demo2 () throws Exception{

        PenguinOuterClass.Shop.Builder shopBuilder = PenguinOuterClass.Shop.newBuilder();
        shopBuilder.setId(1);
        shopBuilder.setName("店铺");

        PenguinOuterClass.Penguin.Builder penguinBuilder = PenguinOuterClass.Penguin.newBuilder();
        penguinBuilder.setId(1);
        penguinBuilder.setName("企鹅");
        penguinBuilder.addShop(shopBuilder.build());

        System.err.println(penguinBuilder.build().getShop(0).getName());
    }

    public static void demo3() {

        PenguinOuterClass.Penguin.Builder penguinBuilder = PenguinOuterClass.Penguin.newBuilder();
        penguinBuilder.setId(1);
        penguinBuilder.setName("调度");
        penguinBuilder.setEmail("123@qq.com");

        PenguinOuterClass.Shop.Builder shopBuilder = PenguinOuterClass.Shop.newBuilder();
        shopBuilder.setId(1);
        shopBuilder.setName("调度店铺");

        penguinBuilder.addShop(shopBuilder);

        PenguinOuterClass.Penguin penguin = penguinBuilder.build();

        JSONObject json = new JSONObject();
        json.put("datas", penguin.toByteArray());
        System.err.println(Arrays.toString(penguin.toByteArray()));
        doPost("http://127.0.0.1:8080/penguin", json);
    }

    public static JSONObject doPost(String url, JSONObject json){

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSON.parseObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public static void demo4() {

        OrgSerializable.Org.Builder builder = OrgSerializable.Org.newBuilder();
        builder.setId(128);
        builder.setName("a");
        OrgSerializable.Org org = builder.build();

        byte[] orgBytes = org.toByteArray();

        System.err.println(Arrays.toString(orgBytes));
    }


    /**
     * 设备下发指令数据大小
     */
    public static void demo5() {
        String cmdStr = "{\"Value\":{\"Channel\":\"01\",\"OrderNo\":\"0620200427114137398633\"},\"IMEI\":\"865650047915091\",\"Cmd\":\"2004050701\",\"MsgId\":\"11290514\",\"Ts\":\"20200427114158\"}";
        byte[] bytesStr = cmdStr.getBytes();
        System.err.println("java序列化长度"+  bytesStr.length);

        CmdSerializable.Value value = CmdSerializable.Value.newBuilder()
                .setChannel("01")
                .setOrderNo("0620200427114137398633")
                .build();

        CmdSerializable.Cmd cmd = CmdSerializable.Cmd.newBuilder()
                .setIMEI("865650047915091")
                .setCmd("2004050701")
                .setMsgId("11290514")
                .setTs("20200427114158")
                .setValue(value)
                .build();

        byte[] bytesCmd = cmd.toByteArray();
        System.err.println("protobuf序列化长度"+  bytesCmd.length);
    }

    /**
     * 1M数据量
     * alijson 序列化时间比protobuf多7倍，数据量越大越比例越小
     */
    public static void demo6() throws Exception{

        StringBuilder sb = new StringBuilder();

        FileInputStream in = new FileInputStream(new File("E:/idea-source/dool/dool-biz/src/main/java/tech/a.txt"));
        byte[] bs = new byte[1024 * 8];
        int length = 0;
        while((length = in.read(bs)) != -1){
            sb.append(new String(bs, 0, length));
        }
        in.close();

        long a = System.currentTimeMillis();
        List<Test> objs = JSON.parseArray(sb.toString(), Test.class);
        long b = System.currentTimeMillis();
        System.err.println("json 时间"+(b-a));

        TestSerializable.Test.Builder builder = TestSerializable.Test.newBuilder();
        objs.forEach(obj -> {
            TestSerializable.Data data = TestSerializable.Data.newBuilder()
                    .setId(obj.getId())
                    .setName(obj.getName())
                    .build();
            builder.addData(data);
        });

        TestSerializable.Test test = builder.build();
        byte[] bytes = test.toByteArray();

        long c = System.currentTimeMillis();
        TestSerializable.Test.parseFrom(bytes);
        long d = System.currentTimeMillis();
        System.err.println("protobuf 时间"+(d-c));
    }
}
