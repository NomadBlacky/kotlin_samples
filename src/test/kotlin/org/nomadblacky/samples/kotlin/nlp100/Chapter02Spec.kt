package org.nomadblacky.samples.kotlin.nlp100

import io.kotlintest.specs.FunSpec
import java.io.File
import java.util.logging.Logger
import kotlin.io.*

/**
 * 言語処理100本ノック 第2章
 * http://www.cl.ecei.tohoku.ac.jp/nlp100/#ch2
 *
 * Created by blacky on 17/03/25.
 */
class Chapter02Spec : FunSpec() {
    init {
        val logger = Logger.getLogger(this.javaClass.name)
        val resource = javaClass.getResource("hightemp.txt")!!

        fun execProcess(vararg cmd: String): String {
            logger.info(cmd.joinToString(separator = " "))
            val process = ProcessBuilder(*cmd).start()
            process.waitFor()
            val result = process.inputStream.bufferedReader().readText()
            return result
        }

        val resultDirPath = "build/nlp100/"
        val resultDir = File(resultDirPath)
        if (resultDir.exists() and resultDir.deleteRecursively().not()) {
            throw IllegalStateException("Failed to delete folder.")
        }
        if (resultDir.exists().not() and resultDir.mkdirs().not()) {
            throw IllegalStateException("Failed to create folder.")
        }

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

            File(resultDir, "col1.txt").printWriter().use { it.print(col1) }
            File(resultDir, "col2.txt").printWriter().use { it.print(col2) }
        }

        test("13. col1.txtとcol2.txtをマージ") {
            val f1 = File(resultDir, "col1.txt")
            val f2 = File(resultDir, "col2.txt")
            if (f1.exists().not() or f2.exists().not()) {
                throw IllegalStateException("File not found")
            }

            val result = f1.readText().lines().zip(f2.readText().lines()).joinToString(separator = "\n") {
                it.toList().joinToString(separator = "\t")
            }
            val paste = execProcess("paste", f1.path, f2.path).trim()

            result shouldBe paste
        }

        test("14. 先頭からN行を出力") {
            val n = 5
            val result = resource.readText().lines().take(n).joinToString(separator = "\n")
            val head = execProcess("head", "-n", n.toString(), resource.path).trim()
            result shouldBe head
        }

        test("15. 末尾のN行を出力") {
            val n = 5
            val result = resource.readText().lines().dropLastWhile(String::isEmpty).takeLast(n).joinToString(separator = "\n")
            val tail = execProcess("tail", "-n", n.toString(), resource.path).trim()
            result shouldBe tail
        }
    }
}