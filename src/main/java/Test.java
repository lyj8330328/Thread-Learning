//import java.net.Socket;
//import java.nio.ByteBuffer;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.Map;
//
///**
// * @Author: 98050
// * @Time: 2018-12-27 11:13
// * @Feature:
// */
//public class Test {
//
//    public void Recive() {
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
//                    closeSocket();
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
//                            Iterator<Map.Entry<Socket, cn.zs.xhp.syncsocket.SyncSocketDataThread>> threadArray = SyncSocketServer.existSocketChannel.entrySet().iterator();
//                            Object removeKey = null;
//                            while(threadArray.hasNext()){
//                                Map.Entry<Socket, cn.zs.xhp.syncsocket.SyncSocketDataThread> item = threadArray.next();
//                                //如果连接已断开，则跳过
//                                if (item.getKey().isClosed()){
//                                    continue;
//                                }
//                                cn.zs.xhp.syncsocket.SyncSocketDataThread value = item.getValue();
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
//                closeSocket();
//                break;
//            }
//        }
//    }
//}
