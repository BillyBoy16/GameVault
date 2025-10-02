pluginManagement {
    repositories {
        google() // Utilisation du dépôt Google
        mavenCentral() // Utilisation de Maven Central
        gradlePluginPortal() // Utilisation du dépôt Gradle Plugin Portal
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GameVault"
include(":app")
 