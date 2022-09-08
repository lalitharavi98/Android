package com.learningjavaandroid.makeitrain;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private Button makeItRain;
    private Button showInfo;
    private TextView moneyValue;
    private int moneyCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeItRain = findViewById(R.id.buttonMakeItRain);
        showInfo = findViewById(R.id.buttonShowInfo);
        moneyValue = findViewById(R.id.moneyValue);
//        makeItRain.setOnClickListener(view -> Log.d("MainActivity", "onClick: Make It Rain!!"));
/*
        Two ways to call onClick Listener
        Method 1 : setOnClickListener method
        Method 2 : Modify activity.xml file button provide onClick Method (showMoney)

 */

    }
    // Method 2
    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void showMoney(View view) {

        //Format number into currency NumberFormat from android.icu.text
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        moneyCounter += 2000;
        moneyValue.setText(String.valueOf(numberFormat.format(moneyCounter)));
        switch (moneyCounter){
            case 20000:
                //when using resource color
                moneyValue.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.solid_red));
                Snackbar.make(moneyValue,"Getting Richer!!",Snackbar.LENGTH_LONG).show();
                break;
            case 30000:
                moneyValue.setTextColor(Color.YELLOW);
                break;
            case 40000:
                moneyValue.setTextColor(Color.GREEN);
                break;
            default:
                moneyValue.setTextColor(Color.CYAN);
                break;
        }
//        Log.d("MainActivity", "onClick: Make It Rain!!" + moneyCounter);
    }

    public void showInfo(View view) {
//        Toast.makeText(MainActivity.this, R.string.app_info,
//                Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", "onClick: Toaster");
        //SnackBar more sophisticated toast.
        Snackbar.make(moneyValue,R.string.app_info,Snackbar.LENGTH_LONG).setAction("More", view1 -> {
            Log.d("Snack", "showInfo: SnackBar More");
        }).show();
    }
}