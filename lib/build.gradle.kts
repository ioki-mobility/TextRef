import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

plugins {
    alias(libs.plugins.androidGradlePlugin)
    alias(libs.plugins.kotlin)
}

kotlinExtension.jvmToolchain(19)

android {
    namespace = "com.ioki.textref"
    compileSdk = 31
    defaultConfig {
        minSdk = 14
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = libs.tools.android.compose.get().version
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions { jvmTarget = "1.8" }
}

dependencies {
    // Implementation
    compileOnly(libs.android.annotation)
    implementation(libs.bundles.android.compose)

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
