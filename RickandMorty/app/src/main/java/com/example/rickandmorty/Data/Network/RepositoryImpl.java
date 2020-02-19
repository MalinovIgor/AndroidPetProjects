//package com.example.rickandmorty.Data.Network;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//
//import com.example.rickandmorty.Data.Network.Character.CharacterResult;
//import com.example.rickandmorty.Data.Network.Character.TheCharacter;
//
//import java.util.Objects;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.internal.EverythingIsNonNull;
//
//
////don't use
//
//public class RepositoryImpl implements Repository {
//
//    public static final String BASE_URL = "https://rickandmortyapi.com/api/";
//    private RickAndMortyApiService mApiService = ApiClient.getInstance().getApi();
//
//    @Override
//    public LiveData<ApiResponse<TheCharacter>> getCharacters(int page) {
//        final MutableLiveData<ApiResponse<TheCharacter>> liveData = new MutableLiveData<>();
//        Call<CharacterResult> call = mApiService.getCharacters( "characters", page);
//        call.enqueue(new Callback<CharacterResult>() {
//            @Override
//            @EverythingIsNonNull
//            public void onResponse(Call<CharacterResult> call, Response<CharacterResult> response) {
//                liveData.setValue(new ApiResponse<>(Objects.requireNonNull(response).body().getResults()));
//            }
//            @Override
//            @EverythingIsNonNull
//            public void onFailure(Call<CharacterResult> call, Throwable t) {
//                liveData.setValue(new ApiResponse<>(t));
//            }
//        });
//        return liveData;
//    }
//
////    @Override
////    public LiveData<ApiResponse<Location>> getLocations() {
////        final MutableLiveData<ApiResponse<Location>> liveData = new MutableLiveData<>();
////        Call<List<Location>> call = mApiService.getLocations();
////        call.enqueue(new Callback<List<Location>>() {
////            @Override
////            @EverythingIsNonNull
////            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
////                liveData.setValue(new ApiResponse<>(Objects.requireNonNull(response).body()));
////            }
////            @Override
////            @EverythingIsNonNull
////            public void onFailure(Call<List<Location>> call, Throwable t) {
////                liveData.setValue(new ApiResponse<Location>(t));
////            }
////        });
////        return liveData;
////    }
////
////    @Override
////    public LiveData<ApiResponse<Episode>> getLocations() {
////        final MutableLiveData<ApiResponse<Episode>> liveData = new MutableLiveData<>();
////        Call<List<Episode>> call = mApiService.getLocations();
////        call.enqueue(new Callback<List<Episode>>() {
////            @Override
////            @EverythingIsNonNull
////            public void onResponse(Call<List<Episode>> call, Response<List<Episode>> response) {
////                liveData.setValue(new ApiResponse<>(Objects.requireNonNull(response).body()));
////            }
////            @Override
////            @EverythingIsNonNull
////            public void onFailure(Call<List<Episode>> call, Throwable t) {
////                liveData.setValue(new ApiResponse<Episode>(t));
////            }
////        });
////        return liveData;
////    }
////
////    @Override
////    public LiveData<ApiResponse<TheCharacter>> getCharacter(int id) {
////        final MutableLiveData<ApiResponse<TheCharacter>> liveData = new MutableLiveData<>();
////        Call<TheCharacter> call = mApiService.getCharacter(id);
////        call.enqueue(new Callback<TheCharacter>() {
////            @Override
////            @EverythingIsNonNull
////            public void onResponse(Call<TheCharacter> call, Response<TheCharacter> response) {
////                liveData.setValue(new ApiResponse<>(Objects.requireNonNull(response).body()));
////            }
////            @Override
////            @EverythingIsNonNull
////            public void onFailure(Call<TheCharacter> call, Throwable t) {
////                liveData.setValue(new ApiResponse<TheCharacter>(t));
////            }
////        });
////        return liveData;
////    }
////
////    @Override
////    public LiveData<ApiResponse<Location>> getLocation(int id) {
////        final MutableLiveData<ApiResponse<Location>> liveData = new MutableLiveData<>();
////        Call<Location> call = mApiService.getLocation(id);
////        call.enqueue(new Callback<Location>() {
////            @Override
////            @EverythingIsNonNull
////            public void onResponse(Call<Location> call, Response<Location> response) {
////                liveData.setValue(new ApiResponse<>(Objects.requireNonNull(response).body()));
////            }
////            @Override
////            @EverythingIsNonNull
////            public void onFailure(Call<Location> call, Throwable t) {
////                liveData.setValue(new ApiResponse<Location>(t));
////            }
////        });
////        return liveData;
////    }
////
////    @Override
////    public LiveData<ApiResponse<Episode>> getLocations(int id) {
////        final MutableLiveData<ApiResponse<Episode>> liveData = new MutableLiveData<>();
////        Call<Episode> call = mApiService.getLocations(id);
////        call.enqueue(new Callback<Episode>() {
////            @Override
////            @EverythingIsNonNull
////            public void onResponse(Call<Episode> call, Response<Episode> response) {
////                liveData.setValue(new ApiResponse<>(Objects.requireNonNull(response).body()));
////            }
////            @Override
////            @EverythingIsNonNull
////            public void onFailure(Call<Episode> call, Throwable t) {
////                liveData.setValue(new ApiResponse<Episode>( t));
////            }
////        });
////        return liveData;
////    }
//}
