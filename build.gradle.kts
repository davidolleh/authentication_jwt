import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
	id ("org.jetbrains.kotlin.plugin.allopen") version "1.7.22"
	id ("org.jetbrains.kotlin.plugin.noarg") version "1.7.22"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	kotlin("plugin.jpa") version "1.7.22"
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.Embeddable")
	annotation("jakarta.persistence.MappedSuperclass")
}

noArg {
	annotation("com.my.Annotation")
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.Embeddable")
	annotation("jakarta.persistence.MappedSuperclass")
}


group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
//	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
//	developmentOnly("org.springframework.boot:spring-boot-devtools")

	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	//uuid 생성 package
	implementation("com.github.f4b6a3:ulid-creator:5.2.0")
	// mail api package
	implementation("com.sun.mail:javax.mail:1.6.2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

