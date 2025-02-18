plugins {
    id("java")
}

group = "dev.itsu.bjvmdemo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(files("libs/bjvm-webapi-bindings.jar"))
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "dev.itsu.bjvmdemo.Main"
    }

    archiveFileName.set("app.jar")
    destinationDirectory.set(file("../frontend-js/public/java"))
}

tasks.compileJava {
    // 文字列結合に invokeDynamic を使用しないようにする（Java8 までのインライン結合を利用）
    options.compilerArgs = arrayListOf("-XDstringConcat=inline")
}
