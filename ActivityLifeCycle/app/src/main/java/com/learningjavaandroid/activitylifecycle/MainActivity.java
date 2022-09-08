 package com.learningjavaandroid.activitylifecycle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.learningjavaandroid.activitylifecycle.databinding.ActivityMainBinding;

 public class MainActivity extends AppCompatActivity {
     private ActivityMainBinding binding;
     private final int RESULT_CODE = 123;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_main);
//        Log.d("Cycle", "onCreate: ");
//        Toast.makeText(MainActivity.this,"onCreate called",Toast.LENGTH_SHORT).show();

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.buttonGuess.setOnClickListener(v -> {
            String guess = binding.guessField.getText().toString().trim();
            if (!guess.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, ShowGuess.class);
                intent.putExtra("guess", guess);

            /*
                used when we don't have to hear back from the other activity.
            */
//               startActivity(intent);

            /*
                 used when we want to register ourselves to onActivityResult
                 from the other activity.

            */
            activityResultLauncher.launch(intent);

            } else {
                Toast.makeText(MainActivity.this,"Enter guess",Toast.LENGTH_SHORT).show();
            }
    });
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Log.d("ActivityResult", "onActivityResult: entered");

                        if(result.getResultCode() == RESULT_CODE){
                            assert result.getData() != null;
                            String message = result.getData().getStringExtra("message_back").toString().trim();
                            Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
                        }

        }
    });






    @Override
    protected void onStart() {
        super.onStart();
//        Log.d("Cycle", "onStart: ");
//        Toast.makeText(MainActivity.this,"onStart called",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.d("Cycle", "onPostResume: ");
//        Toast.makeText(MainActivity.this,"onPostResume called",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Log.d("Cycle", "onPause: ");
//        Toast.makeText(MainActivity.this,"onPause called",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Log.d("Cycle", "onStop: ");
//        Toast.makeText(MainActivity.this,"onStop called",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Log.d("Cycle", "onDestroy: ");
//        Toast.makeText(MainActivity.this,"onDestroy called",Toast.LENGTH_SHORT).show();
    }




 }