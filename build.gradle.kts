plugins {
    alias(libs.plugins.androidGradlePlugin) apply(false)
    alias(libs.plugins.kotlin) apply(false)
}

subprojects {
    version = "2.2.2"
}
