package com.suit.core.socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService extends Thread {

    //socket数
    private static int collectNum = 0;

    private ServerSocket server;

    private String line;

    Socket socket;

    private BufferedReader in;
    private PrintWriter writer;
    private BufferedReader br;

    public SocketService() {
        try {
            try {
                server = new ServerSocket(5209);
                //b)指定绑定的端口，并监听此端口。
                System.out.println("服务器启动成功");
                //创建一个ServerSocket在端口5209监听客户请求
            } catch (Exception e) {
                System.out.println("没有启动监听：" + e);
                //出错，打印出错信息
            }

            start();

        } catch (Exception e) {//出错，打印出错信息
            System.out.println("Error." + e);
        }
    }

    public void onMessage(String message) {

    }

    @Override
    public synchronized void start() {
        collectNum++;
        super.start();
    }

    @Override
    public void run() {
        try {

            socket = server.accept();

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //由Socket对象得到输入流，并构造相应的BufferedReader对象
            writer = new PrintWriter(socket.getOutputStream());
            //由Socket对象得到输出流，并构造PrintWriter对象
            br = new BufferedReader(new InputStreamReader(System.in));

            line = br.readLine();
            //从标准输入读入一字符串
            //4、获取输出流，响应客户端的请求
            while (!line.equals("end")) {
                //如果该字符串为 "bye"，则停止循环
                writer.println(line);
                //向客户端输出该字符串
                writer.flush();
                //刷新输出流，使Client马上收到该字符串
                System.out.println("Server:" + line);
                //在系统标准输出上打印读入的字符串
                System.out.println("Client:" + in.readLine());

                onMessage(in.readLine());
                //从Client读入一字符串，并打印到标准输出上
                line = br.readLine();
                //从系统标准输入读入一字符串

            } //继续循环

            //5、关闭资源
            writer.close(); //关闭Socket输出流
            in.close(); //关闭Socket输入流
            socket.close(); //关闭Socket
            server.close(); //关闭ServerSocket
            collectNum--;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}