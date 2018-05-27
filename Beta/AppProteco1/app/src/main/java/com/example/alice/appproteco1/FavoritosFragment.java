package com.example.alice.appproteco1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import static android.app.Activity.RESULT_OK;

public class FavoritosFragment extends Fragment {

    private static int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseListAdapter<ChatMessage> adapter;
    RelativeLayout activity_main;
    FloatingActionButton fab;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_REQUEST_CODE) {
            if (requestCode == RESULT_OK){
                Snackbar.make(activity_main, "Successfuly signed in", Snackbar.LENGTH_SHORT).show();
                displayChatMessage();
            }else{
                Snackbar.make(activity_main, "We couldnt sign you. Try again later", Snackbar.LENGTH_SHORT).show();

            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        final View view= inflater.inflate(R.layout.fragment_favoritos, container, false);
        Toast.makeText(getContext(), "Welcome " , Toast.LENGTH_SHORT).show();


        activity_main = view.findViewById(R.id.fragment_favoritos);
        fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = v.findViewById(R.id.input);
                FirebaseDatabase.getInstance().getReference().push().setValue(new ChatMessage(input.getText().toString(),
                        FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                input.setText("");
            }
        });

        Snackbar.make(activity_main, "Welcome //user//", Snackbar.LENGTH_SHORT).show();

        //displayChatMessage();

        return view;

    }

    private void displayChatMessage() {

        ListView listView = getView().findViewById(R.id.list_of_messages);
    }
}
