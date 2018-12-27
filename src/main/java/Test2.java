//package cn.zs.xhp.syncsocket;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.time.DateUtils;
//import sun.misc.CRC16;
//
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.net.Socket;
//import java.nio.ByteBuffer;
//import java.nio.channels.SocketChannel;
//import java.util.*;
//import java.util.Map.Entry;
//
//
//public class SyncSocketDataThread implements Runnable {
//
//    private Socket mSocket;
//
//    private SocketChannel mSChannel;
//
//    //当前设备的ID值
//    public String id = "";
//    //当前设备的ID值
//    public String headId = "";
//
//    //最后在线时间
//    public Date mCurrentDate = null;
//
//
//
//    /**
//     * 根据命令类型，拼接命令
//     *
//     * 设备 ID           4字节
//     * 命令域                                    1字节
//     *  0XA1          获取时间
//     *  0XA2          发送实时数据
//     *  0XA3          配置系统参数
//     * 数据长度域                            1字节
//     * 数据域                                    N字节
//     * 校验域                                   2字节
//     * @param
//     * @return
//     */
//
//
//    @Override
//    public void run() {
//        ByteBuffer buf = ByteBuffer.allocate(1024);
//        ByteBuffer head = ByteBuffer.allocate(1);
//        byte[] body = null;
//        while(true){
//            try{
//                //System.out.println("已连接上设备");
//                buf.clear();
//                head.clear();
//                body = null;
//                String data = "";
//                StringBuffer strBuf = new StringBuffer();
//                int numBytesRead = 0;
//                while((numBytesRead = mSChannel.read(buf)) != -1){
//                    if (numBytesRead == 0){
//                        Thread.sleep(1000);
//                        break;
//                    }
//                    buf.flip();
//                    body = new byte[buf.limit()];
//                    buf.get(body, 0,buf.limit());
//                    //strBuf.append(new String(buf.array(),0,buf.limit(),"GBK"));
//                    buf.clear();
//                }
//                //如果接收到的值为-1，表示连接已经断开，退出循环
//                if (numBytesRead == -1){
//                    System.out.println("断开连接");
//                    break;
//                }
//                if (body != null){
//                    data = Utils.byteToHexString(body);
//                    //心跳包：585048，5850484B4A，787068，7870686b6a
//                    if ("4C4F47".equals(data) || "4C4F47494E3A31303031".equals(data) ||
//                            "585048".equals(data) || "5850484B4A".equals(data) ||
//                            "787068".equals(data) || "7870686B6A".equals(data)){
//                        mCurrentDate = new Date();
//                        continue;
//                    }
//                    if (data.indexOf("4C4F47494E3A31303031") != -1){
//                        data = data.replaceAll("4C4F47494E3A31303031", "");
//                    }
//                    if (data.indexOf("5850484B4A") != -1){
//                        data = data.replaceAll("5850484B4A", "");
//                    }
//                    if (data.indexOf("7870686B6A") != -1){
//                        data = data.replaceAll("7870686B6A", "");
//                    }
//                    /*
//                     * 接收数据：
//                     * 设备ID
//                     * 10 06 1C 01
//                     * 命令码 。A1：终端获取服务器时间
//                     * A1
//                     * 命令中的
//                     * 06 指需要服务器返回年、月、日、时、分、秒 6 个数据
//                     * 05则返回年、月、日、时、分 5 个数据
//                     * 06
//                     * 校验码
//                     * 25 49
//                     * */
//                    System.out.println("主动上报接收数据："+data);
//                    try{
//                        mCurrentDate = new Date();
//                        //读取设备ID
//                        String idStr = data.substring(0, 8).toUpperCase();
//                        //2018年4月17日。新增系数调整参数功能，协议格式有变化，需要根据表头固定的010300AB来区分是该类型协议，再解析
//                        if ("010300AB".equals(idStr)){
//                            //获取行政区号
//                            String cmdType = data.substring(8, 12).toUpperCase();
//                            parseCmdAreaData(cmdType);
//                        }else{
//                            headId = idStr;
//                            String tmpId = Utils.bcd2Str(Utils.hexStringToBytes(idStr));
//                            id = tmpId;
//                            System.out.println("["+this.id+"]"+new Date().toLocaleString()+"-主动上报接收数据："+data);
//                            Iterator<Entry<Socket, SyncSocketDataThread>> threadArray = SyncSocketServer.existSocketChannel.entrySet().iterator();
//                            Object removeKey = null;
//                            while(threadArray.hasNext()){
//                                Entry<Socket, SyncSocketDataThread> item = threadArray.next();
//                                //如果连接已断开，则跳过
//                                if (item.getKey().isClosed()){
//                                    continue;
//                                }
//                                SyncSocketDataThread value = item.getValue();
//                                if (item.getKey() != this.mSocket && tmpId.equals(value.id)){
//                                    value.closeSocket();
//                                    removeKey = item.getKey();
//                                }
//                            }
//                            if (removeKey != null){
//                                SyncSocketServer.existSocketChannel.remove(removeKey);
//                            }
//                            //获取命令码
//                            String cmdType = data.substring(8, 10).toUpperCase();
//                            //获取实际数据
//                            String recvData = data.substring(10, data.length() - 4).toUpperCase();
//                            //2015年10月19日。发现有错误数据上传，如果遇到这种数据，就跳过
//                            if ("49".equals(cmdType)){
//                                continue;
//                            }
//                            if ("A1".equals(cmdType)){
//                                //终端获取服务器时间
//                                parseCmdA1(recvData);
//                            }else if ("A2".equals(cmdType)){
//                                //发送实时数据
//                                parseCmdA2(recvData);
//                            }else if ("A3".equals(cmdType)){
//                                //配置系统参数
//                                parseCmdA3(recvData);
//                            }
//                        }
//                    }catch(Exception e){
//                        System.out.println(headId+"主动上报数据解析错误["+e.getMessage()+"]");
//                    }
//
//                    //将返回数据回调给发送方
//                    if (mCallback != null){
//                        this.mCallback.callback(data);
//                    }
//                }
//
//                Thread.sleep(2000);
//            }catch(Exception e){
//                System.out.println(headId+"[连接已断开]");
//                break;
//            }
//        }
//    }
//
//
//
//
//    /**
//     * 终端上传的实时数据。
//     * 接收到终端数据后并不直接写入数据库，先写入缓存文件中，由定时任务去进行数据写入操作，减少频繁数据库交互
//     * @param data
//     */
//    public void parseCmdA2(String data){
//        List<String> dataList = new ArrayList<String>();
//        System.out.println("获取到实时数据，马上进行解析："+data);
//        if (data.length() > 0){
//            String len = data.substring(0, 2);
//            data = data.substring(2);
//            //解析接收到的数据内容
//            for (int i=0; i<data.length(); ){
//                int stepLen = 4;
//                //每4个字符串为一组
//                String cData = data.substring(i, i + stepLen);
//                //7FFF表示无效数据
//                if (!"7FFF".equals(cData)){
//                    //16进制转2进制
//                    String binStr = Utils.hexString2binaryString(cData);
//                    String firstStr = binStr.substring(0, 1);
//                    String rData = "";
//                    //判断2进制第一个字节是否为1，1表示该数据为负数，0表示未正数
//                    if ("1".equals(firstStr)){
//                        //如果结果是负数，则需要先将原值的二进制取反并加1
//                        long d = Long.parseLong(binStr, 2);
//                        String tmp = Long.toBinaryString(~d+1);
//                        tmp = tmp.substring(tmp.length() - 16);
//                        //得到转换后的值
//                        rData = String.valueOf(-Long.parseLong(tmp, 2));
//                    }else{
//                        rData = String.valueOf(Long.parseLong(binStr, 2));
//                    }
//                    dataList.add(rData);
//                }else{
//                    dataList.add("");
//                }
//                i += stepLen;
//            }
//
//            try{
//
//                //向数据库中写入16组通道实时数据。再写入每组通道数据时，根据配置的通道精度，做一次数据处理
//                for (int i=0; i<16; i++){
//                    String dataVal = dataList.get(i);
//                    System.out.println(dataVal);
//                }
//
//            }catch(Exception e){
//                e.printStackTrace();
//                System.out.println("数据库写入失败："+data);
//            }
//
//        }
//    }
//
//
//
//    public static void main(String[] args){
//        String data = "10 06 1C 01 A2 40 07 D0 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 32 CF ";
//        String[] temp = data.split(" ");
//        for (String s : temp){
//
//        }
//
//        String t = new String(Utils.hexStringToBytes(data));
//        System.out.println(StringUtils.leftPad(Integer.toHexString(500)+"", 4, "0"));
//
//    }
//}
