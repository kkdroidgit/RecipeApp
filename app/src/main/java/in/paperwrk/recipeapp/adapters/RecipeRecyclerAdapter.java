package in.paperwrk.recipeapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import in.paperwrk.recipeapp.R;
import in.paperwrk.recipeapp.model.Recipe;
import in.paperwrk.recipeapp.utils.Constants;

import static in.paperwrk.recipeapp.utils.Constants.RECIPE_CATEGORIES;

public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int RECIPE_TYPE = 1;
    private static final int LOADING_TYPE = 2;
    private static final int CATEGORY_TYPE = 3;

    private List<Recipe> mRecipes;
    private OnRecipeListener mOnRecipeListener;

    public RecipeRecyclerAdapter(OnRecipeListener mOnRecipeListener) {
        this.mOnRecipeListener = mOnRecipeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;

        switch (i){
            case RECIPE_TYPE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recipe_list_tiem,
                        viewGroup, false);
                return new RecipeViewHolder(view, mOnRecipeListener);
            case LOADING_TYPE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_loading_list_item,
                        viewGroup, false);
                return new LoadingViewHolder(view);
            case CATEGORY_TYPE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_category_list_item,
                        viewGroup,false);
                return new CategoryViewHolder(view, mOnRecipeListener);
            default:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recipe_list_tiem,
                        viewGroup, false);
                return new RecipeViewHolder(view, mOnRecipeListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);

        if (itemViewType == RECIPE_TYPE){

            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(viewHolder.itemView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(mRecipes.get(i).getImage_url())
                    .into(((RecipeViewHolder)viewHolder).imageView);

            ((RecipeViewHolder)viewHolder).title.setText(mRecipes.get(i).getTitle());

            ((RecipeViewHolder)viewHolder).publisher.setText(mRecipes.get(i).getPublisher());
            ((RecipeViewHolder)viewHolder).socialScore
                    .setText(String.valueOf(Math.round(mRecipes.get(i).getSocial_rank())));

        }
        else if(itemViewType == CATEGORY_TYPE){

            ((CategoryViewHolder)viewHolder).categoryTitle.setText(mRecipes.get(i).getTitle());
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(mRecipes.get(position).getSocial_rank() == -1){
            return CATEGORY_TYPE;
        }
        else if (mRecipes.get(position).getTitle().equals("LOADING...")){
            return LOADING_TYPE;
        }
        else {
            return RECIPE_TYPE;
        }
    }

    public void displayLoading(){
        if (!isLoading()){
            Recipe recipe = new Recipe();
            recipe.setTitle("LOADING...");
            List<Recipe> loadingList = new ArrayList<>();
            loadingList.add(recipe);
            mRecipes = loadingList;
            notifyDataSetChanged();
        }
    }

    private boolean isLoading(){
        if (mRecipes != null){
            if (mRecipes.size() > 0){
                return mRecipes.get(mRecipes.size() - 1).getTitle().equals("LOADING...");
            }
        }
        return false;
    }

    public void displaySearchCategories(){
        List<Recipe> categories = new ArrayList<>();
        for(int i = 0; i< RECIPE_CATEGORIES.length; i++){
            Recipe recipe = new Recipe();
            recipe.setTitle(Constants.RECIPE_CATEGORIES[i]);
            recipe.setSocial_rank(-1);
            categories.add(recipe);
        }
        mRecipes = categories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (mRecipes != null){
            return mRecipes.size();
        }
        return 0;
    }

    public void setRecipes(List<Recipe> recipes){
        mRecipes = recipes;
        notifyDataSetChanged();
    }
}