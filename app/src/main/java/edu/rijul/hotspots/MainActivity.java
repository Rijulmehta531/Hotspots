package edu.rijul.hotspots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button rButton;
    private Button save;
    private EditText barName;
    private EditText address_Et;
    private boolean isFragmentDisplayed = false;
    static final String STATE_FRAGMENT = "state_of_fragment";
    private TextView ratingNum;
    private float beer_rating;
    private float wine_rating;
    private float music_rating;
   RaterDataSource db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rButton = findViewById(R.id.button);
        save = findViewById(R.id.saveToDB);
        barName= findViewById(R.id.bar_name);
        address_Et=findViewById(R.id.address);
        ratingNum=findViewById(R.id.rating_display);
        beer_rating=0;
        wine_rating=0;
        music_rating=0;
        db = new RaterDataSource(this);
        db.open();

        // Set the click listener for the button.
        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentDisplayed) {
                    displayFragment();
                }else{
                    isFragmentDisplayed = false;
                    displayFragment();
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bar=barName.getText().toString();
                String address=address_Et.getText().toString();

                long insertID = db.insertRating(bar,address,beer_rating,wine_rating,music_rating);
                if (insertID != -1) {
                    // Data inserted successfully
                    Toast.makeText(getApplicationContext(), "Data inserted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Failed to insert data
                    Toast.makeText(getApplicationContext(), "Failed to insert data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (savedInstanceState != null) {
            isFragmentDisplayed =
                    savedInstanceState.getBoolean(STATE_FRAGMENT);

        }
    }
    public void displayFragment() {
        rating ratingFragment = rating.newInstance();
        // Get the FragmentManager and start a transaction.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Add the RatingFragment.
        fragmentTransaction.add(R.id.fragment_container,
                ratingFragment).addToBackStack(null).commit();


// Set boolean flag to indicate fragment is open.
        isFragmentDisplayed = true;
    }
    public void updateRatingTextView(String rating) {
        if (ratingNum != null) {
            ratingNum.setText(rating+ " stars");
        }}
    public void saveIndividualRatings(float rating1, float rating2, float rating3) {
        beer_rating=rating1;
        wine_rating=rating2;
        music_rating=rating3;
        }
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }
}