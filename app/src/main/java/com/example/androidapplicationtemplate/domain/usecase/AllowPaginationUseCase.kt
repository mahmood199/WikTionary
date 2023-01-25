package com.example.androidapplicationtemplate.domain.usecase

import com.example.androidapplicationtemplate.core.util.Resource
import com.example.androidapplicationtemplate.data.models.entity.PagingEntity
import javax.inject.Inject

class AllowPaginationUseCase @Inject constructor() {
    operator fun invoke(
        pagingEntity: PagingEntity
    ): Resource<Boolean> {
        return Resource.Success(
            pagingEntity.lastItemPosition == pagingEntity.rvItemCount - 1 &&
                pagingEntity.rvItemCount < pagingEntity.tCount && pagingEntity.dataRequested.not()
        )
    }
}