package com.soda.encode;



import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 了解java的编码格式
 * @author soda
 * @date 2021/7/3
 */
public class JavaEncodeTest {

    private String utf8 = "UTF-8";
    private String utf16 = "UTF16";
    private String gbk = "GBK";
    private String iso = "ISO-8859-1";

    public static void main(String[] args) throws Exception {
        char achar = '國';
        String str = "严";
        //以平台默认编码格式转换成字节序列
        byte[] bytes = str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            System.out.printf("%X", bytes[i]);
        }
        System.out.println();
        //以文件的编码方式读取数据
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\f\\Desktop\\test.txt"));
        int available = fileInputStream.available();
        System.out.println(available);
        byte[] by = new byte[available];
        fileInputStream.read(by);
        System.out.println(Charset.defaultCharset());
        String gbkStr = new String(by, "GBK");
        System.out.println(gbkStr.getBytes().length);

    }

}
