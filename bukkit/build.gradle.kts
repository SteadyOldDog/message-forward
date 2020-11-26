

plugins {
   id ("com.github.johnrengelman.shadow") version ("5.2.0")
   java
   kotlin ("jvm") version "1.4.10"
   id("org.jetbrains.kotlin.plugin.serialization") version("1.4.10")
}

group = "io.github.itsusinn.mc"
version = "1.0-SNAPSHOT"

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "1.8"

repositories {
   mavenCentral()
   jcenter()
   maven("https://repo.codemc.org/repository/maven-public")
   maven("https://papermc.io/repo/repository/maven-public/")
   maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
   maven("https://oss.sonatype.org/content/groups/public/")
   maven("https://kotlin.bintray.com/kotlinx")
}

dependencies {

   implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
   implementation("net.mamoe:mirai-core-qqandroid:1.3.3")

   implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
   implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.10")

   implementation ("org.kodein.di:kodein-di:7.1.0")

   compileOnly("com.github.shynixn.mccoroutine:mccoroutine-bukkit-api:0.0.5")
   implementation("com.github.shynixn.mccoroutine:mccoroutine-bukkit-core:0.0.5")

   implementation("com.charleskorn.kaml:kaml:0.24.0")

   implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
   implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.1")

   compileOnly("org.spigotmc:spigot-api:1.16.3-R0.1-SNAPSHOT")

}

