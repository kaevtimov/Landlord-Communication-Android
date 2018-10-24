package source.kevtimov.landlordcommunicationapp.views.login.finishsignup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import source.kevtimov.landlordcommunicationapp.R;
public class SignUpFinishFragment extends Fragment {


    @Inject
    public SignUpFinishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_up_finish, container, false);

        ButterKnife.bind(this, root);

        return root;
    }

}
