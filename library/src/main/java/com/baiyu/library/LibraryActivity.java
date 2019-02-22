package com.baiyu.library;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * @author baiyu
 */
public class LibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        findViewById(R.id.btn_crash_lib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String test = null;
//                System.out.println(test.length());
                Toast.makeText(LibraryActivity.this, "Biu 特否", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
