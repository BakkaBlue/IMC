package com.IMC.client;

import com.example.client.ui.MainUI;

public class ClientApp {
    private MainUI mainUI;

    public void start() {
        // 初始化客户端应用
        initialize();

        // 启动用户界面
        mainUI.show();
    }

    private void initialize() {
        // 创建主界面
        mainUI = new MainUI();
        // 在这里可以进行其他初始化工作
    }
}
