package com.example.appafterlunch;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    EditText e1,e2;
    ProgressBar p1;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        b1=(Button)findViewById(R.id.button4);//login
        b2=(Button)findViewById(R.id.button5);//signup
        e1=(EditText)findViewById(R.id.editTextText);//username
        e2=(EditText)findViewById(R.id.editTextText2);//password
        e2.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        p1=(ProgressBar)findViewById(R.id.progressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Second.class);
                startActivity(i);
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString().trim();
                String s2=e2.getText().toString();
                if(s1.isEmpty())
                {
                    e1.setError("Enter Username");
                    return;
                }
                else {
                    if (s2.isEmpty())
                    {
                        e2.setError("Enter Password");
                        return;
                    }
                    else
                    {
                        p1.setVisibility(View.VISIBLE);
                        firebaseAuth.signInWithEmailAndPassword(s1, s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    p1.setVisibility(View.INVISIBLE);
                                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT). show();
                                    Intent j = new Intent(MainActivity.this,Third.class);
                                    startActivity(j);
                                    finish();
                                }
                                else
                                {
                                    p1.setVisibility(View.INVISIBLE);
                                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                    }
                }
            }
        });
    }
}