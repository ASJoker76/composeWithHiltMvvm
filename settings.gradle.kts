pluginManagement {
    repositories {
        google()
        jcenter()   // use mavenCentral()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        jcenter()   // use mavenCentral()
        flatDir {
            dirs("src/main/libs")
        }
        mavenCentral()
    }
}

rootProject.name = "kosong"
include(":app")
 