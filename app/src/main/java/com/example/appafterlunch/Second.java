package com.example.appafterlunch;

import android.annotation.SuppressLint;
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

public class Second extends AppCompatActivity {
    EditText e1,e2;
    ProgressBar p1;
    Button b1,b2;
    FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        e1=findViewById(R.id.editTextText3);
        e2=findViewById(R.id.editTextText4);
        p1=findViewById(R.id.progressBar2);
        e2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        firebaseAuth=FirebaseAuth.getInstance();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k =new Intent(Second.this,MainActivity.class);
                startActivity(k);
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
                    e1.setError("Enter Email");
                    return;
                }
                else
                {
                    if(s2.isEmpty())
                    {
                        e2.setError("Enter Password");
                        return;
                    }
                    else
                    {
                        p1.setVisibility(View.VISIBLE);
                        firebaseAuth.createUserWithEmailAndPassword(s1, s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                              if(task.isSuccessful())
                                  {
                                  p1.setVisibility(View.INVISIBLE);
                                  Toast.makeText(Second.this, "Updated", Toast.LENGTH_SHORT).show();
                                  Intent intent=new Intent(Second.this,MainActivity.class);
                                  startActivity(intent);
                                  finish();
                              }
                              else {
                                  p1.setVisibility(View.INVISIBLE);
                                  Toast.makeText(Second.this, "Failed", Toast.LENGTH_SHORT).show();
                              }
                            }
                        });
                    }
                }

            }
        });
    }
}