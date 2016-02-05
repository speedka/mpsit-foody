package com.personal.foody.adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.personal.foody.R;
import com.personal.foody.activities.SubcategoryListActivity;
import com.personal.foody.web.models.OfferItem;

import java.util.List;

/**
 * Created by danielastamati on 31/01/16.
 *
 * Adapter used for displaying a list of offers containing the following data:
 *
 * <ul>
 *
 * <li>restaurant name</li>
 * <li>dish name</li>
 * <li>description</li>
 * <li>price in RON</li>
 * <li>price in EUR</li>
 * <li>button with the action to call the restaurant</li>
 * <li>button to access restaurat's web page</li>
 * <li>button to locate the restaurant on the map</li>
 * </ul>
 *
 *
 */
public class OfferListAdapter extends ArrayAdapter {

    private List<OfferItem> mOffersList;
    private Activity mContext;
    private LayoutInflater mLayoutInflator;

    private int currency = SubcategoryListActivity.RON;

    public OfferListAdapter(Activity context, List<OfferItem> list) {
        super(context, 0);
        mOffersList = list;

        mContext = context;
        mLayoutInflator = context.getLayoutInflater();
    }

    /**
     * @param currency
     * Converts the food prices in the selected currency using
     * the currency value can be either {@link SubcategoryListActivity#RON} or {@link SubcategoryListActivity#EURO}
     */
    public void setCurrency(int currency) {
        this.currency = currency;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mOffersList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflator.inflate(R.layout.item_offer, null);
        }
        OfferItem item = mOffersList.get(position);
        ((TextView) convertView.findViewById(R.id.tv_restaurant_name)).setText(item.restaurantName);
        ((TextView) convertView.findViewById(R.id.tv_dish_name)).setText(item.foodname);
        ((TextView) convertView.findViewById(R.id.tv_desc)).setText(item.description);

        String price = "N/A";
        if (priceDataIsValid(item)) {
            if (currency == SubcategoryListActivity.RON) {
                price = item.priceRon + " " + "RON";
            } else {
                price = item.priceEuro + " " + "EURO";
            }

        }
        ((TextView) convertView.findViewById(R.id.tv_price)).setText(price);

        assignClickListeners(convertView, item);

        return convertView;
    }

    /**
     *
     * @param convertView
     * @param item
     *
     * Assigns the click listeners on the buttons with actions as following:
     * <ul>
     *     <li>The call button triggers a call intent</li>
     *     <li>The locate button triggers a locate intent received by the installed "Maps" apps</li>
     *     <li>The web button opens the restaurat's web page in a browser window</li>
     *
     * </ul>
     */
    private void assignClickListeners(View convertView, final OfferItem item) {
        convertView.findViewById(R.id.iv_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+item.phone));
                mContext.startActivity(intent);
            }
        });


        convertView.findViewById(R.id.iv_locate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q="+item.restaurantName));
                mContext.startActivity(intent);
            }
        });

        convertView.findViewById(R.id.iv_web_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.webPage));
                mContext.startActivity(intent);
            }
        });
    }

    private boolean priceDataIsValid(OfferItem item) {

        if (currency == SubcategoryListActivity.RON) {
            return !TextUtils.isEmpty(item.priceRon) && !item.priceRon.equalsIgnoreCase("null");
        } else {
            return !TextUtils.isEmpty(item.priceEuro) && !item.priceEuro.equalsIgnoreCase("null");
        }

    }
}
