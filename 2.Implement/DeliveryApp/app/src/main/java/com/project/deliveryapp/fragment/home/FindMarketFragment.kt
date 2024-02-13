package com.project.deliveryapp.fragment.home

import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationSource
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay.OnClickListener
import com.naver.maps.map.util.FusedLocationSource
import com.project.deliveryapp.R
import com.project.deliveryapp.data.MarketData
import com.project.deliveryapp.databinding.FragmentFindMarketBinding
import com.project.deliveryapp.dialog.loading.Loading
import com.project.deliveryapp.dialog.loading.LoadingDialog
import com.project.deliveryapp.fragment.home.main.RecentMarketFragment
import com.project.deliveryapp.fragment.home.main.SelectMarketFragment
import com.project.deliveryapp.naver.NaverMapManager
import com.project.deliveryapp.settings.PermissionManager
import com.project.deliveryapp.settings.WindowObject
import com.project.deliveryapp.view_model.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.exitProcess


class FindMarketFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentFindMarketBinding? = null
    private val binding get() = _binding!!


    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel

    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource

    private lateinit var curLocation: LatLng
    private var isUpdated: Boolean = false

    private var waitTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = requireContext()
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        locationSource = FusedLocationSource(this, PermissionManager.GPS_REQUEST_CODE)

        with(requireActivity()) {
            onBackPressedDispatcher.addCallback(this@FindMarketFragment) {
                if(System.currentTimeMillis() - waitTime >=1500 ) {
                    waitTime = System.currentTimeMillis()
                    Toast.makeText(context,"한번 더 누르면 종료됩니다!", Toast.LENGTH_SHORT).show()
                } else {
                    moveTaskToBack(true);
                    finishAndRemoveTask();
                    exitProcess(0);
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFindMarketBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentParams = binding.container.layoutParams
        //fragmentParams.height = WindowObject.height / 4

        childFragmentManager.beginTransaction()
            .replace(binding.container.id, RecentMarketFragment()).commit()

        val mapFragment = NaverMapManager.displayMap(childFragmentManager, binding.mapView.id)
        mapFragment.getMapAsync(this@FindMarketFragment)
    }

    override fun onResume() {
        super.onResume()
        isUpdated = false
    }

    override fun onMapReady(nMap: NaverMap) {
        this.naverMap = nMap
        naverMap.locationSource = locationSource

        naverMap.addOnLocationChangeListener {
            curLocation = LatLng(it.latitude, it.longitude)

            if(isUpdated) {
                return@addOnLocationChangeListener
            }

            isUpdated = true

            curLocation = LatLng(it.latitude, it.longitude)
            val cameraUpdate = CameraUpdate.scrollTo(curLocation)
            naverMap.moveCamera(cameraUpdate)

            loadRecentMarkets(curLocation)
        }


        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        naverMap.uiSettings.isLocationButtonEnabled = true
    }

    private fun loadRecentMarkets(curLocation: LatLng) {
        LoadingDialog.show(requireActivity());

        CoroutineScope(Dispatchers.IO).launch {
            val nearMarkets = viewModel.getNearMarkets(context, curLocation)

            withContext(Dispatchers.Main) {
                nearMarkets.forEach { dto ->
                    val location = LatLng(dto.latitude, dto.longitude)
                    val marker = Marker(location)
                    marker.map = naverMap

                    marker.onClickListener = OnClickListener {
                        viewModel.curMarketId = dto.id

                        childFragmentManager.beginTransaction()
                            .replace(R.id.container, SelectMarketFragment()).commit()

                        false
                    };
                }
            }


            LoadingDialog.dismiss()
        }

    }
}