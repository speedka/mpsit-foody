package com.personal.foody.web.models;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

/**
 * Created by danielastamati on 30/01/16.
 *
 * Class to hold restaurant offer information. Such as:
 * <ul>
 * <li>subcategory ID</li>
 * <li>subcategory name (ex: pizza)</li>
 * <li>minimum price in EUR</li>
 * <li>minimum price in RON</li>
 * <li>maximum price in RON</li>
 * <li>maximum price in EUR</li>
 * </ul>
 *
 * <p>
 * <b>WARNING:</b> do not modify the SerializedNames in this class since they correspond to the JSON
 * objects received from the backend. An 1 to 1 correspondence ensures that
 * {@link com.google.gson.Gson#fromJson(JsonElement, Class)} will work properly when decapsulating JSON data
 * </>
 */

public class SubCategory {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("minPriceEuro")
    public String minPriceEuro;
    @SerializedName("maxPriceEuro")
    public String maxPriceEuro;
    @SerializedName("minPriceRon")
    public String minPriceRon;
    @SerializedName("maxPriceRon")
    public String maxPriceRon;

    public SubCategory(int id, String name, String minPriceEuro, String maxPriceEuro,
                       String minPriceRon, String maxPriceRon){
        this.id = id;
        this.name = name;
        this.minPriceEuro = minPriceEuro;
        this.maxPriceEuro = maxPriceEuro;
        this.minPriceRon = minPriceRon;
        this.maxPriceRon = maxPriceRon;
    }
}
