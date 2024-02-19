package com.snowplowanalytics.androidcomposedemo.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.snowplowanalytics.snowplow.Snowplow
import com.snowplowanalytics.snowplow.configuration.ConfigurationBundle
import com.snowplowanalytics.snowplow.configuration.NetworkConfiguration
import com.snowplowanalytics.snowplow.configuration.RemoteConfiguration
import com.snowplowanalytics.snowplow.configuration.TrackerConfiguration
import com.snowplowanalytics.snowplow.controller.TrackerController
import com.snowplowanalytics.snowplow.event.ListItemView
import com.snowplowanalytics.snowplow.event.ScreenView
import com.snowplowanalytics.snowplow.network.HttpMethod
import com.snowplowanalytics.snowplow.tracker.LogLevel

object Tracking {
    @Composable
    fun setup(): TrackerController? {
        
        // Replace this collector endpoint with your own
        // For Micro, use ngrok or your IP address, e.g. http://192.168.0.16:9090
        // NB http://0.0.0.0:9090 won't work
        val networkConfig = NetworkConfiguration("http://192.168.0.10:9090", HttpMethod.POST)
        val trackerConfig = TrackerConfiguration("android-compose-demo")
            .logLevel(LogLevel.DEBUG)
            
            // turning this off because it won't do anything for a Compose app
            .screenViewAutotracking(false)

        return Snowplow.createTracker(
            LocalContext.current,
            "namespace",
            networkConfig,
            trackerConfig
        )
        
        // Comment out the above and uncomment this to use remote configuration
//        // Make a remote config schema file, including your collector endpoint, and host it
//        // Change this URL to point to the file
//        val remoteConfig = RemoteConfiguration("http://192.168.0.10:8000/config.json", HttpMethod.GET)
//        // Replace this backup endpoint with your own collector address
//        val defaultConfig = listOf(ConfigurationBundle(
//            "defaultNamespace", 
//            NetworkConfiguration("http://192.168.0.10:9090", HttpMethod.POST))
//        )
//
//        Snowplow.setup(
//            LocalContext.current,
//            remoteConfiguration = remoteConfig,
//            defaultBundles = defaultConfig,
//            onSuccess = {}
//        )
//        return Snowplow.defaultTracker
    }

    @Composable
    fun AutoTrackScreenView(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            Snowplow.defaultTracker?.track(ScreenView(destination.route ?: "null"))
        }
    }

    @Composable
    fun TrackListItemView(index: Int, itemsCount: Int?) {
        LaunchedEffect(Unit, block = {
            val event = ListItemView(index, itemsCount)
            Snowplow.defaultTracker?.track(event)
        })
    }
}
