package com.example.practicalexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Activity extends AppCompatActivity {

    private EditText edloginUsername, edloginpassword;
    private DatabaseReference reference;
    private Button btnlogin;
    private String name, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login_);

        reference = FirebaseDatabase.getInstance ().getReference ("Users Data");
        edloginUsername = findViewById (R.id.ed_login_username);
        edloginpassword = findViewById (R.id.ed_login_password);

        btnlogin = findViewById (R.id.btn_login);
        btnlogin.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                name = edloginUsername.getText ().toString ();
                pass = edloginpassword.getText ().toString ();
                reference.child (name).addListenerForSingleValueEvent (listener);
            }

            ValueEventListener listener = new ValueEventListener () {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists ()) {
                        String password = snapshot.child ("password").getValue (String.class);
                        if (password.equals (pass)) {
                            Intent i = new Intent (Login_Activity.this, MainActivity.class);
                            startActivity (i);
                        } else {
                            Toast.makeText (Login_Activity.this, "Password is Wrong", Toast.LENGTH_SHORT).show ();
                        }
                    }
                    else {
                        Toast.makeText (Login_Activity.this, "Record not found", Toast.LENGTH_SHORT).show ();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText (Login_Activity.this, "Error" + error.toString (), Toast.LENGTH_SHORT).show ();
                }
            };
        });
    }
}
