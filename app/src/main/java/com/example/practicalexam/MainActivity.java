package com.example.practicalexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView name, mobilenumber;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        name=findViewById (R.id.showname);
        mobilenumber=findViewById (R.id.showMobilenumber);


        auth = FirebaseAuth.getInstance ();
        user = auth.getCurrentUser ();
        firebaseDatabase = FirebaseDatabase.getInstance ();
        reference = firebaseDatabase.getReference ("Users Data");


Query query = reference.orderByChild ("userName").equalTo (user.getDisplayName ());
        query.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren ()) {
                    String NAME = "" + ds.child ("name").getValue ();
                    String MOBILRNUMBER = "" + ds.child ("mobileNumber").getValue ();
                    name.setText (NAME);
                    mobilenumber.setText (MOBILRNUMBER);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
