package in.paperwrk.recipeapp.viewmodels;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import in.paperwrk.recipeapp.model.Recipe;
import in.paperwrk.recipeapp.repositories.RecipeRepository;

public class RecipeListViewModel extends ViewModel {

    private RecipeRepository mRecipeRepository;
    private boolean mIsQueryingRecipes;
    private Boolean mIsViewingRecipes;

    public RecipeListViewModel() {
        mIsViewingRecipes = false;
        mRecipeRepository = RecipeRepository.getInstance();
        mIsQueryingRecipes = false;
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipeRepository.getRecipes();
    }

    public void searchRecipesApi(String query, int pageNumber){
        mIsViewingRecipes = true;
        mRecipeRepository.searchRecipesApi(query, pageNumber);
        mIsQueryingRecipes = true;
    }

    public Boolean IsViewingRecipes() {
        return mIsViewingRecipes;
    }

    public void setIsViewingRecipes(Boolean isViewingRecipes) {
        this.mIsViewingRecipes = isViewingRecipes;
    }

    public boolean isQueryingRecipes() {
        return mIsQueryingRecipes;
    }

    public void setIsQueryingRecipes(boolean mIsQueryingRecipes) {
        this.mIsQueryingRecipes = mIsQueryingRecipes;
    }

    public boolean onBackPressed(){
        if (mIsQueryingRecipes){
            // cancel retrofit query
            mRecipeRepository.cancelRequest();
            mIsQueryingRecipes = false;
        }
        if (mIsViewingRecipes){
            mIsViewingRecipes = false;
            return false;
        }
        return true;
    }

}
