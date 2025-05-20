plugins {
    alias(libs.plugins.androidGradlePlugin)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.compose)
    `maven-publish`
    signing
}

kotlin.jvmToolchain(19)

android {
    namespace = "com.ioki.textref.compose"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
    }
    buildFeatures.compose = true
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions { jvmTarget = "1.8" }
}

dependencies {
    api(project(":core"))
    api(libs.bundles.android.compose)
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
            artifactId = "compose"

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

    repositories {
        maven("https://central.sonatype.com/repository/maven-snapshots/") {
            name = "SonatypeSnapshot"
            credentials {
                username = System.getenv("SONATYPE_USER")
                password = System.getenv("SONATYPE_PASSWORD")
            }
        }
    }
}

signing {
    val signingKey = System.getenv("GPG_SIGNING_KEY")
    val signingPassword = System.getenv("GPG_SIGNING_PASSWORD")
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
}

