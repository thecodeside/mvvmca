package com.thecodeside.mvvmca.features.drivers

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.thecodeside.mvvmca.R
import com.thecodeside.mvvmca.common.base.BaseActivity
import com.thecodeside.mvvmca.common.util.debounceClicks
import com.thecodeside.mvvmca.injection.ViewModelFactory
import com.thecodeside.mvvmca.location.LocationPermissionManager
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_drivers.*
import javax.inject.Inject

private const val LOG_TAG = "DriversListActivity"

class DriversListActivity : BaseActivity() {

    @Inject
    lateinit var locationPermissionManager: LocationPermissionManager

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<DriversListViewModel>

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(DriversListViewModel::class.java)
    }

    private val itemAdapter = ItemAdapter<DriversItem>()

    private val fastAdapter = FastAdapter.with(itemAdapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drivers)
        setSupportActionBar(drivers_toolbar)

        setupDriversRecycler()
        subscribeToFabClick()
        subscribeToFabState()
        subscribeToDriversList()
    }

    private fun subscribeToDriversList() {
        viewModel.driversList.observe(this, Observer {
            itemAdapter.set(it)
        })
    }

    private fun setupDriversRecycler() {
        recycler_view.apply {
            adapter = fastAdapter
        }
    }

    private fun subscribeToFabState() {
        viewModel.subscriptionState.observe(this, Observer { showFabState(it) })
    }

    private fun showFabState(it: SubscriptionState) {
        when (it) {
            SubscriptionState.PLAY -> drivers_fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
            SubscriptionState.PAUSE -> drivers_fab.setImageResource(R.drawable.ic_pause_black_24dp)
        }
    }

    private fun subscribeToFabClick() {
        return drivers_fab.debounceClicks()
            .flatMap { requestPermissionIfNeeded() }
            .subscribe({
                toggleDriversSubscription(it)
            }, {
                Log.e(LOG_TAG, "Error on FAB click", it)
            })
            .registerSubscription()
    }

    private fun toggleDriversSubscription(hasLocationPermission: Boolean) {
        when (hasLocationPermission) {
            true -> viewModel.toggleDriversSubscription()
            false -> showNoPermissionDialog()
        }
    }

    private fun showNoPermissionDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.permission_needed)
            .setMessage(R.string.no_work_without_permission)
            .show()
    }

    private fun requestPermissionIfNeeded(): Observable<Boolean> {
        return when (locationPermissionManager.isLocationPermissionGranted()) {
            true -> Observable.just(true)
            else -> locationPermissionManager.requestLocationPermission()
        }
    }
}