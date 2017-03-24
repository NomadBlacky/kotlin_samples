package org.nomadblacky.samples.kotlin.nlp100

import io.kotlintest.specs.FunSpec

/**
 * Created by blacky on 17/03/25.
 */
class Chapter02Spec : FunSpec() {
    init {
        test("10. 行数のカウント") {
            println(javaClass)
            val rs = javaClass.getResource("hightemp.txt")
            val lines = rs.readText().lines().filter(String::isNotEmpty).size
            val pb = ProcessBuilder("wc", "-l", rs.path)
            val processWc = pb.start()
            processWc.waitFor()
            val wcLines = processWc.inputStream.bufferedReader().readText().split(" ").first().toInt()
            lines shouldBe wcLines
        }
    }
}