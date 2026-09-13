import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellnessapp.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: VideoRepository
) : ViewModel() {

    private val _selectedFilter = MutableStateFlow("All")
    val selectedFilter = _selectedFilter.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val videos = _selectedFilter.flatMapLatest { filter ->
        repository.getVideos().map { videoList ->
            if (filter == "All") videoList
            else videoList.filter { it.category == filter }
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun updateFilter(filter: String) {
        _selectedFilter.value = filter
    }
}