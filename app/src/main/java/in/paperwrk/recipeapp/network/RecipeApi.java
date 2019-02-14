package in.paperwrk.recipeapp.network;

import in.paperwrk.recipeapp.network.responses.RecipeResponse;
import in.paperwrk.recipeapp.network.responses.RecipeSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApi {

    // Search Recipe Request
    @GET("api/search")
    Call<RecipeSearchResponse> searchRecipes(
            @Query("key") String key,
            @Query("q") String query,
            @Query("page") String page
    );

    // Get Recipe Request
    @GET("api/get")
    Call<RecipeResponse> getRecipe(
            @Query("key") String key,
            @Query("rId") String recipe_id
    );

}
