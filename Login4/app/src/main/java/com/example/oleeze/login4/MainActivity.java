package com.example.oleeze.login4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegister;
    private EditText editTextCpassword;
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;


    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);


        editTextCpassword = (EditText) findViewById(R.id.editTextCpassword);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextName = (EditText) findViewById(R.id.editTextName);

        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String cpassword = editTextCpassword.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



        if(TextUtils.isEmpty(name)){
            //email is empty
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }


        if(!email.matches(emailPattern)){
            Toast.makeText(this, "Invalid email address",Toast.LENGTH_SHORT).show();
            return;
        }


        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        if(editTextPassword.getText().toString().length() < 6){
            Toast.makeText(this, "Password must have a minimum of 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(cpassword)){
            //email is empty
            Toast.makeText(this, "Please re-enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!editTextPassword.getText().toString().equals(editTextCpassword.getText().toString())){
            Toast.makeText(this, "Password does not match. Try again", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(age)){
            //email is empty
            Toast.makeText(this, "Please enter your age", Toast.LENGTH_SHORT).show();
            return;
        }

        //if validation are okay
        //will register user
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //user is succesfully registered
                            progressDialog.hide();



                                finish();
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

                        }else{
                            progressDialog.hide();
                            Toast.makeText(MainActivity.this, "Sorry someone with your email already have an account", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == buttonRegister){
            registerUser();

        }

        if(view == textViewSignin){
            //will open login activity here
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
