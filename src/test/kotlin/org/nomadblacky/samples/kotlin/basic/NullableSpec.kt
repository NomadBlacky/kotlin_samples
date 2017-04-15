package org.nomadblacky.samples.kotlin.basic

import io.kotlintest.specs.FunSpec

/**
 * Created by blacky on 17/04/15.
 */
class NullableSpec : FunSpec() {
    init {
        test("Safe Calls") {
            // Error
            // val a: String = null

            // OK
            val a: String? = null
            val b: String? = "hoge"

            // Error
            // val a.length

            // OK (Safe Calls)
            a?.length shouldBe null
            b?.length shouldBe 4
        }

        test("Method Chain") {
            class Hoge(val i: Int) {
                fun getNull(): Hoge? = null
                fun getHoge(): Hoge? = this
            }
            Hoge(1).getHoge()?.getHoge()?.getHoge()?.i shouldBe 1
            Hoge(1).getHoge()?.getNull()?.getHoge()?.i shouldBe null
        }

        test("Collections of Nullable Type") {
            val nullableList: List<Int?> = listOf(1, 2, null, 4)
            val intList: List<Int> = nullableList.filterNotNull()
            intList shouldBe listOf(1, 2, 4)
        }
    }
}