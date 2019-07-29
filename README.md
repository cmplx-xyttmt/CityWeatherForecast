# City Weather Forecast

City Weather Forecast is an android application that allows users to bookmark cities on a map
and view weather forecasts for their bookmarked cities.

### Screencast
![App gif](screenshots/ezgif.com-resize.gif)

### Technical Details
Here are some technical details for this app:
- Uses the following [Android Architecture Components](https://developer.android.com/topic/libraries/architecture):
     - Room for data persistence
    - Live Data to notify views of changes in data
    - View Model to store UI-related data
- [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started) for navigation
- [Kotlin Couroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) for handling asynchronous tasks (database reads and writes and network connection).
- [Google maps sdk for android](https://developers.google.com/maps/documentation/android-sdk/intro) to enable a user to select a city to bookmark.
- [Open Weather API](https://openweathermap.org/api) to fetch weather forecast data.

### Open source libraries used
- [Retrofit](https://square.github.io/retrofit/): For connecting to the weather api 
- [Glide](https://github.com/bumptech/glide): For loading images (in this app, just weather icons)
- [Moshi](https://github.com/square/moshi): For parsing json data.