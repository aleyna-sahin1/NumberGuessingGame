package com.aleynasahin.numberguessinggame;

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
    private Random random;


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

        random = new Random();
        randomNumber = random.nextInt(5) + 1;
        System.out.println("Random number : " + randomNumber);

    }

    public void guess(View view) {
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
        remainAttempt--;
        if (remainAttempt >= 0) {
            binding.txtRemainingAttempts.setText("Remaining attempts: " + remainAttempt);
        } else {
            binding.txtRemainingAttempts.setText("Remaining attempts: " + 0);
        }

    }
}