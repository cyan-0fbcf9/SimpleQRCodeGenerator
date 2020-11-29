import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.3.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
}

group = "com.cyan-0fbcf9"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    val zxingVersion = "3.4.1"
    implementation("com.google.zxing:core:${zxingVersion}")
    implementation("com.google.zxing:javase:${zxingVersion}")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.register("npmInstall", Exec::class) {
    commandLine = listOf("powershell", "-Command", "npm", "--prefix", "${rootDir}/web", "install", "${rootDir}/web")
}
tasks.register("npmLint", Exec::class) {
    commandLine = listOf("powershell", "-Command", "npm", "--prefix", "${rootDir}/web", "run", "lint")
}

tasks.register("npmBuild", Exec::class) {
    if (!file("${rootDir}/web/node_modules").exists()) {
        dependsOn("npmInstall")
    }
    dependsOn("npmLint")
    commandLine = listOf("powershell", "-Command", "npm", "--prefix", "${rootDir}/web", "run", "build")
}

tasks.processResources {
    dependsOn("npmBuild")
}
