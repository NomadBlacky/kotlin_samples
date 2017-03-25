package org.nomadblacky.samples.kotlin.nlp100

import io.kotlintest.specs.FunSpec

/**
 * 言語処理100本ノック 第2章
 * http://www.cl.ecei.tohoku.ac.jp/nlp100/#ch2
 *
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
            val replaced = resource.readText().replace("\t", " ")
            val tr = execProcess("sed", "-E", """s/\t/ /g""", resource.path)
            replaced shouldBe tr
        }

        test("12. 1列目をcol1.txtに，2列目をcol2.txtに保存") {
            val columns =
                    resource.readText().lines().filter(String::isNotEmpty)
                            .map { it.split(Regex("\\s")) }
                            .map { it.get(0) to it.get(1) }
            val col1 = columns.map { it.first }.joinToString(separator = "\n")
            val col2 = columns.map { it.second }.joinToString(separator = "\n")
            val cut1 = execProcess("cut", "-f1", resource.path).trim()
            val cut2 = execProcess("cut", "-f2", resource.path).trim()

            col1 shouldBe cut1
            col2 shouldBe cut2
        }
    }
}