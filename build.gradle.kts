import io.ebean.gradle.EnhancePluginExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = properties["kotlinVersion"] as String
val karibuVersion = properties["karibuVersion"] as String
val vaadin8Version = properties["vaadin8Version"] as String
buildscript {
  val kotlinVersion: String by project
  repositories {
    mavenCentral()
  }

  dependencies {
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    classpath("io.ebean:ebean-gradle-plugin:11.37.1")
  }
}

plugins {
  id("org.jetbrains.kotlin.jvm") version "1.3.30"
  id("org.gretty") version "2.3.1"
  //id("com.devsoap.plugin.vaadin") version "1.4.1"
  war
}

repositories {
  mavenCentral()
  maven(url = "https://maven.vaadin.com/vaadin-addons/")
  // maven(url = "https://maven.vaadin.com/vaadin-prereleases")
  //maven(url = "https://dl.bintray.com/mvysny/github")
  //maven(url = "https://jcenter.bintray.com")
}

configurations.all {
  resolutionStrategy.cacheChangingModulesFor(4, "hours")
}

defaultTasks("clean", "build")

group = "dep_endereco"
version = "1.0-SNAPSHOT"

apply(plugin = "war")
apply(plugin = "kotlin")
apply(plugin = "io.ebean")

gretty {
  contextPath = "/"
  servletContainer = "jetty9.4"
}
//vaadin {
//  version = "8.5.2"
//}
configure<EnhancePluginExtension> {
  debugLevel = 9
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
  jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
  jvmTarget = "1.8"
}


dependencies {
  // Karibu-DSL dependency
  implementation("com.github.mvysny.karibudsl:karibu-dsl-v8:$karibuVersion")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  // logging
  // currently we are logging through the SLF4J API to LogBack. See logback.xml file for the logger configuration
  implementation("ch.qos.logback:logback-classic:1.2.3")
  implementation("org.slf4j:slf4j-api:1.7.25")
  // this will configure Vaadin to log to SLF4J
  implementation("org.slf4j:jul-to-slf4j:1.7.25")
  // test support
  //testCompile "com.github.kaributesting:karibu-testing-v8:0.4.15"
  testImplementation("com.github.mvysny.dynatest:dynatest:0.8")

  implementation("io.ebean:ebean:11.37.1")
  // compile "io.ebean:querybean-generator:11.37.1"
  implementation("io.ebean:ebean-querybean:11.37.1")
  //compile "io.ebean:ebean-annotation:4.7"
  implementation("io.ebean.tools:finder-generator:4.2.2")

  implementation("com.vaadin:vaadin-themes:$vaadin8Version")
  implementation("com.vaadin:vaadin-server:$vaadin8Version")
  implementation("com.vaadin:vaadin-client-compiled:$vaadin8Version")
  implementation("javax.servlet:javax.servlet-api:3.1.0")

  implementation("mysql:mysql-connector-java:5.1.47")

  implementation("org.apache.commons:commons-dbcp2:2.3.0")

  implementation("org.cups4j:cups4j:0.7.1")
  implementation("org.glassfish.jersey.core:jersey-client:2.27")
  implementation("org.glassfish.jersey.media:jersey-media-multipart:2.27")
  implementation("org.glassfish.jersey.inject:jersey-hk2:2.27")

  implementation("org.xerial:sqlite-jdbc:3.21.0.1")
  implementation("org.sql2o:sql2o:1.5.4")
  // https://mvnrepository.com/artifact/com.jolbox/bonecp
  implementation("com.jolbox:bonecp:0.8.0.RELEASE")

  implementation("org.imgscalr:imgscalr-lib:4.2")
  implementation("de.steinwedel.vaadin.addon:messagebox:4.0.21")
  implementation("org.vaadin.patrik:GridFastNavigation:2.3.10")
  implementation("org.vaadin:viritin:2.8")
  implementation("org.vaadin.crudui:crudui:2.3.0")
  implementation("org.vaadin.addons:filtering-grid:0.1.1")
  implementation("com.fo0.advancedtokenfield:AdvancedTokenField:0.4.1")
  // heroku app runner
  testImplementation("junit:junit:4.11")
}



