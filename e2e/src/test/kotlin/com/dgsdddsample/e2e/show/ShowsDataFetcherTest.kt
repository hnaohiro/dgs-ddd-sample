package com.dgsdddsample.e2e.show

import com.dgsdddsample.configuration.appModule
import com.dgsdddsample.domain.show.*
import com.dgsdddsample.infrastructure.repository.ShowExposedRepository
import com.dgsdddsample.presentation.datafetcher.ShowsDataFetcher
import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(classes = [DgsAutoConfiguration::class, ShowsDataFetcher::class])
@EnableAutoConfiguration
@Transactional
open class ShowsDataFetcherTest {
    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    @BeforeEach
    fun setup() {
        startKoin {
            modules(appModule)
        }

        val showRepository = ShowExposedRepository()
        showRepository.save(
            Show(
                id = ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWEW"),
                version = ShowVersion.init,
                title = Title("test"),
                releaseYear = ReleaseYear(2022),
            )
        )
    }

    @AfterEach
    fun cleanup() {
        stopKoin()
    }

    @Test
    fun shows() {
        val value = dgsQueryExecutor.executeAndExtractJsonPath<Map<String, Any>>(
            """
            {
                shows {
                    title
                    releaseYear
                }
            }
            """.trimIndent(),
            "data"
        )

        (value["shows"] as List<*>).size shouldBe 1
    }
}
