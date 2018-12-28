/**
 * @Author: 98050
 * @Time: 2018-12-28 11:52
 * @Feature:
 */
public class Test4 {

    public static void main(String[] args) {
        String data = "10 06 1C 01 A2 40 07 D0 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
        String result = "";
        String[] temp = data.split(" ");
        for (String s : temp){
            result += s;
        }
        System.out.println(get_crc16(hexStringToBytes(result)));
        System.out.println(Integer.toHexString(-1 & 0xFF));
        byte[] bytes = new byte[]{-1};
        System.out.println(toHex(bytes));
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


    public static String get_crc16 (byte[] bufData){
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;
        int i, j;
        int buflen = bufData.length;
        byte[] pcrc = new byte[3];

        if (buflen == 0)  {
            return "";
        }

        for (i = 0; i < buflen; i++){
            CRC ^= ((int)bufData[i] & 0x000000ff);
            for (j = 0; j < 8; j++)
            {
                if ((CRC & 0x00000001) != 0)
                {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                }
                else
                {
                    CRC >>= 1;
                }
            }
        }

        pcrc[0] = (byte)(CRC & 0x00ff);
        pcrc[1] = (byte)(CRC >> 8);
        //字节低前高后组合
        String crc = Integer.toHexString((int)pcrc[0] & 0x000000ff) + Integer.toHexString((int)pcrc[1] & 0x000000ff);
        return crc.toUpperCase();
    }

    /**
     * 这里做&0xff的原因是：
     * 1.byte是1byte（8位），int是4byte(32位)表示的。
     * 2.Java中是使用了补码的形式进行数据存储的。
     * 3.java中byte数据转化为int数据时会自动补位，如果最高位（符号位）是0，则高24位全部补0，若是1，则高24位全部补1。
     * 4.知道了上面的三点，就有：
     * byte -1的二进制为（补码）：1111 1111 –>对应的16进制为0xFF
     * int -1的二进制为（补码）：1111 1111 1111 1111 1111 1111 1111 1111 –>对应的16进制为0xFFFFFFFF
     * 5.Integer.toHexString(int i)函数内部是通过传进来数字的二进制（补码形式）来进行转换的，因此如果不进行int v = src[i] & 0xFF;
     * 则得到的结果就是0xFFFFFFFF。而&0xff只后，传入的参数的二进制就变为0000 0000 0000 0000 0000 0000 1111 1111（虽然这个数的值以不再是-1，但是将他进行转换得到的0xff才是我们需要的）
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    private static final char[] HEX = "0123456789abcdef".toCharArray();
    public static String toHex(byte[] bytes) {

        if ( bytes == null ) {
            return null;
        }

        char[] chs = new char[bytes.length << 1];

        for ( int i = 0 , k = 0 ; i < bytes.length ; i++ ) {
            chs[k++] = HEX[(bytes[i] & 0xf0) >>> 4];
            chs[k++] = HEX[(bytes[i] & 0x0f)];
        }

        return new String(chs);
    }
}
