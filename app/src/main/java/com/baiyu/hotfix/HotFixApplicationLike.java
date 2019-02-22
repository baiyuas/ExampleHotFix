package com.baiyu.hotfix;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.DownloadTask;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.bugly.beta.tinker.TinkerManager;
import com.tencent.bugly.beta.ui.BetaActivity;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.tinker.entry.DefaultApplicationLike;

/**
 * @author: lpc
 */
public class HotFixApplicationLike extends DefaultApplicationLike {

    public HotFixApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();


        // 补丁包
        Beta.enableHotfix = true;
        Beta.autoCheckUpgrade = true;
        Beta.autoDownloadOn4g = true;
        Beta.autoDownloadOnWifi = true;
        Beta.canAutoDownloadPatch = true;
        Beta.canAutoPatch = true;
        Beta.canNotifyUserRestart = true;

        Beta.upgradeStateListener = new UpgradeStateListener() {
            @Override
            public void onUpgradeFailed(boolean b) {
                Log.d("HotFix-->" , "onUpgradeFailed");
            }

            @Override
            public void onUpgradeSuccess(boolean b) {
                Log.d("HotFix-->" , "onUpgradeSuccess");
            }

            @Override
            public void onUpgradeNoVersion(boolean b) {
                Log.d("HotFix-->" , "onUpgradeNoVersion");
            }

            @Override
            public void onUpgrading(boolean b) {
                Log.d("HotFix-->" , "onUpgrading");
            }

            @Override
            public void onDownloadCompleted(boolean b) {
                Log.d("HotFix-->" , "onDownloadCompleted");
            }
        };
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String s) {
                Log.d("HotFix betaPatch-->" , "onPatchReceived msg: " + s);
            }

            @Override
            public void onDownloadReceived(long l, long l1) {
                Log.d("HotFix betaPatch-->" , "onDownloadReceived progress: " + l + ", total:" + l1);
            }

            @Override
            public void onDownloadSuccess(String s) {
                Log.d("HotFix betaPatch-->" , "onDownloadSuccess msg: " + s);
            }

            @Override
            public void onDownloadFailure(String s) {
                Log.d("HotFix betaPatch-->" , "onDownloadFailure msg: " + s);
            }

            @Override
            public void onApplySuccess(String s) {
                Log.d("HotFix betaPatch-->" , "onApplySuccess msg: " + s);
            }

            @Override
            public void onApplyFailure(String s) {
                Log.d("HotFix betaPatch-->" , "onApplyFailure msg: " + s);
            }

            @Override
            public void onPatchRollback() {
                Log.d("HotFix betaPatch-->" , "onPatchRollback");
            }
        };



        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Bugly.init(getApplication(), "f74c96762a", true);

    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }

}
