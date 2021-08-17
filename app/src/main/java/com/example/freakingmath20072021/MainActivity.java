package com.example.freakingmath20072021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView mTvPoint, mTvExpression, mTvResult;
    ImageButton mImgTrue, mImgFalse;
    RelativeLayout mContainer;
    float mNumber1, mNumber2, mResult;
    int mIndexOperator;
    Random mRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Task 1 : Random biểu thức cho bài toán gồm 2 chữ số
        // Task 2 : Xử lý kết quả random có khi đúng hoặc sai
        // Task 3 :
        //          Nếu trả lời chính xác thì tăng điểm lên và random ra bài toán mới
        //          Nếu sai thì thông báo số điểm đã chơi được

        mapView();
        initData();
        event();

    }

    private void mapView() {
        mTvExpression = findViewById(R.id.textViewExpression);
        mTvPoint = findViewById(R.id.textViewPoint);
        mTvResult = findViewById(R.id.textViewResult);
        mImgTrue = findViewById(R.id.imageButtonTrue);
        mImgFalse = findViewById(R.id.imageButtonFalse);
        mContainer = findViewById(R.id.relativeLayoutContainer);
    }

    private void initData() {
        mNumber1 = mNumber2 = 0;
        mResult = -1f;
        mIndexOperator = -1;
        mRandom = new Random();
    }

    private void event() {
        randomExpression();
    }

    private void randomExpression() {
        // (smax - smin +1) + smin;
        // (99 - 1 + 1) + 1 (Random trong khoảng từ smin -> smax)

        // random toán hạng
        mNumber1 = mRandom.nextInt(99) + 1;
        mNumber2 = mRandom.nextInt(99) + 1;

        // 0 -> +
        // 1 -> -
        // 2 -> *
        // 3 -> /
        // random toán tử
        mIndexOperator = mRandom.nextInt(4);

        switch (mIndexOperator) {
            case 0:
                mResult = mNumber1 + mNumber2;
                setTextNumber(mNumber1, mNumber2, "+");
                break;
            case 1:
                mResult = mNumber1 - mNumber2;
                setTextNumber(mNumber1, mNumber2, "-");
                break;
            case 2:
                mResult = mNumber1 * mNumber2;
                setTextNumber(mNumber1, mNumber2, "*");
                break;
            case 3:
                mResult = mNumber1 / mNumber2;
                setTextNumber(mNumber1, mNumber2, "/");
                break;
        }
    }
    //cast == covert type

    private void setTextNumber(float number1, float number2, String operator) {
        mTvExpression.setText(String.format("%d %s %d", (int) number1, operator, (int) number2));
    }
}