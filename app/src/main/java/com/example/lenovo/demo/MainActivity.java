package com.example.lenovo.demo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.text_id);
        tv.setText("first activity");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });

        String processName = getProcessName();
        String processName1 = getProcessName(this,android.os.Process.myPid());
        Log.d(TAG,"onCreate " + processName + " == " + processName1 + " == pid:" + android.os.Process.myPid() +  " == tid：" +  android.os.Process.myTid());
//        try {
//            return;
//        } catch (Error e){
//
//        } catch (Exception e) {
//
//        }
//         finally {
//           int  aa = 0;
//        }


        ImageView imageView = (ImageView) findViewById(R.id.img_id);
        final  ImageView imageView1 = (ImageView) findViewById(R.id.img_id1);
//        ImageView imageView2 = (ImageView) findViewById(R.id.img_id2);
       final Bitmap originBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.lbjn);
//        imageView.setImageBitmap(originBitmap);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        final Bitmap sampleBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.lbjn,options);
        imageView1.setImageBitmap(originBitmap);
//        imageView1.setBackgroundColor(Color.BLUE);

        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 0.5f);
        final Bitmap scaledBitMap = Bitmap.createBitmap(originBitmap, 0, 0, originBitmap.getWidth(),
                originBitmap.getHeight(), matrix, true);

        imageView1.postDelayed(new Runnable() {
            @Override
            public void run() {

                Log.d(TAG," == " +  sampleBitmap.getWidth() + " == " + sampleBitmap.getHeight() + " == " + sampleBitmap.getByteCount() +
                        " == " +  scaledBitMap.getWidth() + " == " + scaledBitMap.getHeight() + " == " + scaledBitMap.getByteCount()
                        + " == " + originBitmap.getWidth() + " == " + originBitmap.getHeight() + " == " + originBitmap.getByteCount()
                        + " == " + imageView1.getWidth() + " == " + imageView1.getHeight());
            }
        },1000);

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
    protected void onResume() {
        super.onResume();

//        for (int i = 0; i< 5; i++) {
//            startService(new Intent(MainActivity.this,DemoService.class));
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int ss = ev.getAction();
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int ss = event.getAction();
        return super.onTouchEvent(event);
    }
}
