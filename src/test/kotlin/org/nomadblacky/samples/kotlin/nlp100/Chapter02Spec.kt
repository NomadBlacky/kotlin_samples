package org.nomadblacky.samples.kotlin.nlp100

import io.kotlintest.specs.FunSpec

/**
 * Created by blacky on 17/03/25.
 */
class Chapter02Spec : FunSpec() {
    private val resource = javaClass.getResource("hightemp.txt")!!

    fun execProcess(vararg cmd: String): String {
        val process = ProcessBuilder(*cmd).start()
        process.waitFor()
        val result = process.inputStream.bufferedReader().readText()
        return result
    }

    init {
        test("10. 行数のカウント") {
            val lines = resource.readText().lines().filter(String::isNotEmpty).size
            val wcLines = execProcess("wc", "-l", resource.path).split(" ").first().toInt()
            lines shouldBe wcLines
        }

        test("11. タブをスペースに置換") {
            
        }
    }
}