plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    namespace = "com.ioki.textref"

    compileSdk = 30

    defaultConfig {
        minSdk = 14
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions { jvmTarget = "11" }
}

dependencies {
    // Implementation
    compileOnly(libs.android.annotation)

    // Test
    testImplementation(libs.test.junit)
    testImplementation(libs.test.assertj)
    testImplementation(libs.test.mockito)

    // Android Test
    androidTestImplementation(libs.androidTest.junit)
    androidTestImplementation(libs.androidTest.runner)
    androidTestImplementation(libs.androidTest.extJunit)
    androidTestImplementation(libs.androidTest.assertParcelable)
}
