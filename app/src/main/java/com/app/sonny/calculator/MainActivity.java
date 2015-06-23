package com.app.sonny.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity{

    public EditText textField;

    protected float fnum = 0;
    protected float snum =0;
    protected float result =0;
    protected int operation = 0;
    protected boolean isNeg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textField = (EditText)findViewById(R.id.textField);
        textField.setFocusable(false);
        textField.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                reset();
                return true;
            }
        });
    }

    public void buttonClicked (View view){
        try {
            buttonClickedReceiver(view);
        }catch (NumberFormatException e){
            Toast toast = Toast.makeText(getApplicationContext(),"Invalid Operation", Toast.LENGTH_SHORT);
            toast.show();
            reset();
        }
    }


    public void buttonClickedReceiver (View view){
        String text = textField.getText().toString();
            switch (view.getId()){
                case R.id.buttonMultiply:
                case R.id.buttonDivide:
                case R.id.buttonMinus:
                case R.id.buttonPlus:
                case R.id.buttonEquals:
                    isNeg =  false;
                    break;
                default:
                    if (text.length() >= 10) return;
            }

        if (text.equals("0")){
         text = "";
        }
        try{
            Float.parseFloat(text);
        }catch (NumberFormatException e){
            text = "";
        }

        switch (view.getId()){
            case R.id.button0:
                if (!text.equals("0")){
                    text = text + "0";
                }
                break;
            case R.id.button1:
                text += "1";
                break;
            case R.id.button2:
                text += "2";
                break;
            case R.id.button3:
                text += "3";
                break;
            case R.id.button4:
                text += "4";
                break;
            case R.id.button5:
                text += "5";
                break;
            case R.id.button6:
                text += "6";
                break;
            case R.id.button7:
                text += "7";
                break;
            case R.id.button8:
                text += "8";
                break;
            case R.id.button9:
                text += "9";
                break;

            case R.id.buttonDot:
                if (text.equals(""))
                text += "0.";
                else text += ".";
                break;
            case R.id.buttonPlus:
                if (text.equals("")) text  = "0";
                fnum = Float.parseFloat(text);
                operation = 1;
                text = "+";
                break;
            case R.id.buttonMinus:
                if (text.equals("")){
                    text = "0";
                    isNeg = true;
                    break;
                }

                fnum = Float.parseFloat(text);
                operation = 2;
                text = "-";
                break;
            case R.id.buttonDivide:
                if (text.equals("")) text  = "0";
                fnum = Float.parseFloat(text);
                text = "/";
                operation = 3;
                break;
            case R.id.buttonMultiply:
                if (text.equals("")) text  = "0";
                fnum = Float.parseFloat(text);
                text = "X";
                operation = 4;
                break;
            case R.id.buttonEquals:
                if (text.equals("")) text  = "0";
                snum = Float.parseFloat(text);
                switch (operation){
                    case 1:
                        result = fnum + snum;
                        break;
                    case 2:
                        result = fnum - snum;
                        break;
                    case 3:
                        try{
                            result = fnum / snum;
                        }catch (NumberFormatException e){
                            Toast toast = Toast.makeText(getApplicationContext(),"Invalid Operation", Toast.LENGTH_SHORT);
                            toast.show();
                            reset();
                            text = "0";
                        }
                        break;
                    case 4:
                        result = fnum * snum;
                }

                switch (numberType(result)){
                    case 1:
                        text = String.valueOf ((int)result);
                        break;
                    case 2:
                        text = String. valueOf((double)result);
                        break;
                    case 3:
                        text = String.valueOf(result);
                        break;
                }

                break;
        }

        if (isNeg && !text.equals("0"))
            text = Float.parseFloat(text) * -1 < Float.parseFloat(text)? "-" + text: text;

        textField.setText(text);
    }


    public short numberType (float f){
       if (f-(int)f ==0) return 1;
        if (f - (double) f ==0) return 2;
        else return 3;
    }

    public void reset (){
        fnum = 0;
        snum = 0;
        result = 0;
        operation = 0;
        isNeg = false;
        textField.setText("0");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}