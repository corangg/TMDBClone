plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.tmdb"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tmdb"
        minSdk = 34
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

    buildFeatures{
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.fragment:fragment-ktx:1.7.0")
    implementation("androidx.activity:activity-ktx:1.9.0")

    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.8.0")

    implementation ("com.google.code.gson:gson:2.8.6")

    implementation ("androidx.viewpager2:viewpager2:1.0.0")

    implementation ("me.relex:circleindicator:2.1.6")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    implementation("com.google.android.material:material:1.12.0")//CardView

    implementation("com.github.bumptech.glide:glide:4.13.0")//Glide
    kapt("com.github.bumptech.glide:compiler:4.13.0")
}

kapt {
    correctErrorTypes = true
}