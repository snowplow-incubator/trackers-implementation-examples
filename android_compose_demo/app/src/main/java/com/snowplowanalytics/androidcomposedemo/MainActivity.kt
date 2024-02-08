package com.snowplowanalytics.androidcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.snowplowanalytics.androidcomposedemo.ui.SchemaDetailViewModel
import com.snowplowanalytics.androidcomposedemo.ui.SchemaListViewModel
import com.snowplowanalytics.androidcomposedemo.ui.theme.ComposeDemoTheme

class MainActivity : ComponentActivity() {
    private val listVm = SchemaListViewModel()
    private val detailVm = SchemaDetailViewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                ComposeDemoApp(
                    listViewModel = listVm, 
                    detailViewModel = detailVm
                )
            }
        }
    }
}
