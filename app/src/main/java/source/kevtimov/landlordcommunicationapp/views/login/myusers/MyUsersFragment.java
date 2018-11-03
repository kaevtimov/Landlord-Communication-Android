package source.kevtimov.landlordcommunicationapp.views.login.myusers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;

public class MyUsersFragment extends Fragment implements ContractsMyUsers.View{


    @BindView(R.id.lv_users)
    ListView mListViewUsers;

    @BindView(R.id.iv_users)
    ImageView mImageViewUsers;

    private ContractsMyUsers.Presenter mPresenter;
    private ContractsMyUsers.Navigator mNavigator;
    private UsersAdapter mArrayAdapterUser;

    @Inject
    public MyUsersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_my_users, container, false);
        ButterKnife.bind(this, root);

        mArrayAdapterUser = new UsersAdapter(getContext());
        mListViewUsers.setAdapter(mArrayAdapterUser);


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadUsers();
    }


    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(ContractsMyUsers.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setNavigator(ContractsMyUsers.Navigator navigator) {
        this.mNavigator = navigator;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(Throwable error) {
        StyleableToast.makeText(getContext(), error.getMessage(),
                Toast.LENGTH_LONG, R.style.reject_login_toast)
                .show();
    }

    @Override
    public void addUser(User user) {
        mArrayAdapterUser.add(user);
        mArrayAdapterUser.notifyDataSetChanged();
    }
}
