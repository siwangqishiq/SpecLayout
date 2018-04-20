package com.xinlan.speclayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private SpecbgLayout mSpecbgLayout;

    private View mText1;
    private View mText2;
    private View mText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpecbgLayout =(SpecbgLayout)findViewById(R.id.layout);
        mSpecbgLayout.setSelected(0);

        mText1 =(TextView)findViewById(R.id.text1);
        mText2 =(TextView)findViewById(R.id.text2);
        mText3 =(TextView)findViewById(R.id.text3);

        mText1.setOnClickListener(this);
        mText2.setOnClickListener(this);
        mText3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text1:
                mSpecbgLayout.setSelected(0);
                break;
            case R.id.text2:
                mSpecbgLayout.setSelected(1);
                break;
            case R.id.text3:
                mSpecbgLayout.setSelected(2);
                break;
        }
    }
}
