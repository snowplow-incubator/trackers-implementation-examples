package com.snowplowanalytics.androidcomposedemo.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.snowplowanalytics.snowplow.Snowplow
import com.snowplowanalytics.snowplow.configuration.NetworkConfiguration
import com.snowplowanalytics.snowplow.configuration.TrackerConfiguration
import com.snowplowanalytics.snowplow.controller.TrackerController
import com.snowplowanalytics.snowplow.event.ListItemView
import com.snowplowanalytics.snowplow.event.ScreenView
import com.snowplowanalytics.snowplow.network.HttpMethod
import com.snowplowanalytics.snowplow.tracker.LogLevel

object Tracking {
    @Composable
    fun setup(namespace: String) : TrackerController {
        
        // Replace this collector endpoint with your own
        // Use ngrok or your IP address, e.g. http://192.168.0.16:9090 for Micro
        // NB http://0.0.0.0:9090 won't work
        val networkConfig = NetworkConfiguration("http://192.168.0.16:9090", HttpMethod.POST)
        val trackerConfig = TrackerConfiguration("android-compose-demo")
            .logLevel(LogLevel.DEBUG)
            
            // turning this off because it won't do anything for a Compose app
            .screenViewAutotracking(false)

        return Snowplow.createTracker(
            LocalContext.current,
            namespace,
            networkConfig,
            trackerConfig
        )
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
