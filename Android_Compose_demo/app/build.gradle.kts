plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.snowplowanalytics.androidcomposedemo"
    compileSdk = 34

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }

    defaultConfig {
        applicationId = "com.snowplowanalytics.androidcomposedemo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.01.00")
    implementation(composeBom)
    
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.navigation:navigation-compose:2.6.0")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.preference:preference-ktx:1.2.1")
    implementation("androidx.browser:browser:1.5.0")

    implementation("com.snowplowanalytics:snowplow-android-tracker:6.0.0")

    // for install referrer, AAID, and app set ID
    implementation("com.google.android.gms:play-services-appset:16.0.2")
    implementation("com.android.installreferrer:installreferrer:2.2")
    implementation("com.google.android.gms:play-services-ads:22.6.0")
    implementation("com.google.android.gms:play-services-ads-identifier:18.0.1")

    // app specific: HTTP client for getting schemas from Iglu Central
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.0.0")
}
