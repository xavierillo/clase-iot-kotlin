plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.appiotandroidxml"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.appiotandroidxml"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true   // útil para XML
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // SplashScreen moderno (Android 12+ con compatibilidad hacia atrás)
    implementation("androidx.core:core-splashscreen:1.2.0")

    //retrofit2 es una librería para hacer llamadas HTTP en Android.
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    //retrofit2 converter es una librería para convertir datos JSON en objetos Kotlin o Java.
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    //okhttp3 es una librería para hacer llamadas HTTP en Android.
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    //kotlinc-core es una librería para generar código Kotlin en tiempo de ejecución.
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    implementation(libs.mediation.test.suite)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}