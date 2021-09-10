package com.example.yonetimerchant.fragments.fragment_service_location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Address
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
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_KEYBOARD
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class ServiceLocationFragment :
    BaseFragment<ServiceLocationViewModel, FragmentServiceLocationBinding>() {

    private var isEntireCity: Boolean = true
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var map: GoogleMap? = null
    private var locationRequest: LocationRequest? = null
    private val AUTOCOMPLETE_REQUEST_CODE = 1
    var serviceRadius = "Entire City" // it would be by default

    override fun getViewMode(): Class<ServiceLocationViewModel> =
        ServiceLocationViewModel::class.java

    override fun getLayout(): Int = R.layout.fragment_service_location

    @SuppressLint("SetTextI18n")
    override fun bindingToViews() {
        viewModel!!.getServiceLocations()
        binding.mapView.onCreate(bundle)
        binding.mapView.onResume()

        binding.serviceLocationViewModel = viewModel
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

                map?.setOnMyLocationButtonClickListener(object :
                    GoogleMap.OnMyLocationButtonClickListener {
                    override fun onMyLocationButtonClick(): Boolean {
                        viewModel!!.isCurrentLocation = "1"
                        fillAddressForm(map?.myLocation)
                        return false
                    }
                })


                map?.setOnMyLocationClickListener {
                    var geocoder = Geocoder(
                        requireContext(),
                        Locale.getDefault()
                    )
                    var addresses = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                }
            }
        })

        createLocationRequest()

        binding.rbSelectRadius.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                isEntireCity = !b
                binding.radiusBar.visibility = View.VISIBLE
                binding.tvRadiusCounter.visibility = View.VISIBLE
                binding.tvSelectedRadius.setText("${binding.radiusBar.progress} miles")
                binding.tvRadiusCounter.setText(binding.radiusBar.progress.toString())

            }
        }

        binding.rbEntireCity.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                isEntireCity = b
                binding.radiusBar.visibility = View.GONE
                binding.tvRadiusCounter.visibility = View.GONE
                binding.tvSelectedRadius.setText(serviceRadius)
                viewModel!!.businessRange = getString(R.string.entire_city)
            }
        }

        val TAG = ServiceLocationFragment::class.java.simpleName
        binding.radiusBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.tvRadiusCounter.setText(p1.toString())
                Log.d(TAG, "onProgressChanged: ${p1.toString()}")
                binding.tvSelectedRadius.setText("${p1.toString()} miles")

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        viewModel!!.businessLocation.observe(this, androidx.lifecycle.Observer {
            reverseGeocodeing(it.lat.toDouble(),it.lng.toDouble())

            binding.etSearchLocation.setText(it.country)
            binding.tvSelectedCountry.setText(it.country)
            binding.etStreetAddress.setText(it.let{it.city+" "+it.address+" "+it.country})
//            binding.etState.setText(it.adminArea)
            binding.etSearchCity.setText(it.city)
            binding.etZipCode.setText(it.zipcode)
            binding.tvStartTime.setText(it.openingTime)
            binding.tvCloseTime.setText(it.closeTime)
            binding.tvSelectedCity.setText(it.city)
            if (isEntireCity)
                binding.tvSelectedRadius.setText(serviceRadius)
            else
                binding.tvSelectedRadius.setText("${binding.radiusBar.progress} miles")
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

    fun reverseGeocodeing(lat: Double, lng: Double)
    {
        var geocoder = Geocoder(
            requireContext(),
            Locale.getDefault()
        )


        var addresses = geocoder.getFromLocation(lat, lng, 1)
        var latLng = LatLng(lat,lng)

        settingMarkerForMap(latLng)
        viewModel!!.lat = lat.toString()
        viewModel!!.lng =  lng.toString()
        bindDataToViews(addresses.get(0))
    }
    private fun fillAddressForm(myLocation: Location?) {
        var geocoder = Geocoder(
            requireContext(),
            Locale.getDefault()
        )
        var addresses = geocoder.getFromLocation(myLocation?.latitude!!, myLocation.longitude, 1)

        viewModel!!.lat = myLocation?.latitude.toString()
        viewModel!!.lng =  myLocation.longitude.toString()

        bindDataToViews(addresses.get(0))
    }
    fun bindDataToViews(address: Address)
    {
        binding.etSearchLocation.setText(address.countryName)
        binding.tvSelectedCountry.setText(address.countryName)
        binding.etStreetAddress.setText(address.getAddressLine(0))
        binding.etState.setText(address.adminArea)
        binding.etSearchCity.setText(address.locality)
        binding.tvSelectedCity.setText(address.locality)
        if (isEntireCity)
            binding.tvSelectedRadius.setText(serviceRadius)
        else
            binding.tvSelectedRadius.setText("${binding.radiusBar.progress} miles")
    }
    private fun fillAddressFromAutoComplete(myLocation: LatLng?) {
        var geocoder = Geocoder(
            requireContext(),
            Locale.getDefault()
        )
        var addresses = geocoder.getFromLocation(myLocation?.latitude!!, myLocation.longitude, 1)
        binding.etSearchLocation.setText(addresses.get(0).countryName)
        binding.tvSelectedCountry.setText(addresses.get(0).countryName)
        binding.etStreetAddress.setText(addresses.get(0).getAddressLine(0))
        binding.etState.setText(addresses.get(0).adminArea)
        binding.etSearchCity.setText(addresses.get(0).locality)
        binding.tvSelectedCity.setText(addresses.get(0).locality)
        if (isEntireCity)
            binding.tvSelectedRadius.setText(serviceRadius)
        else
            binding.tvSelectedRadius.setText("${binding.radiusBar.progress} miles")
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

        binding.tvStartTime.setOnClickListener {
            showDatePicker(true)
        }
        binding.tvCloseTime.setOnClickListener {
            showDatePicker(false)
        }

    }

    fun showDatePicker(isStartTime: Boolean) {
        var title = ""
        if (isStartTime)
            title = "Opening Time"
        else
            title = "Close Time"
        val materialTimePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setInputMode(INPUT_MODE_KEYBOARD)
            .setTitleText(title)
            .build()
        materialTimePicker.show(childFragmentManager, "show")

        materialTimePicker.addOnPositiveButtonClickListener {
            materialTimePicker.minute
            if (isStartTime) {
                binding.tvStartTime.setText(
                    "${materialTimePicker.hour}"
                )
            }
            else{
                binding.tvCloseTime.setText(
                    "${materialTimePicker.hour}"
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE && resultCode == RESULT_OK) {
            var places = Autocomplete.getPlaceFromIntent(data!!)
            fillAddressFromAutoComplete(places.latLng)
            var address = places.address
            viewModel!!.isCurrentLocation = "0"
            settingMarkerForMap(places.latLng)

        }
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