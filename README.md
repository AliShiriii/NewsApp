
![news(1)](https://user-images.githubusercontent.com/65492308/184654648-cb25f668-2d3a-47ee-b561-5bf0b13f3f81.png)


# NewsApp

* This app is a Play Store-level news app to show the world's news
* News app using API from https://newsapi.org/ News API, JSON API for live news and blog articles from media. News Feed is a simple app that gives you the latest and greatest news from multiple sources, so you don't have to switch between apps to stay informed.
# Functionality

* Breacking News 
* Saved News and Delete News
* Search News

# Libraries used

   * Kotlin - official programming language for Android development .
   * Coroutines - for asynchronous programming .
   * Android Architecture Components - Collection of libraries that help you design robust, testable, and maintainable apps.
   * LiveData - Data objects that notify views when the underlying database changes.
   * ViewModel - Stores UI-related data that isn't destroyed on UI changes.
   * Room - Access your app's SQLite database with in-app objects and compile-time checks.
   * Navigation -
   * dagger-hilt - Dependency Injection Framework (Kotlin).
   * Retrofit - A type-safe HTTP client for Android and Java.
   * Fragment
   * Glide for image loading
   * pretty time - format time

# Design Pattern

* MVVM (Model-View-ViewModel) is one of the architectural patterns which enhances separation of concerns, it allows separating the user interface logic from the business (or the back-end) logic. Its target (with other MVC patterns goal) is to achieve the following principle “Keeping UI code simple and free of app logic in order to make it easier to manage

* Lifecycles: It manages activity and fragment lifecycles of our app, survives configuration changes, avoids memory leaks and easily loads data into our UI.

* LiveData: It notifies views of any database changes. Use LiveData to build data objects that notify views when the underlying database changes.

* Room: It is a SQLite object mapping library. Use it to Avoid boilerplate code and easily convert SQLite table data to Java objects. Room provides compile time checks of SQLite statements and can return RxJava, Flowable and LiveData observables.

* ViewModel: It manages UI-related data in a lifecycle-conscious way. It stores UI-related data that isn't destroyed on app rotations.

* Repository: The repository depends on a persistent data model and a remote backend data source.

![mvvm-architecture-pattern](https://user-images.githubusercontent.com/65492308/184433445-c245b60b-13f1-4c3c-ab37-32e0cfe23180.png)

# Screens of the app

![photo_2022-08-28_09-38-56](https://user-images.githubusercontent.com/65492308/187085735-8d5ec698-d5e6-4eb0-83ae-c5eb4fa38d47.jpg)

![photo_2022-08-28_09-38-55](https://user-images.githubusercontent.com/65492308/187085698-1a4b32a8-b3d8-4244-aa5c-4e3e646c41f3.jpg)

![photo_2022-08-28_09-50-16](https://user-images.githubusercontent.com/65492308/187085769-92c128e0-a996-4cdc-acbe-4973436dc48e.jpg)

![photo_2022-08-28_09-49-51](https://user-images.githubusercontent.com/65492308/187085808-b371f46b-0b9a-49aa-bdd4-81575320d193.jpg)

![photo_2022-08-28_09-50-40](https://user-images.githubusercontent.com/65492308/187085904-4f1827e5-abcb-4971-82c0-a81d1ba5114e.jpg)
