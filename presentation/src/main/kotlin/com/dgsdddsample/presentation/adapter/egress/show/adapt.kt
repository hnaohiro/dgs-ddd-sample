package com.dgsdddsample.presentation.adapter.egress.show

import com.dgsdddsample.domain.show.Show
import com.dgsdddsample.presentation.generated.types.Show as GraphQLShow

fun Show.adapt(): GraphQLShow {
    return GraphQLShow(
        id = id.string(),
        version = version.int(),
        title = title.string(),
        releaseYear = releaseYear.int()
    )
}
