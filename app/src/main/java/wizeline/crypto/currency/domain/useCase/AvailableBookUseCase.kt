package wizeline.crypto.currency.domain.useCase

import kotlinx.coroutines.flow.*
import wizeline.crypto.currency.domain.repositories.CryptoCurrenciesRepository
import wizeline.crypto.currency.data.Result
import wizeline.crypto.currency.domain.model.AvailableBooksModel
import javax.inject.Inject

class AvailableBookUseCase @Inject constructor(
    private val repository: CryptoCurrenciesRepository
) {
    suspend operator fun invoke(search:String): Flow<Result<List<AvailableBooksModel>>> {
         return repository.getAvailableBooks()
    }
}