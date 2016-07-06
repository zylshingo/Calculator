package com.jikexueyuan.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtOperator;
    private TextView txtResult;
    private long NumOne = 0;        //计算数字1
    private long NumTwo = 0;        //计算数字2
    private long sum = 0;           //结果数字
    private int operator = 0;       //定义运算符类型
    private String btnstr = "";     //获取按钮的文本
    private String displayNum = ""; //获取文本框显示的文本
    private String Num01 = "";      //计算数字1的字符串
    private String Num02 = "";      //计算数字2的字符串
    private Long flag = null;    //判断第一个数字是否存在

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();
    }

    private void initial() {
        txtOperator = (TextView) findViewById(R.id.txtOperator);
        txtResult = (TextView) findViewById(R.id.txtResult);
        findViewById(R.id.btnNum0).setOnClickListener(this);
        findViewById(R.id.btnNum1).setOnClickListener(this);
        findViewById(R.id.btnNum2).setOnClickListener(this);
        findViewById(R.id.btnNum3).setOnClickListener(this);
        findViewById(R.id.btnNum4).setOnClickListener(this);
        findViewById(R.id.btnNum5).setOnClickListener(this);
        findViewById(R.id.btnNum6).setOnClickListener(this);
        findViewById(R.id.btnNum7).setOnClickListener(this);
        findViewById(R.id.btnNum8).setOnClickListener(this);
        findViewById(R.id.btnNum9).setOnClickListener(this);
        findViewById(R.id.btnPlus).setOnClickListener(this);
        findViewById(R.id.btnMinus).setOnClickListener(this);
        findViewById(R.id.btnTimes).setOnClickListener(this);
        findViewById(R.id.btnDiv).setOnClickListener(this);
        findViewById(R.id.btnReset).setOnClickListener(this);
        findViewById(R.id.btnEqual).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        //数字的输入
        if (btn.getId() == R.id.btnNum0 || btn.getId() == R.id.btnNum1 || btn.getId() == R.id.btnNum2 ||
                btn.getId() == R.id.btnNum3 || btn.getId() == R.id.btnNum4 || btn.getId() == R.id.btnNum5 ||
                btn.getId() == R.id.btnNum6 || btn.getId() == R.id.btnNum7 || btn.getId() == R.id.btnNum8 ||
                btn.getId() == R.id.btnNum9) {
            if (operator == 0) {
                if (Num01.length()>15) {
                    return;
                }
                btnstr = btn.getText().toString();
                Num01 += btnstr;
                NumOne = Long.valueOf(Num01);
                txtResult.setText(String.valueOf(NumOne));
                flag = NumOne;
                txtOperator.setText("");
            }
            if (operator != 0) {
                if (Num02.length()>15) {
                    return;
                }
                btnstr = btn.getText().toString();
                Num02 += btnstr;
                NumTwo = Long.valueOf(Num02);
                txtResult.setText(String.valueOf(NumTwo));
                txtOperator.setText("");
            }
        }

        //运算符的输入,如果数字1和2的字符串都在 并且运算符类型不为0 则开始计算结果
        if (btn.getId() == R.id.btnPlus || btn.getId() == R.id.btnMinus ||
                btn.getId() == R.id.btnTimes || btn.getId() == R.id.btnDiv) {
            txtOperator.setText("");
            if (flag != null && operator != 0 && !Num02.equals("")) {
                compute();
                switch (btn.getId()) {
                    case R.id.btnPlus:
                        operator = 1;
                        txtOperator.setText("+");
                        break;
                    case R.id.btnMinus:
                        operator = 2;
                        txtOperator.setText("-");
                        break;
                    case R.id.btnTimes:
                        operator = 3;
                        txtOperator.setText("*");
                        break;
                    case R.id.btnDiv:
                        operator = 4;
                        txtOperator.setText("/");
                        break;
                }
            } else {
                switch (btn.getId()) {
                    case R.id.btnPlus:
                        operator = 1;
                        txtOperator.setText("+");
                        break;
                    case R.id.btnMinus:
                        operator = 2;
                        txtOperator.setText("-");
                        break;
                    case R.id.btnTimes:
                        operator = 3;
                        txtOperator.setText("*");
                        break;
                    case R.id.btnDiv:
                        operator = 4;
                        txtOperator.setText("/");
                        break;
                }
            }
        }

        //等于符号的输入和计算
        if (btn.getId() == R.id.btnEqual) {
            if (flag == null) {
                txtOperator.setText("请输入第一个数字！");
            } else if (operator == 0) {
                txtOperator.setText("请输入运算符号");
            } else if (Num02.equals("")) {
                txtOperator.setText("请输入第二个数字");
            } else if (flag != null && operator != 0 && !Num02.equals("")) {
                compute();
            }
        }

        //重置全部数据
        if (btn.getId() == R.id.btnReset) {
            reSet();
        }
    }

    //重置方法
    private void reSet() {
        NumOne = 0;
        NumTwo = 0;
        sum = 0;
        operator = 0;
        btnstr = "";
        displayNum = "";
        Num01 = "";
        Num02 = "";
        flag = null;
        txtResult.setText("");
    }

    //计算方法
    private void compute() {
        switch (operator) {
            case 1:
                sum = NumOne + NumTwo;
                break;
            case 2:
                sum = NumOne - NumTwo;
                break;
            case 3:
                sum = NumOne * NumTwo;
                break;
            case 4:
                if (NumTwo == 0) {
                    Toast.makeText(this, "除数不能为零", Toast.LENGTH_SHORT).show();
                    break;
                }
                sum = NumOne / NumTwo;
                break;
            default:
                break;
        }
        Num01 = String.valueOf(sum);
        NumOne = Long.valueOf(Num01);
        txtOperator.setText("=");
        if (Num01.length()>15) {
            txtOperator.setText("数据已经超过限制，请重新计算！");
            reSet();
        }
        txtResult.setText(Num01);
        Num01 = "";
        Num02 = "";
        operator = 0;


    }



}
