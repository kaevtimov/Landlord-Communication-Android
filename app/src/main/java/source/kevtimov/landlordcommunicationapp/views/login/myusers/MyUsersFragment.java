package source.kevtimov.landlordcommunicationapp.views.login.myusers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.emredavarci.circleprogressbar.CircleProgressBar;
import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;

public class MyUsersFragment extends Fragment implements ContractsMyUsers.View{


    @BindView(R.id.lv_users)
    ListView mListViewUsers;

    @BindView(R.id.iv_users)
    ImageView mImageViewUsers;

    @BindView(R.id.progress_bar)
    CircleProgressBar mProgressBar;

    private ContractsMyUsers.Presenter mPresenter;
    private ContractsMyUsers.Navigator mNavigator;
    private UsersAdapter mArrayAdapterUser;
    private List<User> users;

    @Inject
    public MyUsersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_my_users, container, false);
        ButterKnife.bind(this, root);
        users = new ArrayList<>();

        mArrayAdapterUser = new UsersAdapter(getContext());
        mListViewUsers.setAdapter(mArrayAdapterUser);


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mArrayAdapterUser.clear();
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
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable error) {
        StyleableToast.makeText(getContext(), error.getMessage(),
                Toast.LENGTH_LONG, R.style.reject_login_toast)
                .show();
    }

    @Override
    public void addUser(User user) {
        if(!users.contains(user)){
            users.add(user);
            mArrayAdapterUser.add(user);
        }
    }

    @Override
    public void navigateToDetails(User user) {
        mNavigator.navigateToDetails(user);
    }

    @OnItemClick(R.id.lv_users)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        User user = mArrayAdapterUser.getItem(position);

        mPresenter.allowNavigation(user);
    }
}
