plugins {
    alias(libs.plugins.androidGradlePlugin) apply(false)
    alias(libs.plugins.kotlin) apply(false)
}

subprojects {
    version = "3.0.0-SNAPSHOT"
}
