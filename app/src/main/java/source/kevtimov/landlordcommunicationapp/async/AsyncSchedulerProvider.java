package source.kevtimov.landlordcommunicationapp.async;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import source.kevtimov.landlordcommunicationapp.async.base.SchedulerProvider;

public class AsyncSchedulerProvider implements SchedulerProvider {
    private static SchedulerProvider instance;

    @Inject
    public AsyncSchedulerProvider() {

    }

    public static SchedulerProvider getSchedulerProvider() {
        if (instance == null) {
            instance = new AsyncSchedulerProvider();
        }
        return instance;
    }

    @Override
    public Scheduler background() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
