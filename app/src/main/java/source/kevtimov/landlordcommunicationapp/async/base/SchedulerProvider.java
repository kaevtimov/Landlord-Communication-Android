package source.kevtimov.landlordcommunicationapp.async.base;

import io.reactivex.Scheduler;

public interface SchedulerProvider {
    Scheduler background();
    Scheduler ui();
}
