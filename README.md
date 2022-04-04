![Thumbnail](https://user-images.githubusercontent.com/49922631/161585003-2b77ea0a-9d18-4521-bc34-c4a92ba95917.png)

![Kotlin version](https://img.shields.io/badge/kotlin-v1.6.1-purple) ![Room version](https://img.shields.io/badge/room-v2.4.2-green) ![Retrofit version](https://img.shields.io/badge/retrofit2-v2.9.0-yellowgreen) ![Dagger version](https://img.shields.io/badge/dagger2-v2.41-blue) ![Picasso version](https://img.shields.io/badge/picasso-v2.71828-red) ![Coroutines version](https://img.shields.io/badge/coroutines-v1.6-orange) ![Java version](https://img.shields.io/badge/Java-v11-brightgreen)
## Recommendations
- Android Studio Bumblebee 2021.1.1 Patch 2
- Android 7.0 +

## Basic Overview

Application that displays : 

- 50 cryptocurrencies
- Their value in dollars
- Detailed information about each cryptocurrency
- Minimum and maximum cost per day
- Updates every minute

## Screens
Dark Theme

![screen_one_day](https://user-images.githubusercontent.com/49922631/161603598-ca9f833d-25b8-420c-9f64-d57d538ae1ab.png)
![screen_two_day](https://user-images.githubusercontent.com/49922631/161603623-84665ef5-b153-4618-baa6-7b46747c11bb.png)

Light Theme

![screen_one_dark](https://user-images.githubusercontent.com/49922631/161603907-1452666f-8040-4074-8fdc-d33671d2c0f6.png)
![screen_second_dark](https://user-images.githubusercontent.com/49922631/161603904-2dcc3115-1efe-4cfc-a968-59e742a664c2.png)

## API
Here you can get a free api key for 100,000 queries -https://min-api.cryptocompare.com

There is documentation for working with the api - https://min-api.cryptocompare.com/documentation

Here you can keep track of how many requests were made and how many you have left - https://www.cryptocompare.com/cryptopian/api-keys

P.S 
If you need to change the refresh time (the default refresh time is 1 minute) then you need to go to: com.example.cryptocheck -> data -> workers -> RefreshDataWorker -> line 39

```
delay(60000) // 1 minute
```
The value is in milliseconds (1 minute = 60,000 milliseconds). If you decrease the value, then the application will update the data on cryptocurrencies faster, as this will increase the number of requests, which in turn leads to a faster spending limit of requests and vice versa respectively

## Default configuration  

Java Version
```
compileOptions {
        sourceCompatibility JavaVersion.VERSION_11
        targetCompatibility JavaVersion.VERSION_11
    }
```
View Binding
```
buildFeatures {
        viewBinding true
    }
```

## Dependencies 

CryptoCheck currently has the following dependencies

```
dependencies {
    implementation 'androidx.appcompat:appcompat:1.4.1'
    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'com.google.android.material:material:1.5.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.3'
    implementation 'androidx.recyclerview:recyclerview:1.2.1'
    implementation 'androidx.cardview:cardview:1.0.0'

    // Add coroutines dependencies
    def coroutines_version = "1.6.0"

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"

    // Add retrofit dependencies

    def retrofit_version = "2.9.0"

    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit_version")

    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation 'androidx.test.ext:junit-ktx:1.1.3'
    testImplementation 'junit:junit:4.13.2'

    // Add room dependencies

    def room_version = "2.4.2"

    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    // Add picasso dependencies

    def picasso_version = "2.71828"

    implementation ("com.squareup.picasso:picasso:$picasso_version")

    // Add work manager dependencies

    def work_manager_version = "2.7.1"

    implementation ("androidx.work:work-runtime-ktx:$work_manager_version")

    // Add Dagger dependencies

    def dagger_version = "2.41"

    implementation ("com.google.dagger:dagger:$dagger_version")
    kapt ("com.google.dagger:dagger-compiler:$dagger_version")
}
```
## Explanation of the code
Since the project is mainly a training project, there are a lot of comments and explanations in the code.

If you, like me, have difficulty with the English language, I advise you to use this translator. I think it captures the essence better - https://www.deepl.com

An example of what it looks like
![exp_code](https://user-images.githubusercontent.com/49922631/161605358-4f302bbc-dca2-42a0-afcc-6f5fcc32c147.png)

## License

MIT
