plugins {
    alias(libs.plugins.androidGradlePlugin) apply(false)
    alias(libs.plugins.kotlin) apply(false)
}

subprojects {
    version = "3.1.0-SNAPSHOT"
}
