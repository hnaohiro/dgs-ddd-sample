package com.dgsdddsample.domain.show

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.be
import io.kotest.matchers.should
import io.kotest.matchers.string.startWith

class TitleTest : BehaviorSpec() {
    init {
        Given("Constructor") {
            When("value is valid") {
                Then("returns valid Title") {
                    val title = Title("test")
                    title.string() should be("test")
                }
            }

            When("value is empty") {
                Then("throws IllegalArgumentException") {
                    val exception = shouldThrow<IllegalArgumentException> {
                        Title("")
                    }
                    exception.message should startWith("value should not be empty")
                }
            }

            When("length is greater than 50") {
                Then("throws IllegalArgumentException") {
                    val exception = shouldThrow<IllegalArgumentException> {
                        Title("a".repeat(51))
                    }
                    exception.message should startWith("length should be less than 51")
                }
            }
        }
    }
}
