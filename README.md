# GithubPetApp

The application displays Github most starred repos from the Github API.

Language: Kotlin\n
Design pattern: MVVM\n
Features: Coroutines Flow\n

Libraries:
* [Google Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Navigation component](https://developer.android.com/guide/navigation)
* [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
* [GSON](https://github.com/google/gson)

3d party libraries:
* [Retrofit](https://square.github.io/retrofit/)
* [Glide](https://github.com/bumptech/glide)

### Description

#### Google Hilt
Library for Dependency Injection. The simpler version of Dagger 2. For small application no needs to use a huge Dagger 2 library. Hilt is easy for implementation and is a good choice for most project

#### Navigation component
Library for navigation through fragments but  it is not necessary and overcomplicated for small projects. For this project uses only for example 

#### Paging 3
The good solution from google to use pagination in RecyclerView. It supports reverse pagination and also jumping from page to page and caching

#### GSON
Library for easy working with Json files

#### Retrofit
Retrofit is a third party library but for now is the most popular library for REST networking

#### Glide
Library for loading images from the network. Easy for use and recommended by Google. 