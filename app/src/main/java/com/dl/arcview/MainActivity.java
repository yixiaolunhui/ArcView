package com.dl.arcview;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private NestedScrollView scrollView;
    private View banner;
    private ArcShaderView arcview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView = findViewById(R.id.scrollView);
        banner = findViewById(R.id.banner);
        arcview = findViewById(R.id.arcview);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                float scale = 1.0f * scrollY / (banner.getBottom());
                if (scale <= 1) {
                    arcview.setArcHeight(scale);
                }
            }
        });
    }
}
