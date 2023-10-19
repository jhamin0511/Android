pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Android"
include(":core:design")
include(":core:model")
include(":core:testing")
include(":ModelViewController:mvc")
include(":ModelViewController:model")
include(":ModelViewController:view")
include(":ModelViewController:controller")
