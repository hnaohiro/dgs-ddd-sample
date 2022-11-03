package com.dgsdddsample.domain.show

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.be
import io.kotest.matchers.should
import io.kotest.matchers.string.startWith

class ShowIdTest : BehaviorSpec() {
    init {
        Given("Constructor") {
            When("value is valid") {
                Then("returns valid ShowId") {
                    val showId = ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWEW")
                    showId.string() should be("01GGXJJSBWQZYP8FZAGEB4YWEW")
                }
            }

            When("length is not equal to 26") {
                Then("throws IllegalArgumentException") {
                    val exception = shouldThrow<IllegalArgumentException> {
                        ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWE")
                    }
                    exception.message should startWith("length should equal 26")
                }
            }
        }
    }
}
