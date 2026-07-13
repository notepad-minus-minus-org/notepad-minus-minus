plugins {
    id("java")
    id("com.diffplug.spotless") version "8.8.0"
    application
}

group = "com.nmm"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_24
    targetCompatibility = JavaVersion.VERSION_24
}

application {
    mainClass.set("com.nmm.Main")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("com.formdev:flatlaf:3.7")
    implementation("org.apache.pdfbox:pdfbox:3.0.5")
}

tasks.test {
    useJUnitPlatform()
}

spotless {
    java {
        palantirJavaFormat("2.94.0").style("AOSP")
        removeUnusedImports()
    }
}

tasks.register<Copy>("installGitHooks") {
    description = "Copy the pre-push hook"
    group = "verification"
    from(file("$rootDir/config/git/pre-push"))
    into(file("$rootDir/.git/hooks"))

    filePermissions {
        user {
            read = true
            write = true
            execute = true
        }
        group {
            read = true
            execute = true
        }
        other {
            read = true
            execute = true
        }
    }
}

tasks.named("classes") {
    dependsOn("installGitHooks")
}

tasks.named("build") {
    dependsOn("spotlessApply")
}

tasks.named("run") {
    dependsOn("spotlessApply")
}
