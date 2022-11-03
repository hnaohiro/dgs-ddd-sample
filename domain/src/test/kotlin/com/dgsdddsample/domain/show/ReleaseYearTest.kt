package com.dgsdddsample.domain.show

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.be
import io.kotest.matchers.should
import io.kotest.matchers.string.startWith

class ReleaseYearTest : BehaviorSpec() {
    init {
        Given("Constructor") {
            When("value is valid") {
                Then("returns valid ReleaseYear") {
                    val releaseYear = ReleaseYear(2000)
                    releaseYear.int() should be(2000)
                }
            }

            When("length is less than 1900") {
                Then("throws IllegalArgumentException") {
                    val exception = shouldThrow<IllegalArgumentException> {
                        ReleaseYear(1899)
                    }
                    exception.message should startWith("value should be greater than 1900")
                }
            }
        }
    }
}
