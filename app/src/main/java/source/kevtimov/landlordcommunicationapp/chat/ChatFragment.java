package source.kevtimov.landlordcommunicationapp.chat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment implements ChatContracts.View {

    @BindView(R.id.lv_landlords)
    ListView mLandlordsListView;

    @Inject
    ChatListAdapter chatListAdapter;

    private ChatContracts.Navigator mNavigator;
    private ChatContracts.Presenter mPresenter;
    public static final String NO_LANDLORDS = "Nothing to show";

    @Inject
    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, view);
        mLandlordsListView.setAdapter(chatListAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadLandlords();
    }

    @Override
    public void setPresenter(ChatContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setNavigator(ChatContracts.Navigator navigator) {
        mNavigator = navigator;
    }

    @Override
    public void showLandlords(List<User> landlordsList) {
        chatListAdapter.clear();
        chatListAdapter.addAll(landlordsList);
        chatListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showEmptyLandlordList() {
        Toast.makeText(getContext(), NO_LANDLORDS, Toast.LENGTH_SHORT).show();
    }

    @OnItemClick(R.id.lv_landlords)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User landlord = (User) parent.getItemAtPosition(position);
        mPresenter.selectLandlord(landlord);
    }
}
