
plugins {
    `java-library`
    `jvm-test-suite`
    war
}

// Needed to specify against which JRE we are compiling against

java {                                      
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
 
group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    // This is a useless comment
    // implementation("jakarta.servlet:jakarta.servlet-api:5.0.0")
    // compileOnly("jakarta.servlet:jakarta.servlet-api:5.0.0")
    // implementation("jakarta.servlet:jakarta.servlet-api:4.0.3")
    implementation("javax.servlet:javax.servlet-api:4.0.0")
    implementation("javax.servlet:jstl:1.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8")
    implementation("mysql:mysql-connector-java:8.0.33")

    // implementation("jakarta.servlet:jakarta.servlet-api:4.0.3")
    // implementation("jakarta.servlet:jakarta.servlet-api:4.0.3")
    // compileOnly("jakarta.servlet:jakarta.servlet-api:4.0.3")
    // compileOnly("jakarta.servlet.jsp:jakarta.servlet.jsp-api:2.3.6")
    // compileOnly("javax.servlet:javax.servlet-api:4.0.1")
    // compileOnly("javax.servlet.jsp:javax.servlet.jsp-api:2.3.3")
}


tasks {
    withType<JavaCompile> {
        options.compilerArgs.addAll(listOf("-Xlint:unchecked", "-Xlint:deprecation"))
    }

    withType<Test> {
        useJUnit()
    }

    val cuztomTest by creating(Test::class) {
        group = "verification"
        description = "Runs all verification tasks"
    }

    named("check").configure {
        dependsOn(cuztomTest)
    }
}

// New Test definition format allows for multiple SEPARATE sets of test to be
// defined and run independently. Reference at:
// https://docs.gradle.org/8.1/userguide/upgrading_version_8.html#test_task_default_classpath
// This snippet complements the soon-to-be-deprecated behavior, but with the
// additional specifications of a particular classpath and testClassesDirs
// so as to not raise warning from gradle. This specific definitions is what
// will allow us to run independent sets of tests
val test by testing.suites.existing(JvmTestSuite::class)
tasks.named<Test>("cuztomTest") {
    testClassesDirs = files(test.map { it.sources.output.classesDirs })
    classpath = files(test.map { it.sources.runtimeClasspath })
}


sourceSets {
    main {
        java {
            srcDir("src/main/java")
        }
        resources {
            srcDir("src/main/resources")
        }
    }

    test {
        java {
            srcDir("src/test/java")
        }
        resources {
            srcDir("src/test/resources")
        }
    }
}

val webAppDir = "src/main/webapp"

tasks.war {
    // webXml = file("$webAppDir/WEB-INF/web.xml")
    from(webAppDir)
    archiveBaseName.set("webdev_bown")
    archiveVersion.set("")
    // classpath(fileTree("additionalLibs")) // adds a file-set to the WEB-INF/lib dir.
    // destinationDir = file("$buildDir/dist")
}

tasks.named("clean") {
    doLast {
        delete("$buildDir/dist")
    }
}
