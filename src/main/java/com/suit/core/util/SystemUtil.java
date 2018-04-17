package com.suit.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.suit.core.socket.SocketClient;
import com.suit.core.socket.SocketService;
import com.suit.core.websocket.WsServer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suit.shiro.ShiroUser;

public class SystemUtil {
    private static final Logger logger = LoggerFactory.getLogger(SystemUtil.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

    private static ShiroUser user;

    public static InetAddress address;

    public static ShiroUser getUser() {
        return user;
    }

    //socket
    public static SocketService socketService;

    public static SocketClient socketClient;

    public SystemUtil() {
        socketService = new mySocketService();
//        socketClient = new SocketClient();

        //开启websocket
        WsServer s;
        s = new WsServer(8887);
        s.start();

        try {
            address = java.net.InetAddress.getLocalHost();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public class mySocketService extends SocketService {

        @Override
        public void onMessage(String message) {
            System.out.println(message);
        }
    }

    /**
     * 获取会话用户信息
     *
     * @return
     */
    public static ShiroUser getShiroUser() {
        user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user;
    }

    /**
     * 获取正式ip
     *
     * @param request
     * @return
     */
    public static String getClientIP(HttpServletRequest request) {
        String fromSource = "X-Real-IP";
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
            fromSource = "X-Forwarded-For";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            fromSource = "Proxy-Client-IP";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            fromSource = "WL-Proxy-Client-IP";
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteHost();
            fromSource = "request.getRemoteHost";
        }

        logger.error(fromSource + "--" + ip);
        return ip;
    }

    /**
     * 访问的HTTP请求的URL中，去掉contextRoot。
     * <p>
     * 首先重HttpServletRequest对象中获取ContentRoot，然后再从该对象中获取http请求的URI，
     * 最后从获取的URI字符串中去掉ContextRoot后，以字符串类型放回。
     *
     * @param request HttpServletRequest对象
     * @return String 返回的字符串中去掉了ContentRoot。
     */
    public static String removeContextRoot(HttpServletRequest request) {
        String contextRoot = request.getContextPath();
        String requestPath = request.getRequestURI();
        if (requestPath.contains(contextRoot)) {
            requestPath = requestPath.substring(contextRoot.length());
        }

        return requestPath;
    }

    /**
     * 密码加密
     *
     * @param originPass
     * @return
     */
    public static String encryPass(String originPass, String salt) {
        String newPassword = new SimpleHash(
                "md5",
                originPass,
                ByteSource.Util.bytes(salt),
                2).toHex();
        return newPassword;
    }

    public static void main(String[] args) {
        String newPassword = new SimpleHash(
                "md5",
                "123456zl",
                ByteSource.Util.bytes("3AFO6NO9QO79ZMSHD7UCI342WK8ZQZ8G"),
                2).toHex();

        System.out.println(newPassword);
    }

    // 获取下一个月，格式为yyyyMM
    public static int nextMonth(int month) throws ParseException {
        Date nowDate = sdf.parse(month + "");
        Calendar c = Calendar.getInstance();
        c.setTime(nowDate);
        c.add(Calendar.MONTH, 1);
        int nextmonth = Integer.parseInt(sdf.format(c.getTime()));
        return nextmonth;
    }

    /**
     * 获取ip地区
     *
     * @param request
     * @return
     */
    public static String getIpRegion(HttpServletRequest request) {
        //淘宝IP地址库：http://ip.taobao.com/instructions.php
        String add = null;
        String ip = getClientIP(request);
        try {
            //URL U = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=114.111.166.72");
            URL U = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            URLConnection connection = U.openConnection();
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            in.close();
            JSONObject jsonObject = JSONObject.fromObject(result);
            Map<String, Object> map = (Map) jsonObject;
            String code = String.valueOf(map.get("code"));//0：成功，1：失败。
            if ("1".equals(code)) {//失败
                String data = String.valueOf(map.get("data"));//错误信息
            } else if ("0".equals(code)) {//成功
                Map<String, Object> data = (Map<String, Object>) map.get("data");

                String country = String.valueOf(data.get("country"));//国家
                String area = String.valueOf(data.get("area"));//
                String city = String.valueOf(data.get("city"));//省（自治区或直辖市）
                String region = String.valueOf(data.get("region"));//市（县）
                add = country + "-" + city + "-" + region;
                return add;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

    /**
     * enum转jsonArray
     * @param _class
     * @return
     */
    public static JSONArray genEnumJOSN(Class _class){
        JSONArray array = new JSONArray();

        for (Field field: _class.getFields()){
            array.add(field.getName());
        }
        return array;
    }

}
