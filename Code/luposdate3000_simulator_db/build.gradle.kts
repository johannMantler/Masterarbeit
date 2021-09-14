import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
buildscript {
    repositories {
        mavenLocal()
        jcenter()
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
    }
}
evaluationDependsOn(":src:luposdate3000_shared")
evaluationDependsOn(":src:luposdate3000_dictionary")
evaluationDependsOn(":src:luposdate3000_endpoint")
evaluationDependsOn(":src:luposdate3000_endpoint_launcher_java_sockets")
evaluationDependsOn(":src:luposdate3000_operator_base")
evaluationDependsOn(":src:luposdate3000_operator_factory")
evaluationDependsOn(":src:luposdate3000_operator_physical")
evaluationDependsOn(":src:luposdate3000_result_format")
evaluationDependsOn(":src:luposdate3000_optimizer_distributed_query")
evaluationDependsOn(":src:luposdate3000_visualize_distributed_database")
evaluationDependsOn(":src:luposdate3000_buffer_manager_inmemory")
evaluationDependsOn(":src:luposdate3000_kv")
evaluationDependsOn(":src:luposdate3000_vk")
evaluationDependsOn(":src:luposdate3000_test_buffermanager")
evaluationDependsOn(":src:luposdate3000_optimizer_ast")
evaluationDependsOn(":src:luposdate3000_optimizer_logical")
evaluationDependsOn(":src:luposdate3000_optimizer_physical")
evaluationDependsOn(":src:luposdate3000_parser")
evaluationDependsOn(":src:luposdate3000_operator_arithmetik")
evaluationDependsOn(":src:luposdate3000_triple_store_manager")
evaluationDependsOn(":src:luposdate3000_operator_logical")
evaluationDependsOn(":src:luposdate3000_triple_store_id_triple")
evaluationDependsOn(":src:luposdate3000_jena_wrapper_off")
evaluationDependsOn(":src:luposdate3000_shared_js_browser")
plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.5.0"
}
repositories {
    mavenLocal()
    jcenter()
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
}
group = "luposdate3000"
version = "0.0.1"
apply(plugin = "maven-publish")
kotlin {
    explicitApi()
    metadata {
        compilations.forEach {
            it.kotlinOptions {
                freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
                freeCompilerArgs += "-Xnew-inference"
                freeCompilerArgs += "-Xinline-classes"
            }
        }
    }
    jvm {
        compilations.forEach {
            it.kotlinOptions {
                jvmTarget = "1.8"
                useIR = true
                freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
                freeCompilerArgs += "-Xno-param-assertions"
                freeCompilerArgs += "-Xnew-inference"
                freeCompilerArgs += "-Xno-receiver-assertions"
                freeCompilerArgs += "-Xno-call-assertions"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.ionspin.kotlin:bignum:0.3.1-SNAPSHOT")
                implementation(project(":src:luposdate3000_shared"))
                implementation(project(":src:luposdate3000_dictionary"))
                implementation(project(":src:luposdate3000_endpoint"))
                implementation(project(":src:luposdate3000_endpoint_launcher_java_sockets"))
                implementation(project(":src:luposdate3000_operator_base"))
                implementation(project(":src:luposdate3000_operator_factory"))
                implementation(project(":src:luposdate3000_operator_physical"))
                implementation(project(":src:luposdate3000_result_format"))
                implementation(project(":src:luposdate3000_optimizer_distributed_query"))
                implementation(project(":src:luposdate3000_visualize_distributed_database"))
                implementation(project(":src:luposdate3000_buffer_manager_inmemory"))
                implementation(project(":src:luposdate3000_kv"))
                implementation(project(":src:luposdate3000_vk"))
                implementation(project(":src:luposdate3000_test_buffermanager"))
                implementation(project(":src:luposdate3000_optimizer_ast"))
                implementation(project(":src:luposdate3000_optimizer_logical"))
                implementation(project(":src:luposdate3000_optimizer_physical"))
                implementation(project(":src:luposdate3000_parser"))
                implementation(project(":src:luposdate3000_operator_arithmetik"))
                implementation(project(":src:luposdate3000_triple_store_manager"))
                implementation(project(":src:luposdate3000_operator_logical"))
                implementation(project(":src:luposdate3000_triple_store_id_triple"))
                implementation(project(":src:luposdate3000_jena_wrapper_off"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("com.soywiz.korlibs.krypto:krypto-jvm:1.9.1")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
    }
    sourceSets["commonMain"].kotlin.srcDir("D:/ideaprojects/luposdate3000/src/xxx_generated_xxx/src/luposdate3000_simulator_db/src/commonMain/kotlin")
    sourceSets["commonMain"].resources.srcDir("D:/ideaprojects/luposdate3000/src/xxx_generated_xxx/src/luposdate3000_simulator_db/src/commonMain/resources")
    sourceSets["jvmMain"].kotlin.srcDir("D:/ideaprojects/luposdate3000/src/xxx_generated_xxx/src/luposdate3000_simulator_db/src/jvmMain/kotlin")
    sourceSets["jvmMain"].resources.srcDir("D:/ideaprojects/luposdate3000/src/xxx_generated_xxx/src/luposdate3000_simulator_db/src/jvmMain/resources")
}
tasks.register("luposSetup") {
    fun fixPathNames(s: String): String {
        var res = s.trim()
        var back = ""
        while (back != res) {
            back = res
            res = res.replace("\\", "/").replace("/./", "/").replace("//", "/")
        }
        return res
    }
    val regexDisableNoInline = "(^|[^a-zA-Z])noinline ".toRegex()
    val regexDisableInline = "(^|[^a-zA-Z])inline ".toRegex()
    val regexDisableCrossInline = "(^|[^a-zA-Z])crossinline ".toRegex()
    for (bp in listOf(File(buildDir.parentFile, "/src"), File(rootDir, "src/luposdate3000_shared_inline/src"))) {
        for (it in bp.walk()) {
            val tmp = it.toString()
            val ff = File(tmp)
            if (ff.isFile() && ff.name.endsWith(".kt")) {
                File(ff.absolutePath + ".tmp").printWriter().use { out ->
                    var line = 0
                    ff.forEachLine { line2 ->
                        var s = line2
                        s = s.replace("SOURCE_FILE_START.*SOURCE_FILE_END".toRegex(), "SOURCE_FILE_START*/\"${fixPathNames(ff.absolutePath)}:$line\"/*SOURCE_FILE_END")
                        s = s.replace("/*NOINLINE*/", "noinline ")
                        s = s.replace("/*CROSSINLINE*/", "crossinline ")
                        s = s.replace("/*INLINE*/", "inline ")
                        out.println(s)
                        line++
                    }
                }
                File(ff.absolutePath + ".tmp").copyTo(ff, true)
                File(ff.absolutePath + ".tmp").delete()
            }
        }
    }
}
tasks.named("compileKotlinJvm") {
    dependsOn("luposSetup")
    doLast {
        File(buildDir, "external_jvm_dependencies").printWriter().use { out ->
            for (f in configurations.getByName("jvmRuntimeClasspath").resolve()) {
                if (!"$f".contains("luposdate3000")) {
                    out.println("$f")
                }
            }
        }
    }
}
tasks.named("build") {
}
tasks.withType<Test> {
    maxHeapSize = "1g"
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        showStandardStreams = true
        events.add(TestLogEvent.FAILED)
        events.add(TestLogEvent.STARTED)
        events.add(TestLogEvent.PASSED)
        events.add(TestLogEvent.SKIPPED)
        events.add(TestLogEvent.STANDARD_OUT)
        events.add(TestLogEvent.STANDARD_ERROR)
    }
}
