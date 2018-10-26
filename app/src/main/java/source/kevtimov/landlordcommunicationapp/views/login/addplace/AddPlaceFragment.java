package source.kevtimov.landlordcommunicationapp.views.login.addplace;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import source.kevtimov.landlordcommunicationapp.R;

public class AddPlaceFragment extends Fragment {


    @Inject
    public AddPlaceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_add_place, container, false);

        ButterKnife.bind(this, root);

        return root;
    }

}
