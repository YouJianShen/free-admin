package com.suit.core.exception;

import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * Created by hsy on 14-3-12.
 */
public class StringUtils {
    public static String generateUuidString() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        System.out.println(str);
        System.out.println(temp);
        return temp;
    }

    /**
     * 生成指定位数的随机数字符串
     */
    public static String getRandomStr(int size) {
        if (size <= 0) {
            return "";
        }
        Random ran = new Random();
        StringBuffer numStr = new StringBuffer();
        for (int i = 0; i < size; i++) {
            int p = ran.nextInt(10);
            numStr.append(p);
        }
        return numStr.toString();
    }

    /**
     * 如果s为null则返回""，否则返回字符串本身 null2String
     *
     * @param s
     * @return
     */
    public static String nullToString(String s) {
        return (s != null ? s.trim() : "");
    }

    public static String join(Object[] o, String flag) {
        if (o == null) {
            return null;
        }
        StringBuffer str_buff = new StringBuffer();

        for (int i = 0, len = o.length; i < len; i++) {
            str_buff.append(String.valueOf(o[i]));
            if (i < len - 1) str_buff.append(flag);
        }

        return str_buff.toString();
    }

    public static String join(ArrayList a, String flag) {
        if (a.size() == 0) {
            return null;
        }
        StringBuffer str_buff = new StringBuffer();

        for (int i = 0, len = a.size(); i < len; i++) {
            str_buff.append(String.valueOf(a.get(i)));
            if (i < len - 1) str_buff.append(flag);
        }

        return str_buff.toString();
    }

    private static final String PRE_FIX_UTF = "&#x";
    private static final String POS_FIX_UTF = ";";


    public static String XmlFormalize(String sTemp) {
        StringBuffer sb = new StringBuffer();

        if (sTemp == null || sTemp.equals("")) {
            return "";
        }
        String s = StringUtils.TranEncodeTOGB(sTemp);
        for (int i = 0; i < s.length(); i++) {
            char cChar = s.charAt(i);
            if (StringUtils.isGB2312(cChar)) {
                sb.append(PRE_FIX_UTF);
                sb.append(Integer.toHexString(cChar));
                sb.append(POS_FIX_UTF);
            } else {
                switch ((int) cChar) {
                    case 32:
                        sb.append("&#32;");
                        break;
                    case 34:
                        sb.append("&quot;");
                        break;
                    case 38:
                        sb.append("&amp;");
                        break;
                    case 60:
                        sb.append("&lt;");
                        break;
                    case 62:
                        sb.append("&gt;");
                        break;
                    default:
                        sb.append(cChar);
                }
            }
        }
        return sb.toString();
    }


    public static String TranEncodeTOGB(String str) {
        try {
            String strEncode = StringUtils.getEncoding(str);
            String temp = new String(str.getBytes(strEncode), "GB2312");
            return temp;
        } catch (java.io.IOException ex) {

            return null;
        }
    }


    public static boolean isGB2312(char c) {
        Character ch = new Character(c);
        String sCh = ch.toString();
        try {
            byte[] bb = sCh.getBytes("gb2312");
            if (bb.length > 1) {
                return true;
            }
        } catch (UnsupportedEncodingException ex) {
            return false;
        }
        return false;
    }


    public static String getEncoding(String str) {
        String encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }

        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }

    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String hmac_sha1(String key, String data) {
        byte[] byteHMAC = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec spec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            mac.init(spec);
            byteHMAC = mac.doFinal(data.getBytes());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException ignore) {
        }
        String oauth = new BASE64Encoder().encode(byteHMAC);
        return oauth;
    }

    public static void main(String args[]) throws UnsupportedEncodingException {
//        String data = "oauth_consumer_key=abcdefg&oauth_nonce=03ggdh&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1393468028&oauth_version=1.0";
//        System.out.println(hmac_sha1("abcdefg", data));
//        System.out.println(URLEncoder.encode(hmac_sha1("abcdefg", data), "UTF-8"));
        System.out.println(generateUuidString());
    }

}
