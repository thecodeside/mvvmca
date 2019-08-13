package com.thecodeside.mvvmca.features.drivers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.thecodeside.mvvmca.features.drivers.usecase.DriversRemoteToRecyclerItemMapper
import com.thecodeside.mvvmca.features.drivers.usecase.GetDriversList
import com.thecodeside.mvvmca.helpers.RxTestRule
import com.thecodeside.mvvmca.helpers.mockDriverRemoteModelList
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit


@RunWith(MockitoJUnitRunner::class)
class DriversListViewModelTest {

    @Mock
    private lateinit var getDriversList: GetDriversList
    @Mock
    private lateinit var observer: Observer<List<DriversItem>>

    private lateinit var viewModel: DriversListViewModel

    @Rule
    @JvmField
    var rxTestRule: RxTestRule = RxTestRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        viewModel = DriversListViewModel(getDriversList, DriversRemoteToRecyclerItemMapper())
    }

    @Test
    fun `whenever subscription is toggled subscription state is changing from PLAY to PAUSE`() {
        RxJavaPlugins.setComputationSchedulerHandler { TestScheduler() }

        assertEquals(SubscriptionState.PLAY, viewModel.subscriptionState.value)
        viewModel.toggleDriversSubscription()
        assertEquals(SubscriptionState.PAUSE, viewModel.subscriptionState.value)
        viewModel.toggleDriversSubscription()
        assertEquals(SubscriptionState.PLAY, viewModel.subscriptionState.value)
    }

    @Test
    fun `whenever subscription is PLAY it returns DriversItem every 5 sec`() {
        whenever(getDriversList()).thenReturn(Observable.just(mockDriverRemoteModelList))

        val testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }

        viewModel.driversList.observeForever(observer)
        viewModel.toggleDriversSubscription()

        testScheduler.advanceTimeBy(10, TimeUnit.SECONDS)

        verify(observer, times(3)).onChanged(any())
    }
}