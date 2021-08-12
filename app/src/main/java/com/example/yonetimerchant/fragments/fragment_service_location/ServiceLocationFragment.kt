package com.example.yonetimerchant.fragments.fragment_service_location

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.yoneti.base.BaseFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.acitivities.HomeActivity
import com.example.yonetimerchant.databinding.FragmentServiceLocationBinding
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class ServiceLocationFragment :
    BaseFragment<ServiceLocationViewModel, FragmentServiceLocationBinding>() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var map: GoogleMap? = null
    private var locationRequest: LocationRequest? = null
    private val AUTOCOMPLETE_REQUEST_CODE = 1

    override fun getViewMode(): Class<ServiceLocationViewModel> =
        ServiceLocationViewModel::class.java

    override fun getLayout(): Int = R.layout.fragment_service_location

    override fun bindingToViews() {

        binding.mapView.onCreate(bundle)
        binding.mapView.onResume()

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 123
            )
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

        binding.mapView.getMapAsync(object : OnMapReadyCallback {
            override fun onMapReady(p0: GoogleMap?) {
                map = p0!!
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                map?.isMyLocationEnabled = true

                map?.uiSettings?.setMyLocationButtonEnabled(true)

                map?.setOnMyLocationButtonClickListener(object : GoogleMap.OnMyLocationButtonClickListener {
                    override fun onMyLocationButtonClick(): Boolean {

                        fillAddressForm(map?.myLocation)
                        return false
                    }
                })


                map?.setOnMyLocationClickListener {
                    var geocoder = Geocoder(requireContext(),
                        Locale.getDefault())
                    var addresses =  geocoder.getFromLocation(it.latitude,it.longitude,1)
                }
            }
        })

        createLocationRequest()
//        binding.mapView.enable

        binding.rbSelectRadius.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                binding.radiusBar.visibility = View.VISIBLE
                binding.tvRadiusCounter.visibility = View.VISIBLE
            }
        }

        binding.rbEntireCity.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                binding.radiusBar.visibility = View.GONE
                binding.tvRadiusCounter.visibility = View.GONE
            }
        }

        val TAG = ServiceLocationFragment::class.java.simpleName
        binding.radiusBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.tvRadiusCounter.setText(p1.toString())
                Log.d(TAG, "onProgressChanged: ${p1.toString()}")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        setUi()
//        binding.etSearchCity.setOnClickListener {
//
//            // Set the fields to specify which types of place data to
//            // return after the user has made a selection.
//            val fields = listOf(Place.Field.ID, Place.Field.NAME)
//
//            // Start the autocomplete intent.
//            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
//                .build(this)
//            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
//
//        }


//        binding.etSearchLocation.isFocusable = false
        Places.initialize(requireContext(), "AIzaSyA5wn9L2_Rze1zAWuKQhyveBW6McIJ8ROs")
    }

    private fun fillAddressForm(myLocation: Location?) {
        var geocoder = Geocoder(requireContext(),
            Locale.getDefault())
        var addresses =  geocoder.getFromLocation(myLocation?.latitude!!,myLocation.longitude,1)


        binding.etSearchLocation.setText(addresses.get(0).countryName)
        binding.etStreetAddress.setText(addresses.get(0).getAddressLine(0))
        binding.etSearchCity.setText(addresses.get(0).locality)
    }

    fun createLocationRequest() {
        locationRequest = LocationRequest.create()?.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        var task = LocationServices.getSettingsClient(requireContext())
            .checkLocationSettings(builder.build())
        task.addOnSuccessListener {
            if (it.locationSettingsStates.isGpsPresent) {
                Toast.makeText(requireContext(), "gps on hay", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(requireContext(), "gps band hay", Toast.LENGTH_SHORT).show()
        }

        task.addOnFailureListener {
            if (it is ResolvableApiException) {
                try {
                    it.startResolutionForResult(activity as HomeActivity, 999)
                } catch (exception: IntentSender.SendIntentException) {

                }
            }

        }
    }

    private fun setUi() {
        binding.scrollView.smoothScrollTo(0, binding.scrollView.getChildAt(0).height)

        binding.etSearchLocation.setOnClickListener {
            val fields = listOf(
                Place.Field.ID,
                Place.Field.ADDRESS,
                Place.Field.LAT_LNG,
                Place.Field.ADDRESS_COMPONENTS,
                Place.Field.PLUS_CODE
            )

            val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .build(requireContext().applicationContext)
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE && resultCode == RESULT_OK) {
            var places = Autocomplete.getPlaceFromIntent(data!!)
            var address = places.address
            settingLocationReturnedData()
            settingMarkerForMap(places.latLng)

        }
    }

    private fun settingLocationReturnedData() {

    }

    fun settingMarkerForMap(latLng: LatLng?) {
            var markerOption = MarkerOptions()
            markerOption.position(latLng!!)
            markerOption.title("new latlng")
            map?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
            map?.addMarker(markerOption)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

}