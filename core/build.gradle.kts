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
            groupId = "com.ioki.textref"
            artifactId = "textref"
            version = projectVersion

            pom {
                name.set("TextRef")
                description.set("An abstraction over Android strings with formatting support")
                url.set("https://github.com/ioki-mobility/TextRef")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                organization {
                    name.set("ioki")
                    url.set("https://ioki.com")
                }
                developers {
                    developer {
                        name.set("Stefan 'StefMa' M.")
                        email.set("StefMaDev@outlook.com")
                        url.set("https://StefMa.guru")
                        organization.set("ioki")
                        organizationUrl.set("https://ioki.com")
                    }
                }
                scm {
                    url.set("https://github.com/ioki-mobility/TextRef")
                    connection.set("scm:git:git://github.com/ioki-mobility/TextRef.git")
                    developerConnection.set("scm:git:ssh://git@github.com:ioki-mobility/TextRef.git")
                }
            }

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
