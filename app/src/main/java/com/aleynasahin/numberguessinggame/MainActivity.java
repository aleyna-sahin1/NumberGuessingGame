package com.aleynasahin.numberguessinggame;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aleynasahin.numberguessinggame.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int remainAttempt = 3, randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Random random = new Random();
        randomNumber = random.nextInt(5) + 1;
        System.out.println("Random number : " + randomNumber);


    }


    @SuppressLint("SetTextI18n")
    public void guess(View view) {
        remainAttempt--;
        int number = Integer.parseInt(String.valueOf(binding.editTxtNumber.getText()));
        if (!TextUtils.isEmpty(String.valueOf(binding.editTxtNumber.getText()))) {
            if (remainAttempt > 0) {
                if (number == randomNumber) {
                    binding.txtResult.setText("Your guess is true!");

                } else {
                    binding.txtResult.setText("Your guess is false, try again'");
                }
            } else {
                binding.txtResult.setText("No attempts left!");

            }

        }

        if (remainAttempt>0) {
            binding.txtRemainingAttempts.setText("Remaining attempts: " + remainAttempt);
        } else {
            binding.txtRemainingAttempts.setText("Remaining attempts: " + 0);

        }


    }
    public void restart(View view){
        AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setMessage("Are you sure you want to restart the game?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
    }

}