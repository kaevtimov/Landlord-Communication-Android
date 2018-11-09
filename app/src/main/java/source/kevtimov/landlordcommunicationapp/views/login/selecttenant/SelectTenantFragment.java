package source.kevtimov.landlordcommunicationapp.views.login.selecttenant;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.emredavarci.circleprogressbar.CircleProgressBar;
import com.muddzdev.styleabletoast.StyleableToast;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.models.User;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

public class SelectTenantFragment extends Fragment implements ContractsSelectTenant.View{

    @BindView(R.id.tv_select_tenant)
    TextView mTextViewSelectTenant;

    @BindView(R.id.lv_tenants)
    ListView mListViewSelectTenant;

    @BindView(R.id.progress_bar)
    CircleProgressBar mProgressBar;


    private ContractsSelectTenant.Presenter mPresenter;
    private ContractsSelectTenant.Navigator mNavigator;
    private ArrayAdapter<User> mTenantAdapter;


    @Inject
    public SelectTenantFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_select_tenant, container, false);

        ButterKnife.bind(this, root);

        mTenantAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_expandable_list_item_1);

        mListViewSelectTenant.setAdapter(mTenantAdapter);

        initFont();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadTenants();
    }

    @Override
    public void showEmptyList(){
        StyleableToast.makeText(getContext(), Constants.NO_TENANTS_FOUND,
                Toast.LENGTH_LONG, R.style.reject_login_toast)
                .show();
    }

    @Override
    public void showTenants(List<User> tenantList) {
        mTenantAdapter.clear();
        mTenantAdapter.addAll(tenantList);
        mTenantAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(ContractsSelectTenant.Presenter presenter) {
        this.mPresenter = presenter;
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
    public void setNavigator(ContractsSelectTenant.Navigator navigator) {
        this.mNavigator = navigator;
    }

    @Override
    public void navigateOnCancel() {
        mNavigator.navigateToAddPlaceOnCancel();
    }

    @Override
    public void navigateOnDone(User tenant) {
        mNavigator.navigateToAddPlaceOnDone(tenant);
    }

    @Override
    public void showError(Throwable error) {
        StyleableToast.makeText(getContext(), error.getMessage(),
                Toast.LENGTH_LONG, R.style.reject_login_toast)
                .show();
    }

    @OnClick(R.id.btn_cancel)
    public void OnClickCancel(View v){
        mPresenter.allowNavigateOnCancel();
    }

    @OnItemClick(R.id.lv_tenants)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        User tenant = mTenantAdapter.getItem(position);

        mPresenter.allowNavigateOnDone(tenant);
    }

    private void initFont() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int selectedFont = Integer.parseInt(sharedPreferences.getString(Constants.FONT_LIST, "1"));

        switch (selectedFont) {
            case 1:
                mTextViewSelectTenant.setTypeface(EasyFonts.droidSerifBold(getContext()));
                break;
            case 2:
                mTextViewSelectTenant.setTypeface(EasyFonts.funRaiser(getContext()));
                break;
            case 3:
                mTextViewSelectTenant.setTypeface(EasyFonts.walkwayBold(getContext()));
                break;

        }
    }
}
