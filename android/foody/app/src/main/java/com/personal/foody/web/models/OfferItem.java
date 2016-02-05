package com.personal.foody.web.models;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

/**
 * Created by danielastamati on 30/01/16.
 * Class to hold restaurant offer information. Such as:
 * <ul>
 * <li>dish name</li>
 * <li>description</li>
 * <li>restaurant name</li>
 * <li>price in RON</li>
 * <li>price in EUR</li>
 * <li>phone number of the restaurant</li>
 * <li>restaurant's web page (if any)</li>
 * </ul>
 *
 * <p>
 * <b>WARNING:</b> do not modify the SerializedNames in this class since they correspond to the JSON
 * objects received from the backend. An 1 to 1 correspondence ensures that
 * {@link com.google.gson.Gson#fromJson(JsonElement, Class)} will work properly when decapsulating JSON data
 * </>
 */
public class OfferItem {

    @SerializedName("name")
    public String foodname;
    @SerializedName("description")
    public String description;
    @SerializedName("restaurantName")
    public String restaurantName;
    @SerializedName("priceRon")
    public String priceRon;
    @SerializedName("priceEuro")
    public String priceEuro;
    @SerializedName("phone")
    public String phone;
    @SerializedName("webPage")
    public String webPage;

}
