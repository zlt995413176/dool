package tech;


import java.util.Arrays;

/**
 * @author zenglt
 * @date 2020/4/29 15:12
 */
public class Proto {

    public static void main(String[] args) throws Exception{

        DemoSerializable.Demo.Builder builder = DemoSerializable.Demo.newBuilder();

        builder.setId(1);
        builder.setName("a");
        DemoSerializable.Demo demo = builder.build();

        byte[] bytes = demo.toByteArray();
        System.err.println(Arrays.toString(bytes));

        DemoSerializable.Demo obj = DemoSerializable.Demo.parseFrom(bytes);
        System.err.println(obj);
    }


}