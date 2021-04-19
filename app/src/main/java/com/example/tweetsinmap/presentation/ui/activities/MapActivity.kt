package com.example.tweetsinmap.presentation.ui.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.tweetsinmap.OnMapAndViewReadyListener
import com.example.tweetsinmap.R
import com.example.tweetsinmap.data.entities.response.TweetsResponse
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapActivity : AppCompatActivity(),
        OnMapAndViewReadyListener.OnGlobalLayoutAndMapReadyListener,
        View{
    private lateinit var map: GoogleMap
    lateinit var presenter: MapPresenter
    lateinit var input: EditText
    private var timer = Timer()
    private val DELAY: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        presenter = MapPresenterImpl()
        presenter.setView(this)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        input = findViewById(R.id.et_search)
        input.addTextChangedListener(textWatcher)

        //colocar un textwatcher al edit text y en el ontextchanged pasara un string que es lo que esta escrito, en ese metodo hago la llamada al presenter
        //presenter.getTweetsByLocation()
        OnMapAndViewReadyListener(mapFragment, this)
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(s.toString().isNotEmpty()) {
                timer.cancel()
                timer = Timer()
                timer.schedule(
                        object : TimerTask() {
                            override fun run() {
                                var texto: String = s.toString()
                                //we need to get current location to pass as parameter
                                presenter.getTweetsByLocation(texto, "-22.912214,-43.230182,1km")
                            }
                        },
                        DELAY
                )
            }
        }
    }

    override fun showTweetsByLocation(list: List<TweetsResponse>?) {
        //fill the map with tweets
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        // Return if googleMap was null
        map = googleMap ?: return

        with(map) {
            uiSettings.isZoomControlsEnabled = false

            setOnMarkerClickListener(markerClickListener)
            setOnMapClickListener { selectedMarker = null }

            setContentDescription("hola")

            // test markers
            val bounds = com.google.android.gms.maps.model.LatLngBounds.Builder()
            tweets.map { tweet ->
                addMarker(MarkerOptions().apply {
                    position(tweet.latLng)
                    title(tweet.title)
                    snippet(tweet.snippet)
                    icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                })
                bounds.include(tweet.latLng)
            }

            moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLngBounds(bounds.build(), 50))
        }
    }

    /** Keeps track of the selected marker. It will be set to null if no marker is selected. */
    private var selectedMarker: Marker? = null

    /**
     * If user tapped on the the marker which was already showing info window,
     * the showing info window will be closed. Otherwise will show a different window.
     */
    private val markerClickListener = object : GoogleMap.OnMarkerClickListener {
        override fun onMarkerClick(marker: Marker?): Boolean {
            if (marker == selectedMarker) {
                selectedMarker = null
                //move to detail tweet activity
                return true
            }

            selectedMarker = marker
            return false
        }
    }

    class MarkerInfo(val latLng: LatLng, val title: String, val snippet: String)

    private val tweets = listOf(
            MarkerInfo(LatLng(-27.47093, 153.0235),
                    "Brisbane", "Population: 2,074,200"),
            MarkerInfo(LatLng(-37.81319, 144.96298),
                    "Melbourne", "Population: 4,137,400"),
            MarkerInfo(LatLng(-33.87365, 151.20689),
                    "Sydney", "Population: 4,627,300"),
            MarkerInfo(LatLng(-34.92873, 138.59995),
                    "Adelaide", "Population: 1,213,000"),
            MarkerInfo(LatLng(-31.952854, 115.857342),
                    "Perth", "Population: 1,738,800")
    )
}