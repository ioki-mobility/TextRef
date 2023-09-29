import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

plugins {
    alias(libs.plugins.androidGradlePlugin)
    alias(libs.plugins.kotlin)
    `maven-publish`
}

kotlinExtension.jvmToolchain(19)

android {
    namespace = "com.ioki.textref.compose"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
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
    api(project(":core"))
    api(libs.bundles.android.compose)
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
            artifactId = "compose"
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
