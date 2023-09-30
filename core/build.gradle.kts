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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions { jvmTarget = "1.8" }
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

android.publishing {
    singleVariant("release") {
        withSourcesJar()
        withJavadocJar()
    }
}

val projectVersion = version as String
publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.ioki-mobility.TextRef"
            artifactId = "core"
            version = projectVersion

            pom {
                url.set("https://github.com/ioki-mobility/TextRef")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/ioki-mobility/TextRef/blob/main/LICENSE.md")
                    }
                }
                organization {
                    name.set("ioki")
                    url.set("https://ioki.com")
                }
                scm {
                    url.set("https://github.com/ioki-mobility/TextRef")
                    connection.set("https://github.com/ioki-mobility/TextRef.git")
                    developerConnection.set("git@github.com:ioki-mobility/TextRef.git")
                }
            }

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
