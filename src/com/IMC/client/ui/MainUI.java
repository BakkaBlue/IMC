package com.IMC.client.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI {
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private JTextField ipAddressField;
    private JButton connectButton;

    // 新增 CommunicationManager 实例
    private CommunicationManager communicationManager;

    public MainUI() {
        initialize();
    }

    public void show() {
        EventQueue.invokeLater(() -> {
            try {
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("IMC - Instant Messaging Client");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 主面板
        JPanel mainPanel = new JPanel();
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BorderLayout(0, 0));

        // 聊天记录区域
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // 输入消息区域
        JPanel inputPanel = new JPanel();
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        inputPanel.setLayout(new BorderLayout(0, 0));

        inputField = new JTextField();
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputField.setColumns(10);

        // 发送按钮
        JButton sendButton = new JButton("Send");
        inputPanel.add(sendButton, BorderLayout.EAST);
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 处理发送消息的逻辑
                String message = inputField.getText();
                if (!message.isEmpty()) {
                    sendMessage(message);
                    inputField.setText(""); // 清空输入框
                }
            }
        });

        // 连接区域
        JPanel connectionPanel = new JPanel();
        frame.getContentPane().add(connectionPanel, BorderLayout.NORTH);
        connectionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel ipAddressLabel = new JLabel("IP Address:");
        connectionPanel.add(ipAddressLabel);

        ipAddressField = new JTextField();
        connectionPanel.add(ipAddressField);
        ipAddressField.setColumns(10);

        connectButton = new JButton("Connect");
        connectionPanel.add(connectButton);
        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 处理连接按钮点击事件
                String ipAddress = ipAddressField.getText();
                if (!ipAddress.isEmpty()) {
                    connectToServer(ipAddress);
                }
            }
        });
    }

    // 新增连接到服务器的方法
    private void connectToServer(String ipAddress) {
        // 在这里处理连接到服务器的逻辑
        communicationManager = new CommunicationManager(ipAddress, 12345);
    }

    private void sendMessage(String message) {
        // 发送消息到服务器或对方客户端
        if (communicationManager != null) {
            communicationManager.sendMessage(message);
        }
    }

    public void appendMessage(String message) {
        // 更新聊天记录
        chatArea.append(message + "\n");
    }
}
