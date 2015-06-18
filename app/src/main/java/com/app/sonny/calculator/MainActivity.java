package com.app.sonny.calculator;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity{

    public static EditText textField;
    float inc = 1F;
    protected float prevInt = 0;
    protected float fnum = 0;
    protected float snum =0;
    protected float result =0;
    protected int operation = 0;
    protected boolean dec = false;
    protected boolean isNeg = false;

    private int decCount = 1;


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
        //textField.setTypeface(Typeface.SERIF);
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
        //retrieves text from the textfield

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
            ////OPERATIONS
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
                           if (snum != 0) result = fnum / snum;
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
        inc = 1F;
        prevInt = 0;
        dec = false;
        decCount = 1;
        operation = 0;
        textField.setText("0");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}