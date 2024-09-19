plugins {
    application
    id("org.jetbrains.kotlin.jvm") version "1.9.24"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation(platform("org.hibernate.orm:hibernate-platform:6.6.0.Final"))
    implementation("org.hibernate.orm:hibernate-core")
    implementation("jakarta.transaction:jakarta.transaction-api")
    implementation("com.h2database:h2:2.2.220")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "no.hvl.dat250.jpa.tutorial.basicexample.Main"
}


tasks.named<Test>("test") {
    useJUnitPlatform()
}
