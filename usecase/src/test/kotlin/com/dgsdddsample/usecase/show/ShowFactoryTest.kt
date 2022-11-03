package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.shared.ULID
import com.dgsdddsample.domain.shared.ULIDGenerator
import com.dgsdddsample.domain.show.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import io.mockk.every
import io.mockk.mockk

class ShowFactoryTest : BehaviorSpec() {
    private val showRepository = mockk<ShowRepository>()
    private val ulidGenerator = mockk<ULIDGenerator>()
    private val showFactory = ShowFactory(showRepository, ulidGenerator)

    init {
        every { ulidGenerator.generate() } returns ULID("01GGXJJSBWQZYP8FZAGEB4YWEW")

        Given("build") {
            When("showRepository.existBy returns false") {
                every { showRepository.existBy(Title("test"), ReleaseYear(2022)) } returns false

                Then("returns valid Show") {
                    showFactory.build(Title("test"), ReleaseYear(2022)).let {
                        it shouldBe Show(
                            id = ShowId(ulidGenerator.generate()),
                            version = ShowVersion.init,
                            title = Title("test"),
                            releaseYear = ReleaseYear(2022),
                        )
                    }
                }
            }

            When("showRepository.existBy returns true") {
                every { showRepository.existBy(Title("test"), ReleaseYear(2022)) } returns true

                Then("throws IllegalArgumentException") {
                    val exception = shouldThrow<IllegalArgumentException> {
                        showFactory.build(Title("test"), ReleaseYear(2022))
                    }
                    exception.message should startWith("title and releaseYear already exist")
                }
            }
        }

        Given("buildWithIdAndVersion") {
            When("params are valid") {
                val showId = ShowId(ulidGenerator.generate())
                every { showRepository.findById(showId) } returns Show(
                    showId,
                    ShowVersion.init,
                    Title("test"),
                    ReleaseYear(2022)
                )

                Then("returns valid Show") {
                    showFactory.buildWithIdAndVersion(
                        showId,
                        ShowVersion.init,
                        Title("test2"),
                        ReleaseYear(2022)
                    ) shouldBe Show(
                        showId,
                        ShowVersion.init.increment(),
                        Title("test2"),
                        ReleaseYear(2022)
                    )
                }
            }

            When("showRepository.findById returns null") {
                val showId = ShowId(ulidGenerator.generate())
                every { showRepository.findById(showId) } returns null

                Then("throws IllegalArgumentException") {
                    val exception = shouldThrow<IllegalArgumentException> {
                        showFactory.buildWithIdAndVersion(showId, ShowVersion.init, Title("test"), ReleaseYear(2022))
                    }
                    exception.message should startWith("not found")
                }
            }

            When("version is invalid") {
                val showId = ShowId(ulidGenerator.generate())
                every { showRepository.findById(showId) } returns Show(
                    showId,
                    ShowVersion.init.increment(),
                    Title("test"),
                    ReleaseYear(2022)
                )

                Then("throws IllegalArgumentException") {
                    val exception = shouldThrow<IllegalArgumentException> {
                        showFactory.buildWithIdAndVersion(showId, ShowVersion.init, Title("test"), ReleaseYear(2022))
                    }
                    exception.message should startWith("invalid version")
                }
            }
        }
    }
}
