# Movision

Android App using The Movie DB API


## Description
A simple app that contains basic list detail functionality with unit tests. It connects to TMDB API and fetch popular, on playing and upcoming movies.


## Tech Stack
- Dagger Hilt - Used to provide dependency injection
- Retrofit 2 - OkHttp3 - request/response API
- Jetpack Navigation - UI Navigation between fragments
- Glide - for image loading.
- RxJava 3 - reactive programming paradigm
- LiveData - use LiveData to see UI update with data changes.
- Data Binding - bind UI components in layouts to data sources
- Mockk, jUnit4 - Unit Testing

## App architecture
- Followed the rules from Architecture guidelines recommended by Google.
- keep views only responsible for UI related code
- ViewModel contains only business logic and provides connection between the UI and Domain layer
- Domain layer provides usecases and data to ViewModel classes. (single source of truth)

<img src="https://github.com/independentdev/Movision/blob/master/docs/achitecture.png">