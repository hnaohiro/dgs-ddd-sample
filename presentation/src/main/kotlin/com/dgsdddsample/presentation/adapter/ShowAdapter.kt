package com.dgsdddsample.presentation.adapter

import com.dgsdddsample.domain.show.Show as InternalShow
import com.dgsdddsample.presentation.generated.types.Show as ExternalShow

class ShowAdapter {
    fun adapt(internal: InternalShow): ExternalShow {
        return ExternalShow(
            id = internal.id.string(),
            version = internal.version.int(),
            title = internal.title.string(),
            releaseYear = internal.releaseYear.int()
        )
    }
}
