package edu.rijul.hotspots;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import java.text.DecimalFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link rating#newInstance} factory method to
 * create an instance of this fragment.
 */
public class rating extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public rating() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment rating.
     */
    // TODO: Rename and change types and number of parameters
    public static rating newInstance(String param1, String param2) {
        rating fragment = new rating();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        final View rootView= inflater.inflate(R.layout.fragment_rating, container, false);
        final RatingBar beerRatingBar=rootView.findViewById(R.id.beer_rating);
        final RatingBar wineRatingBar=rootView.findViewById(R.id.wine_rating);
        final RatingBar musicRatingBar=rootView.findViewById(R.id.music_rating);
        Button saveButton = rootView.findViewById(R.id.button_save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating1 = beerRatingBar.getRating();
                float rating2 = wineRatingBar.getRating();
                float rating3 = musicRatingBar.getRating();
                float avg = (rating1+rating2+rating3)/3;
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String formattedResult = decimalFormat.format(avg);
//                String rating = String.valueOf(ratingBar.getRating());
                // Get a reference to the main activity
                MainActivity mainActivity = (MainActivity) getActivity();

                // Update the main activity's TextView with the rating average and sending the individual ratings to the main activity
                if (mainActivity != null) {
                    mainActivity.updateRatingTextView(formattedResult);
                    mainActivity.saveIndividualRatings(rating1,rating2,rating3);
                }
                // Close the fragment by removing it from the container
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager != null) {
                    fragmentManager.beginTransaction().remove(rating.this).commit();
                }
            }
        });// Return the View for the fragment's UI.
        return rootView;
    }
    public static rating newInstance() {
        return new rating();
    }
}