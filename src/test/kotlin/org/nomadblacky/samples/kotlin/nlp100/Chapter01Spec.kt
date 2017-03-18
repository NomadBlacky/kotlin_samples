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

        test("05. n-gram") {
            fun ngram(input: String, n: Int): List<String> {
                val last = input.length - n
                return (0..last).map { input.slice(IntRange(it, (it + n - 1))) }.toList()
            }
            val text = "I am an NLPer"
            println(ngram(text, 2))
        }
    }
}