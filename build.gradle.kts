plugins {
    id("java")
    application
}

group = "com.nmm"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_26
    targetCompatibility = JavaVersion.VERSION_26
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
}

tasks.test {
    useJUnitPlatform()
}
