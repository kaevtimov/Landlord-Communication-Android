package source.kevtimov.landlordcommunicationapp.chat.templatemessage;

import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import source.kevtimov.landlordcommunicationapp.R;
import source.kevtimov.landlordcommunicationapp.utils.Constants;

public class TemplateMessageActivity extends DaggerAppCompatActivity implements ContractsTemplateMessage.Navigator{

    @Inject
    TemplateMessageFragment mTemplateMessageFragment;

    @Inject
    ContractsTemplateMessage.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_message);

        mTemplateMessageFragment.setPresenter(mPresenter);
        mTemplateMessageFragment.setNavigator(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.templ_message_content, mTemplateMessageFragment)
                .commit();
    }

    @Override
    public void navigateBackToChat(String templateMessage) {
        Intent intent = new Intent();
        intent.putExtra(Constants.TEMPLATE_MESSAGE, templateMessage);
        setResult(RESULT_OK, intent);
        finish();
    }
}
