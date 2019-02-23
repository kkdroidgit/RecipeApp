package in.paperwrk.recipeapp.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import in.paperwrk.recipeapp.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    OnRecipeListener listener;
    TextView categoryTitle;
    CircleImageView circleImageView;

    public CategoryViewHolder(@NonNull View itemView, OnRecipeListener listener) {
        super(itemView);
        this.listener = listener;
        categoryTitle = itemView.findViewById(R.id.category_title);
        circleImageView = itemView.findViewById(R.id.category_image);

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        listener.onCategoryClick(categoryTitle.getText().toString());
    }
}
