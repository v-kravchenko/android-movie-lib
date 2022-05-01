package com.redcatgames.movies.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.domain.repository.MovieRepository
import com.redcatgames.movies.domain.util.UseCaseResult
import retrofit2.HttpException
import javax.inject.Inject

class PopularMoviesPagingSource @Inject constructor(
    private val movieRepository: MovieRepository
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER

            return when(val response = movieRepository.loadPopularMovies(pageNumber)) {
                is UseCaseResult.Success -> {
                    val movies = response.value
                    val nextPageNumber = if (movies.isEmpty()) null else pageNumber + 1
                    val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
                    LoadResult.Page(movies, prevPageNumber, nextPageNumber)
                }
                is UseCaseResult.Failure -> {
                    LoadResult.Error(Exception(response.errorMessage))
                }
            }

        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

//    @AssistedFactory
//    interface Factory {
//
//        fun create(@Assisted("query") query: String): PopularMoviesPagingSource
//    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
        const val PAGE_SIZE = 20
    }
}