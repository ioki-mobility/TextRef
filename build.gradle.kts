plugins {
    alias(libs.plugins.androidGradlePlugin) apply(false)
    alias(libs.plugins.kotlin) apply(false)
    alias(libs.plugins.wrapperUpgrade)
}

subprojects {
    version = "2.1.0"
}

wrapperUpgrade {
    gradle {
        create("textref") {
            repo.set("ioki-mobility/TextRef")
            baseBranch.set("main")
        }
    }
}
