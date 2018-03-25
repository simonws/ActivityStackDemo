package com.example.lenovo.demo;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by lenovo on 2018/3/24.
 */

public class SocketDemoActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socket_activity);
        Button sendBtn = (Button) findViewById(R.id.send);
        sendBtn.setOnClickListener(SocketDemoActivity.this);
        Button connectBtn = (Button) findViewById(R.id.connect);
        connectBtn.setOnClickListener(this);

    }


    public static String getProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }


    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.send:
               send(v);
               break;
           case  R.id.connect:
               conn(v);
               break;
            default:break;
       }

    }


    private Socket socket;
    private String ipString = "127.0.0.1";

    /**
     * 建立服务端连接
     */
    public void conn(View v) {
        new Thread() {

            @Override
            public void run() {

                try {
                    socket = new Socket(ipString, 9999);
                    Log.e("JAVA", "建立连接：" + socket.isConnected());
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 发送消息
     */
    public void send(View v) {
        new Thread() {
            @Override
            public void run() {

                try {
                    // socket.getInputStream()
                    DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
                    writer.writeUTF("嘿嘿，你好啊，服务器.."); // 写一个UTF-8的信息

                    System.out.println("发送消息");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
