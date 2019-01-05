package source.kevtimov.landlordcommunicationapp.async.base

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun background(): Scheduler
    fun ui(): Scheduler
}
