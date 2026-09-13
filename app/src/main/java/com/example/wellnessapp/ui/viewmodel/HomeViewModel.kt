import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellnessapp.data.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: VideoRepository
) : ViewModel() {

    private val _selectedFilter = MutableStateFlow("All")
    val selectedFilter = _selectedFilter.asStateFlow()

    // Filter change hole video list update hobe
    val videos = _selectedFilter.map { filter ->
        if (filter == "All") repository.getAllVideos()
        else repository.getVideosByCategory(filter)
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun updateFilter(filter: String) {
        _selectedFilter.value = filter
    }
}