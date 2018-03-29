package com.suit.core.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SocketClient extends Thread {
    public PrintWriter writer;

    private Socket socket;

    private static Queue<String> messageQueen = new LinkedList<String>();

    public SocketClient() {
        try {
            // 1、创建客户端Socket，指定服务器地址和端口
            // Socket socket=new Socket("127.0.0.1",5200);
            socket = new Socket(InetAddress.getLocalHost(), 5209);
            System.out.println("客户端socket启动成功");
            // 2、获取输出流，向服务器端发送信息
            // 向本机的52000端口发出客户请求

            start();

        } catch (Exception e) {
            System.out.println("can not listen to:" + e);// 出错，打印出错信息
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
        writer.write("222");
        writer.flush();
    }


    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            // 由系统标准输入设备构造BufferedReader对象
            writer = new PrintWriter(socket.getOutputStream());

            writer.write("链接成功");
            writer.flush();

            // 由Socket对象得到输出流，并构造PrintWriter对象
            //3、获取输入流，并读取服务器端的响应信息
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 由Socket对象得到输入流，并构造相应的BufferedReader对象
            String readline;
            readline = br.readLine(); // 从系统标准输入读入一字符串
            while (!readline.equals("end")) {
                // 在系统标准输出上打印读入的字符串
                System.out.println("Server:" + in.readLine());



                // 从Server读入一字符串，并打印到标准输出上
                readline = br.readLine(); // 从系统标准输入读入一字符串

            }

            //4、关闭资源
            writer.close(); // 关闭Socket输出流
            in.close(); // 关闭Socket输入流
            socket.close(); // 关闭Socket
        } catch (Exception e) {
            System.out.println("can not listen to:" + e);// 出错，打印出错信息
        }

    }
}