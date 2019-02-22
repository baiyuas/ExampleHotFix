package com.baiyu.hotfix;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.baiyu.library.LibraryActivity;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * @author baiyu
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_crash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CrashReport.testJavaCrash();
                Toast.makeText(MainActivity.this, "测试成功!", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LibraryActivity.class));
            }
        });

        findViewById(R.id.btn_apply_hand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Beta.applyTinkerPatch(MainActivity.this, Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch.zip");
            }
        });
    }
}
