plugins {
    alias(libs.plugins.androidGradlePlugin) apply(false)
    alias(libs.plugins.kotlin) apply(false)
    alias(libs.plugins.nmcpAggregation)
}

allprojects {
    group = "com.ioki.textref"
    version = "3.2.0-SNAPSHOT"
}

nmcpAggregation {
    centralPortal {
        username = providers.environmentVariable("SONATYPE_USER")
        password = providers.environmentVariable("SONATYPE_PASSWORD")
        publishingType = "USER_MANAGED"
    }

    publishAllProjectsProbablyBreakingProjectIsolation()
}