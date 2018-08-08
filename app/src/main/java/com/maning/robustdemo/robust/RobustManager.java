package com.maning.robustdemo.robust;

import android.content.Context;

import com.meituan.robust.Patch;
import com.meituan.robust.PatchExecutor;
import com.meituan.robust.RobustCallBack;

import java.util.List;

/**
 * @author : maning
 * @desc :
 */
public class RobustManager {

    public static void loadPatch(Context context, String currentMD5) {
        //插入补丁
        PatchesRobustInfoImpl patchesRobustInfo = new PatchesRobustInfoImpl();
        patchesRobustInfo.setCurrentMD5(currentMD5);
        new PatchExecutor(context.getApplicationContext(), patchesRobustInfo, new RobustCallBack() {

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

}
