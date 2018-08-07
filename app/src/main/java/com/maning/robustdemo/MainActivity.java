package com.maning.robustdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.meituan.robust.Patch;
import com.meituan.robust.PatchExecutor;
import com.meituan.robust.RobustCallBack;
import com.meituan.robust.patch.annotaion.Modify;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnFixBug(View view) {
        if (PermissionUtils.isGrantSDCardReadPermission(this)) {
            startFixBug();
        } else {
            PermissionUtils.requestSDCardReadPermission(this, 10010);
        }
    }

    private void startFixBug() {
        Toast.makeText(this, "开始修复补丁", Toast.LENGTH_SHORT).show();
        //插入补丁
        new PatchExecutor(getApplicationContext(), new PatchesRobustInfoImpl(), new RobustCallBack() {

            @Override
            public void onPatchListFetched(boolean result, boolean isNet, List<Patch> patches) {
                System.out.println(" robust arrived in onPatchListFetched");
            }

            @Override
            public void onPatchFetched(boolean result, boolean isNet, Patch patch) {
                System.out.println(" robust arrived in onPatchFetched");
            }

            @Override
            public void onPatchApplied(boolean result, Patch patch) {
                System.out.println(" robust arrived in onPatchApplied ");

            }

            @Override
            public void logNotify(String log, String where) {
                System.out.println(" robust arrived in logNotify " + where);
            }

            @Override
            public void exceptionNotify(Throwable throwable, String where) {
                throwable.printStackTrace();
                System.out.println(" robust arrived in exceptionNotify " + where);
            }
        }).start();
    }

    @Modify
    public void btnOther(View view) {
        testToast();
//        startActivity(new Intent(this, SecondActivity.class));
    }

    private void testToast() {
        Toast.makeText(this, "BugFix", Toast.LENGTH_SHORT).show();
    }


}
