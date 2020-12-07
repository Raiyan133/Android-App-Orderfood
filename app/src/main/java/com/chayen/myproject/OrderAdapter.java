package com.chayen.myproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Food> {
    private List<Food> list;
    private Context context;

    TextView currentFoodName,
            currentCost,
            quantityText,
            addMeal,
            subtractMeal,
            removeMeal;

    public OrderAdapter(Context context, List<Food> myOrders) {
        super(context, 0, myOrders);
        this.list = myOrders;
        this.context = context;
    }


    public View getView(final int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.
                    from(getContext()).inflate(
                    R.layout.
                            column_order_food,parent,false
            );
        }

        final Food currentFood = getItem(position);

        currentFoodName = (TextView)listItemView.findViewById(R.id.tvNameOrder);
        currentCost = (TextView)listItemView.findViewById(R.id.tvPriceOrder);
        subtractMeal = (TextView)listItemView.findViewById(R.id.minus_meal);
        quantityText = (TextView)listItemView.findViewById(R.id.tvAmount);
        addMeal = (TextView)listItemView.findViewById(R.id.plus_meal);
        removeMeal = (TextView)listItemView.findViewById(R.id.delete_item);

        //Set the text of the meal, amount and quantity
        currentFoodName.setText(currentFood.getmName());
        currentCost.setText(""+ currentFood.getmAmount());
        quantityText.setText(""+ currentFood.getmQuantity());

        //OnClick listeners for all the buttons on the ListView Item
        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentFood.addToQuantity();
                quantityText.setText(""+ currentFood.getmQuantity());
                currentCost.setText(""+ (currentFood.getmAmount() * currentFood.getmQuantity()));
                notifyDataSetChanged();
            }
        });

        subtractMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentFood.removeFromQuantity();
                quantityText.setText(""+currentFood.getmQuantity());
                currentCost.setText(""+ (currentFood.getmAmount() * currentFood.getmQuantity()));
                notifyDataSetChanged();
            }
        });

        removeMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        return listItemView;
    }

}