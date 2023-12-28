package com.IMC.client.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CommunicationManager {
    private Socket socket;
    private PrintWriter clientOut;
    private BufferedReader clientIn;

    public CommunicationManager(String opponentIpAddress, int port) {
        try {
            // 创建ServerSocket用于接收连接
            ServerSocket serverSocket = new ServerSocket(port);

            // 在后台启动线程监听连接
            new Thread(() -> {
                try {
                    // 等待客户端连接
                    socket = serverSocket.accept();
                    // 获取输入输出流
                    clientOut = new PrintWriter(socket.getOutputStream(), true);
                    clientIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    // 启动接收消息的线程
                    receiveMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // 连接到对方客户端
            socket = new Socket(opponentIpAddress, port);
            // 获取输入输出流
            clientOut = new PrintWriter(socket.getOutputStream(), true);
            clientIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 启动接收消息的线程
            receiveMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        // 发送消息到对方客户端
        clientOut.println(message);
    }

    private void receiveMessages() {
        try {
            while (true) {
                // 接收对方客户端的消息并处理
                String opponentMessage = clientIn.readLine();
                if (opponentMessage != null) {
                    // 在这里处理接收到的消息，可以调用业务逻辑层的方法处理
                    // 例如：MainLogic.handleReceivedMessage(opponentMessage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            // 关闭连接
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
