package com.example.practicalexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration_Activity extends AppCompatActivity {
    private EditText edName, edMobilenumber, edUsername, edpassword;
    private Button btnregister;
    private TextView tvlogin;
    private DatabaseReference reference;
    private String Name, MobileNumber, UserName, Password;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_registration_);
        edName = findViewById (R.id.ed_name);
        edMobilenumber = findViewById (R.id.ed_mobilenumber);
        edUsername = findViewById (R.id.ed_user_name);
        edpassword = findViewById (R.id.ed_password);
        btnregister = findViewById (R.id.btn_register);
        tvlogin = findViewById (R.id.textlogin);


        auth = FirebaseAuth.getInstance ();
        if (auth.getCurrentUser () != null) {
            startActivity (new Intent (getApplicationContext (), MainActivity.class));
            finish ();
        }
        reference = FirebaseDatabase.getInstance ().getReference ("Users Data");


        btnregister.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                Name = edName.getText ().toString ();
                MobileNumber = edMobilenumber.getText ().toString ();
                UserName = edUsername.getText ().toString ();
                Password = edpassword.getText ().toString ();

                if (TextUtils.isEmpty (Name)) {
                    edName.setError ("Please fill The Name...");
                    return;
                } else if (TextUtils.isEmpty (MobileNumber) && (MobileNumber.length () < 10)) {
                    edMobilenumber.setError ("Please Valid Mobile Number...");
                    return;
                } else if (Password.length () < 6) {
                    edpassword.setError ("Password must be 6 characters");
                    return;
                } else if (TextUtils.isEmpty (UserName)) {
                    edUsername.setError ("Please fill The UserName...");
                    return;
                }
                User_Details user_details = new User_Details (Name, MobileNumber, UserName, Password);
                reference.child (UserName).setValue (user_details).
                        addOnFailureListener (new OnFailureListener () {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText (Registration_Activity.this, "Fail" + e.toString (), Toast.LENGTH_SHORT).show ();
                            }
                        }).
                        addOnSuccessListener (new OnSuccessListener<Void> () {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText (Registration_Activity.this, "Data Is save ", Toast.LENGTH_SHORT).show ();
                                Intent i = new Intent (Registration_Activity.this, MainActivity.class);
                                i.putExtra ("Name",Name);
                                i.putExtra ("Mobile",MobileNumber);
                                startActivity (i);
                            }
                        });
            }
        });


        tvlogin.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Registration_Activity.this, Login_Activity.class);
                startActivity (i);

            }
        });
    }
}
