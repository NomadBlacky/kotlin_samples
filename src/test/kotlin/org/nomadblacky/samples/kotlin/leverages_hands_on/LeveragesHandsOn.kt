package org.nomadblacky.samples.kotlin.leverages_hands_on

import io.kotlintest.specs.FunSpec

/**
 * Created by blacky on 17/04/15.
 */
class LeveragesHandsOn : FunSpec() {
    init {
        test("1. Kotlinってなに?") {
            /**
             * 静的型付けオブジェクト指向プログラミング言語
             * 開発者: JetBrains
             * 2016年2月リリース
             *
             * Javaだと...
             * + 冗長、ボイラープレート
             * + nullポインタ
             * + 広報互換性
             *   + 古い文法が存在
             *   + 型安全性に影響
             *
             * Kotlinなら ...
             * + Javaよりも安全、簡潔
             * + Javaエンジニアにとって学習コスト低
             * + JetBrainsの後ろ盾
             * + 公式のAndroidサポート
             * + JavaScriptへのトランスパイル、LLVM(実験段階)
             */
        }

        test("2. 開発環境の準備") {
            /**
             * Try Kotlin ... ブラウザ上で動く環境
             * http://try.kotl.in
             *
             * IntelliJ IDEA + Kotlin Plugin
             */
        }

        test("3. Hello World") {
            // package sample.package
            fun main(args: Array<String>) {
                println("Hello world!")
            }
            main(arrayOf())
            /**
             * packageとファイルシステムは無関係
             * トップレベルに関数をおける
             * 配列はクラスで表現される。特別な文法はない。
             * セミコロンは不要
             */
        }

        test("4. 基本的な文法") {
            // 基本的にJavaと変わらない
            1 + 2 shouldBe 3
            2 * 3 shouldBe 6
            3 * 4.0 shouldBe 12.0
            (4 < 5) shouldBe true
            'A'.isDigit() shouldBe false
            "Hi".toUpperCase() shouldBe "HI"

            // ↓変数宣言    ↓型
            val userName: String = "ほげ"
            val userAge: Int = 28
            // Stringテンプレート
            "${userName}さんは${userAge}歳です" shouldBe "ほげさんは28歳です"

            //           ↓型を省略できる
            val userName2 = "ほげ"
            val userAge2 = 28
            "${userName2}さんは${userAge2}歳です" shouldBe "ほげさんは28歳です"

            // 条件分岐
            val score = 65
            if (score >= 60) {
                println("合格")
            } else {
                println("失格")
            }
            // ifは式 ... 値を返す
            val score2 = 65
            val message = if (score2 >= 60) {
                "合格"
            } else {
                "失格"
            }
            message shouldBe "合格"

            // forループ
            val names = listOf("hoge", "foo", "bar")
            for (name in names) {
                println(name)
            }

            // 変数
            val nums = listOf(1, 2, 3)
            var sum = 0
            for (n in nums) {
                sum = sum + n
            }
            sum shouldBe 6

            // when式
            val word = "one"
            val num = when(word) {
                "zero" -> 0
                "one" -> 1
                "two" -> 2
                else -> -1
            }
            num shouldBe 1

            // if-else たくさん問題
            fun result(score: Int): String {
                return when {
                    80 <= score && score <= 100 -> "すっごーい!"
                    60 <= score && score < 80   -> "合格"
                    0 <= score && score < 60    -> "不合格"
                    else -> "は?"
                }
            }
            result(100) shouldBe "すっごーい!"
            result(80) shouldBe "すっごーい!"
            result(79) shouldBe "合格"
            result(60) shouldBe "合格"
            result(59) shouldBe "不合格"
            result(0) shouldBe "不合格"
            result(-1) shouldBe "は?"
        }

        test("4. [課題] FizzBuzz") {
            for (i in 1..100) {
                val s = when {
                    i % 3 == 0 && i % 5 == 0 -> "FizzBuzz"
                    i % 3 == 0 -> "Fizz"
                    i % 5 == 0 -> "Buzz"
                    else -> i.toString()
                }
                println(s)
            }
            for (i in 1..100) {
                val p = Pair(i % 3, i % 5)
                val msg = when {
                    p.first == 0 && p.second == 0 -> "FizzBuzz"
                    p.first == 0  -> "Fizz"
                    p.second == 0 -> "Buzz"
                    else          -> i.toString()
                }
                println(msg)
            }
        }

        test("5. 関数に挑戦") {
            //      ↓引数             ↓戻り値の型
            fun add(a: Int, b: Int): Int {
                return a + b
            }
            add(1, 2) shouldBe 3

        }

        test("5. [課題] 関数を定義しよう") {
            // 引数として与えられた整数を2乗した数を返すsquare
            fun square(x: Int): Int = x * x
            square(2) shouldBe 4
            square(-5) shouldBe 25

            // 引数として与えられた2つの整数のうち、大きい方を返すmax
            fun max(a: Int, b: Int): Int = if (a > b) a else b
            max(2, 1) shouldBe 2
            max(-2, -1) shouldBe -1

            // 引数として与えられた整数が偶数の場合にtrue、それ以外はfalseを返すisEven
            fun isEven(x: Int): Boolean = x % 2 == 0
            isEven(1) shouldBe false
            isEven(2) shouldBe true

            // 引数として与えられた整数の階乗を返すfactorial
            fun factorial(x: Int): Int = (2..x).fold(1) { acc, x -> acc * x }
            factorial(0) shouldBe 1
            factorial(1) shouldBe 1
            factorial(3) shouldBe 6
            factorial(4) shouldBe 24
            factorial(5) shouldBe 120
        }

        test("6. クラス") {
            // プライマリコンストラクタ
            class Person(val name: String) {
                fun introduceMyself(): String = "I am $name."
            }

            val hoge = Person("hoge")
            hoge.name shouldBe "hoge"
            hoge.introduceMyself() shouldBe "I am hoge."
        }

        class IntPair(val first: Int, val second: Int) {
            fun sum(): Int = first + second
            fun max(): Int = if (first > second) first else second
            fun swap(): IntPair = IntPair(second, first)
        }

        test("6. [課題] クラスを定義しよう") {
            val p = IntPair(10, 20)
            p.sum() shouldBe 30
            p.max() shouldBe 20
            val ps = p.swap()
            ps.first shouldBe 20
            ps.second shouldBe 10
        }

        test("7. 拡張関数") {
            //  ↓メソッドを生やすクラス                 ↓thisは自身を参照する
            fun String.hello(): String = "Hello, $this!"
            "hoge".hello() shouldBe "Hello, hoge!"
        }

        test("7. [課題] 拡張関数をつくろう") {
            /**
             * 拡張関数meet
             * + Intに対する拡張関数
             * + Int型の引数をひとつ取る
             * + 地震がfirstに、引数がsecondになるIntPairオブジェクトを返す
             */
            // infix ... 中置記法
            infix fun Int.meet(x: Int): IntPair = IntPair(this, x)
            val p = 10.meet(20)
            p.first shouldBe 10
            p.second shouldBe 20
            val p2 = 10 meet 20 // 中置記法で
            p2.first shouldBe 10
            p2.second shouldBe 20
        }

        test("次のステップへ...") {
            /**
             * Kotlin Koans
             * https://kotlinlang.org/docs/tutorials/koans.html
             *
             * Kotlinを始めようハンズオン(Android)
             * https://goo.gl/e4A261
             */
        }

        test("質問") {
            /**
             * Q. JDKのバージョンはいくつ以上?
             * A. 1.6 以上が必要
             *
             * Q. KotlinにおけるのJavadocは?
             * A. Kdoc
             *
             * Q. ユニットテストは?
             * A. JUnit,spek
             *
             * Q. Android以外に使われてるところは?
             * A. Webフレームワーク ... 有力なフレームワークはない? → Springは使える
             * A. Sparkとの相性よい
             *
             * Q. 静的コード解析?
             * A. ない? FindBugsはclassファイルに対して解析するのでいけるかも?
             */
        }
    }

}