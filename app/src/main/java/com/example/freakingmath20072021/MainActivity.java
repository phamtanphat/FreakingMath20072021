package com.example.freakingmath20072021;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView mTvPoint, mTvExpression, mTvResult;
    ImageButton mImgTrue, mImgFalse;
    RelativeLayout mContainer;
    float mNumber1, mNumber2, mResult;
    int mIndexOperator , mPoint;
    boolean mIsTrue;
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
        mNumber1 = mNumber2 = mPoint =  0;
        mResult = -1f;
        mIndexOperator = -1;
        mIsTrue = false;
        mRandom = new Random();
    }

    private void event() {
        randomExpression();

        mImgTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mIsTrue){
                    mPoint += 1;
                }else{
                    Toast.makeText(MainActivity.this, "Kết quả sai , số điểm cao nhất là " + mPoint, Toast.LENGTH_SHORT).show();
                    mPoint = 0;
                }
                mTvPoint.setText(String.valueOf(mPoint));
                randomExpression();
            }
        });

        mImgFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mIsTrue){
                    mPoint += 1;
                }else{
                    Toast.makeText(MainActivity.this, "Kết quả sai , số điểm cao nhất là " + mPoint, Toast.LENGTH_SHORT).show();
                    mPoint = 0;
                }
                mTvPoint.setText(String.valueOf(mPoint));
                randomExpression();
            }
        });
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
        // random trường hợp kết quả nên đúng hay sai
        mIsTrue = mRandom.nextBoolean();
        // random color background;
        mContainer.setBackgroundColor(Color.rgb(mRandom.nextInt(200),mRandom.nextInt(200),mRandom.nextInt(200)));

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

        if (!mIsTrue){
            Log.d("BBB","Giá trị trước khi random kết quả : " + mResult);
            mResult += mRandom.nextInt(5) + 1;
            Log.d("BBB","Giá trị sau khi random kết quả : " + mResult);
        }
        setTextResult(mResult);

    }
    //cast == covert type

    private void setTextNumber(float number1, float number2, String operator) {
        mTvExpression.setText(String.format("%d %s %d", (int) number1, operator, (int) number2));
    }

    private void setTextResult(float result){
        if (Math.floor(result) - result < 0){
            // format 2 số thập phân : new DecimalFormat("#.##").format(result)
            mTvResult.setText(String.format("= %s" , new DecimalFormat("#.##").format(result).replace(",",".")));
        }else {
            mTvResult.setText(String.format("= %d" , (int) result));
        }
    }
}