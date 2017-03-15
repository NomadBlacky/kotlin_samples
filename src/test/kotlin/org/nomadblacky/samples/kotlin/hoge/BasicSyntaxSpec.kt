package org.nomadblacky.samples.kotlin.hoge

import io.kotlintest.specs.FunSpec


/**
 * Created by blacky on 17/03/15.
 */
class BasicSyntaxSpec : FunSpec() {
    init {
        test("Variables") {
            var x = 10 // mutable
            val y = 20 // immutable
            x shouldBe 10
            y shouldBe 20

            x = 30
            // y = 40 error!
            x shouldBe 30
        }

        test("Functions") {
            fun sum(x: Int, y: Int): Int {
                return x + y
            }
            // shorthand notation
            fun sum2(x: Int, y: Int) = x + y

            sum(10, 20) shouldBe 30
            sum2(10, 20) shouldBe 30
        }

        test("String Template") {
            val i = 10
            val s = "HOGE"
            "i=${i} s=$s" shouldBe "i=10 s=HOGE"
        }

        test("if Statement") {
            fun longer(s1: String, s2: String): String {
                if (s1.length > s2.length) {
                    return s1
                } else {
                    return s2
                }
            }
            longer("hoge", "foo") shouldBe "hoge"
            longer("bar", "piyo") shouldBe "piyo"
        }

        test("when Statement") {
            fun whenWithArg(arg: Int): String {
                return when (arg) {
                    0 -> "zero"
                    1 -> "one"
                    else -> "huh?"
                }
            }
            fun whenWhithoutArg(arg: Int): String {
                return when {
                    arg == 0 -> "zero"
                    arg == 1 -> "one"
                    else -> "huh?"
                }
            }
            whenWithArg(0) shouldBe "zero"
            whenWithArg(1) shouldBe "one"
            whenWithArg(2) shouldBe "huh?"
            whenWhithoutArg(0) shouldBe "zero"
            whenWhithoutArg(1) shouldBe "one"
            whenWhithoutArg(2) shouldBe "huh?"
        }
    }
}