package com.dgsdddsample.infrastructure

import com.dgsdddsample.domain.show.*
import com.dgsdddsample.infrastructure.db.connectDatabase
import com.dgsdddsample.infrastructure.db.testTransaction
import com.dgsdddsample.infrastructure.repository.ShowExposedRepository
import com.dgsdddsample.infrastructure.table.ShowTable
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.jetbrains.exposed.sql.insert

class ShowExposedRepositoryTest : BehaviorSpec() {
    init {
        connectDatabase()
        val showExposedRepository = ShowExposedRepository()

        Given("findById") {
            val show = Show(
                id = ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWEW"),
                version = ShowVersion.init,
                title = Title("test"),
                releaseYear = ReleaseYear(2022),
            )

            When("record exists") {
                val result = testTransaction {
                    ShowTable.insert {
                        it[id] = show.id.string()
                        it[version] = show.version.int()
                        it[title] = show.title.string()
                        it[releaseYear] = show.releaseYear.int()
                    }
                    showExposedRepository.findById(ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWEW"))
                }

                Then("returns result") {
                    result shouldBe show
                }
            }

            When("record does not exist") {
                val result = testTransaction {
                    showExposedRepository.findById(ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWE0"))
                }

                Then("returns null") {
                    result shouldBe null
                }
            }
        }
    }
}
