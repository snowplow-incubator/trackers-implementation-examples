# Trackers implementation examples

"Realistic" if extremely simple demos for the main trackers.

## Mobile demos

The Android and iOS demos are very similar. They download all the public Iglu Central schemas and display them in a list on the main screen. Click on each schema tile to get more details on a second screen. The tracker has basically default configuration.

Expected events:
- ScreenView
- ScreenEnd
- Foreground/Background

### Running the demos
**Android**
1. Open Android Studio
2. File > Open the `Android_Compose_demo` subfolder
3. Set your collector URL in `Android_Compose_demo/app/src/main/java/com/snowplowanalytics/androidcomposedemo/data/Tracking.kt`
4. Run using the green triangle button at the top right(ish) of the window

**iOS**
1. Open XCode
2. File > Open `ios_demo`
3. Set your collector URL in `ios_demo/ios_demo/Tracking.swift`
4. Run using the grey triangle button at the top left(ish) of the window
