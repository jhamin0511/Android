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
include(":app")
include(":core:design")
include(":Asynchronous:AsyncTask")
include(":Asynchronous:Executor")
include(":Asynchronous:Handler")
include(":Asynchronous:HandlerThread")
include(":Asynchronous:Thread")
