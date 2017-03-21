package org.nomadblacky.samples.kotlin.nlp100

import io.kotlintest.specs.FunSpec

/**
 * 言語処理100本ノック 第1章
 * http://www.cl.ecei.tohoku.ac.jp/nlp100/#ch1
 *
 * Created by blacky on 17/03/18.
 */
class Chapter01Spec : FunSpec() {
    init {
        test("00. 文字列の逆順") {
            "stressed".reversed() shouldBe "desserts"
        }

        test("01. 「パタトクカシーー」") {
            val patrolCar = "パタトクカシーー".filterIndexed { index, _ -> index % 2 == 0 }
            patrolCar shouldBe "パトカー"
        }

        test("02. 「パトカー」＋「タクシー」＝「パタトクカシーー」") {
            val patrolCar = "パトカー"
            val taxi = "タクシー"
            val result = patrolCar.zip(taxi, {a,b -> a.toString() + b.toString()}).joinToString(separator = "") { s -> s }

            result shouldBe "パタトクカシーー"
        }

        test("03. 円周率") {
            val text = "Now I need a drink, alcoholic of course, after the heavy lectures involving quantum mechanics."
            val regex = Regex("""\w+""")
            val result = regex.findAll(text).map { mr -> mr.value.length }.toList()
            result shouldBe listOf(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9)
        }

        test("04. 元素記号") {
            val text = "Hi He Lied Because Boron Could Not Oxidize Fluorine. New Nations Might Also Sign Peace Security Clause. Arthur King Can."
            val extractFirst = listOf(1, 5, 6, 7, 8, 9, 15, 16, 19).map { it - 1 }
            val regex = Regex("""\w+""")
            val result = regex.findAll(text).mapIndexed { i, mr ->
                if (extractFirst.contains(i)) i to mr.value.take(1) else i to mr.value.take(2)
            }.toMap()
            println(result)
        }

        fun ngram(input: String, n: Int): List<String> {
            val last = input.length - n
            return (0..last).map { input.slice(IntRange(it, (it + n - 1))) }.toList()
        }

        test("05. n-gram") {
            val text = "I am an NLPer"
            println(ngram(text, 2))
        }

        test("06. 集合") {
            val x = "paraparaparadise"
            val y = "paragraph"
            val biGramX = ngram(x, 2)
            val biGramY = ngram(y, 2)
            println(biGramX.toHashSet() + biGramY.toHashSet())
            println(biGramX.toHashSet() intersect biGramY.toHashSet())
            println(biGramX.toHashSet() - biGramY.toHashSet())
            println(biGramX.contains("se"))
            println(biGramY.contains("se"))
        }

        test("07. テンプレートによる文生成") {
            fun getText(x: Int, y: String, z: Double): String = "%d時の%sは%.1f".format(x, y, z)
            getText(12, "気温", 22.4) shouldBe "12時の気温は22.4"
        }

        test("08. 暗号文") {
            fun chpher(text: String): String {
                return text.map { if (it.isLowerCase()) (219 - it.toInt()).toChar() else it }.joinToString(separator = "")
            }
            val text = "I have a pen. I have an apple."
            text.let(::chpher).let(::chpher) shouldBe text
        }

    }
}
