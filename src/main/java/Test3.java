import java.util.*;

/**
 * @Author: 98050
 * @Time: 2018-12-27 12:28
 * @Feature:
 */
public class Test3 {

    public static void main(String[] args){
        String data = "10 06 1C 01 A2 40 07 D0 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 32 CF ";
        String result = "";
        String[] temp = data.split(" ");
        for (String s : temp){
            result += s;
        }
        System.out.println(result);
        //获取命令码
        String cmdType = result.substring(8, 10).toUpperCase();
        String recvData = result.substring(10, result.length() - 4).toUpperCase();
        if ("A2".equals(cmdType)){
           parseCmdA2(recvData);
        }
    }

    public static void parseCmdA2(String data){
        List<String> dataList = new ArrayList<String>();
        System.out.println("获取到实时数据，马上进行解析："+data);
        if (data.length() > 0){
            String len = data.substring(0, 2);
            data = data.substring(2);
            //解析接收到的数据内容
            for (int i=0; i<data.length(); ){
                int stepLen = 4;
                //每4个字符串为一组
                String cData = data.substring(i, i + stepLen);
                //7FFF表示无效数据
                if (!"7FFF".equals(cData)){
                    //16进制转2进制
                    String binStr =hexStr2Byte(cData);
                    String firstStr = binStr.substring(0, 1);
                    String rData = "";
                    //判断2进制第一个字节是否为1，1表示该数据为负数，0表示未正数
                    if ("1".equals(firstStr)){
                        //如果结果是负数，则需要先将原值的二进制取反并加1
                        long d = Long.parseLong(binStr, 2);
                        String tmp = Long.toBinaryString(~d+1);
                        tmp = tmp.substring(tmp.length() - 16);
                        //得到转换后的值
                        rData = String.valueOf(-Long.parseLong(tmp, 2));
                    }else{
                        rData = String.valueOf(Long.parseLong(binStr, 2));
                    }
                    dataList.add(rData);
                }else{
                    dataList.add("");
                }
                i += stepLen;
            }

            try{

                //向数据库中写入16组通道实时数据。再写入每组通道数据时，根据配置的通道精度，做一次数据处理
                for (int i=0; i<16; i++){
                    String dataVal = dataList.get(i);
                    if (i == 0){
                        System.out.println("通道" + (i+1) + ":" + Long.parseLong(dataVal) / 100);
                    }else if (i == 1){
                        System.out.println("通道" + (i+1) + ":" + Long.parseLong(dataVal) / 10);
                    }else {
                        System.out.println("通道" + (i+1) + ":" + Long.parseLong(dataVal));
                    }


                }

            }catch(Exception e){
                e.printStackTrace();
                System.out.println("数据库写入失败："+data);
            }

        }
    }


    public static String hexStr2Byte(String hex) {
        StringBuilder result = new StringBuilder();
        Map<Character,String> map = new HashMap<Character, String>(16);
        map.put('0', "0000");
        map.put('1', "0001");
        map.put('2', "0010");
        map.put('3', "0011");
        map.put('4', "0100");
        map.put('5', "0101");
        map.put('6', "0110");
        map.put('7', "0111");
        map.put('8', "1000");
        map.put('9', "1001");
        map.put('A', "1010");
        map.put('B', "1011");
        map.put('C', "1100");
        map.put('D', "1101");
        map.put('E', "1110");
        map.put('F', "1111");
        for (int i = 0; i < hex.length(); i++) {
           result.append(map.get(hex.charAt(i)));
        }

        return result.toString();
    }




}
