package org.nomadblacky.samples.kotlin.hoge

import io.kotlintest.specs.FunSpec

/**
 * Created by blacky on 17/03/14.
 */
class HogeTest : FunSpec() {
    init {
        test("hoge") {
            "hoge".length shouldBe 4
        }
    }
}
