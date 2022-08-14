
![news](https://user-images.githubusercontent.com/65492308/184536358-fc283b30-520a-4a36-bcae-8c684a5ee75b.png)


# NewsApp

* This app is a Play Store-level news app to show the world's news

# Functionality

* Breacking News 
* Saved News and Delete News
* Search News

# Libraries used

* Room
* Viewmodel
* Lifecycles
* Livedata
* Coroutines
* Material library
* Navigation Components
* ViewBinding
* DataStore(Preferences)

# Design Pattern

* MVVM (Model-View-ViewModel) is one of the architectural patterns which enhances separation of concerns, it allows separating the user interface logic from the business (or the back-end) logic. Its target (with other MVC patterns goal) is to achieve the following principle “Keeping UI code simple and free of app logic in order to make it easier to manage

* Lifecycles: It manages activity and fragment lifecycles of our app, survives configuration changes, avoids memory leaks and easily loads data into our UI.

* LiveData: It notifies views of any database changes. Use LiveData to build data objects that notify views when the underlying database changes.

* Room: It is a SQLite object mapping library. Use it to Avoid boilerplate code and easily convert SQLite table data to Java objects. Room provides compile time checks of SQLite statements and can return RxJava, Flowable and LiveData observables.

* ViewModel: It manages UI-related data in a lifecycle-conscious way. It stores UI-related data that isn't destroyed on app rotations.

* Repository: The repository depends on a persistent data model and a remote backend data source.

![mvvm-architecture-pattern](https://user-images.githubusercontent.com/65492308/184433445-c245b60b-13f1-4c3c-ab37-32e0cfe23180.png)

