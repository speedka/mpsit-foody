package com.personal.foody.adapters;

import android.app.Activity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.personal.foody.R;
import com.personal.foody.activities.SubcategoryListActivity;
import com.personal.foody.web.models.SubCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielastamati on 29/01/16.
 * <p/>
 * Adapter class for rendering Subcategory list item. Implements {@link android.support.v7.widget.SearchView.OnQueryTextListener}
 */
public class SubcategoryListAdapter extends ArrayAdapter implements Filterable, SearchView.OnQueryTextListener {


    private LayoutInflater mLayoutInflater;

    private List<SubCategory> mCompleteList;
    private ArrayList<SubCategory> mMatchList;

    private int currency = SubcategoryListActivity.RON;

    public SubcategoryListAdapter(Activity context, List<SubCategory> list) {
        super(context, 0);

        mCompleteList = list;
        mMatchList = new ArrayList<>();
        mLayoutInflater = context.getLayoutInflater();
    }

    public void setCurrency(int currency) {
        this.currency = currency;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mMatchList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_dish_list, null);
        }

        SubCategory item = mMatchList.get(position);

        String price = "N/A";

        if (priceDataIsValid(currency, item)) {
            if (currency == SubcategoryListActivity.RON) {
                price = item.minPriceRon + " - " + item.maxPriceRon + " RON";
            } else {
                price = item.minPriceEuro + " - " + item.maxPriceEuro + " EURO";
            }
        }


        ((TextView) convertView.findViewById(R.id.tv_price)).setText(price);
        ((TextView) convertView.findViewById(R.id.tv_title)).setText(item.name);


        return convertView;
    }

    private boolean priceDataIsValid(int currency, SubCategory item) {

        if (currency == SubcategoryListActivity.RON) {
            return !TextUtils.isEmpty(item.maxPriceRon) && !item.maxPriceRon.equalsIgnoreCase("null")
                    && !TextUtils.isEmpty(item.minPriceRon) && !item.minPriceRon.equalsIgnoreCase("null");

        } else {

            return !TextUtils.isEmpty(item.maxPriceEuro) && !item.maxPriceEuro.equalsIgnoreCase("null")
                    && !TextUtils.isEmpty(item.minPriceEuro) && !item.minPriceEuro.equalsIgnoreCase("null");
        }

    }

    @Override
    /**
     * Filters the list items that match the query.
     * Gets called when the query changes in the search toolbar
     */
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                results.count = 0;
                mMatchList.clear();
                if (!TextUtils.isEmpty(constraint)) {
                    for (SubCategory food : mCompleteList) {
                        if (food.name.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            mMatchList.add(food);
                        }
                    }
                } else {
                    mMatchList.addAll(mCompleteList);
                }

                results.count = mMatchList.size();
                results.values = mMatchList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null) {
                    notifyDataSetChanged();
                }
            }
        };
        return filter;
    }

    @Override
    /**
     * Registers a text submitted event and triggers {@link #getFilter()}
     */
    public boolean onQueryTextSubmit(String query) {
        getFilter().filter(query);
        return false;
    }

    @Override
    /**
     * Registers a text changed event and triggers {@link #getFilter()}
     */
    public boolean onQueryTextChange(String newText) {
        getFilter().filter(newText);
        return false;
    }
}
