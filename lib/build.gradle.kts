import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

plugins {
    alias(libs.plugins.androidGradlePlugin)
    alias(libs.plugins.kotlin)
    `maven-publish`
}

kotlinExtension.jvmToolchain(19)

android {
    namespace = "com.ioki.textref"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
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

android.publishing {
    singleVariant("release") {
        withSourcesJar()
        withJavadocJar()
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.ioki-mobility"
            artifactId = "TextRef"
            version = "1.3.1"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
