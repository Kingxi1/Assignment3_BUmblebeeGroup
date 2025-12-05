// app/build.gradle.kts
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
}

// 从 local.properties 读取 API key
val localProps = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) {
        file.inputStream().use { load(it) }
    }
}

android {
    namespace = "com.example.assignment3"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.assignment3"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // 把 key 写入 BuildConfig，Java 里可以用 BuildConfig.GOOGLE_MAPS_API_KEY
        buildConfigField(
            "String",
            "GOOGLE_MAPS_API_KEY",
            "\"${localProps.getProperty("GOOGLE_MAPS_API_KEY", "")}\""
        )
        buildConfigField(
            "String",
            "WEATHER_API_KEY",
            "\"${localProps.getProperty("WEATHER_API_KEY", "")}\""
        )

        // 把 Maps key 传给 Manifest 里的 ${GOOGLE_MAPS_API_KEY}
        manifestPlaceholders += mapOf(
            "GOOGLE_MAPS_API_KEY" to localProps.getProperty("GOOGLE_MAPS_API_KEY", "")
        )
    }

    buildFeatures {
        buildConfig = true
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Google Maps SDK
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    // Google Places
    implementation("com.google.android.libraries.places:places:3.5.0")

    // Volley（HTTP）
    implementation("com.android.volley:volley:1.2.1")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
