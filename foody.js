"use strict";

var express = require("express");
var mysql = require('mysql');
var request = require('request');

var util = require("util");
var expressValidator = require('express-validator');

var mysql_config = {
  host     : 'localhost',
  user     : 'foody',
  password : '1234',
  database : 'foody'
};
var currency = 'RON';
var app = express();
app.use(express.bodyParser());
app.use(expressValidator()); // this line must be immediately after express.bodyParser()!

app.set('views', './html');
app.set('view engine', 'ejs');
app.engine('html', require('ejs').renderFile);

/**
* generic function used for querying the database
* it expects a valid mysql query and returns the data generated by the query
*/
function getQueryResult(queryString, callback){
    var connection = mysql.createConnection(mysql_config);
    
    connection.connect();

    connection.query(queryString, function(err, data, fields) {
        if (err) {
            callback(err, data);
            return -1;
        }
        callback(err, data);
    });
       
    connection.end();
}

/**
* creates the response in a dictionary format which can be easily converted into a json
* used in get subcategories call
* it expects an array with the product subcategories as input as well as the exchange rate
*/
function createGetSubcategoryMap(data, rate){
    var data_map = [];
    for (var row in data){
        var min_euro = null,
            max_euro = null;
        if(data[row].min_price != null){
            min_euro = (parseFloat(data[row].min_price) / rate).toFixed(2);
            max_euro = (parseFloat(data[row].max_price) / rate).toFixed(2);
        }
        data_map.push({"id" :data[row].subcategory_id, "name": data[row].subcategory_name, "minPriceEuro": min_euro, "maxPriceEuro": max_euro,
                        "minPriceRon": data[row].min_price, "maxPriceRon": data[row].max_price});
    }
    return {"data":data_map};
}


/**
* creates the response in a dictionary format which can be easily converted into a json
* used in get_offers call
* it expects an array with the food items as input as well as the exchange rate
*/
function createItemsMap(data, rate){
    var data_map = [];
    for (var row in data){
        var price_euro = null;
        if(data[row].price_ron != null){
            price_euro = (parseFloat(data[row].price_ron) / rate).toFixed(2);
        }
        data_map.push({"name" :data[row].item_name, "description": data[row].description, "restaurantName": data[row].name, 
        "priceRon": data[row].price_ron, "priceEuro": price_euro, "phone": data[row].phone, "webPage": data[row].weblink});
    }
    return {"data":data_map};
}

/**
* function to retrieve the exchange rate
* it calls an API and gets from the response the needed currency
*/
function callExchangeRateAPI(currency, error, callback){
    // http://api.fixer.io/latest?base=USD -> for changing the base currency
    var rate = null;
   request('http://api.fixer.io/latest', function (error, response, body) {
    if (!error && response.statusCode == 200) {
        // get the rate for RON
         rate = JSON.parse(body)['rates'][currency];
         // console.log(a);
         callback(error, rate);
     }
     else{
         callback(error, rate);
         return -1;
     }
    });
}

/**
* used for log the exact date of the event call
*/
function getDateTime() {

    var date = new Date();
    var hour = date.getHours();
    hour = (hour < 10 ? "0" : "") + hour;
    var min  = date.getMinutes();
    min = (min < 10 ? "0" : "") + min;
    var sec  = date.getSeconds();
    sec = (sec < 10 ? "0" : "") + sec;
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    month = (month < 10 ? "0" : "") + month;
    var day  = date.getDate();
    day = (day < 10 ? "0" : "") + day;
    return year + ":" + month + ":" + day + " " + hour + ":" + min + ":" + sec;
}

/**
 * @api {GET} /get_subcategory get a list with all subcategeries for a given main food category.
 * @apiName GetSubcategory
 * @apiDescription get the subcategories of the food from the databases along with their range of price
 * @apiParam {Number} [id] food main category.
 * @apiSampleRequest https://mpsit-foody-catalincalotescu-1.c9users.io/get_subcategory?id=3
 */
app.get('/get_subcategory', function(req, res, next)
{
    console.log(getDateTime() + " - /get_subcategory");
    var queryString = "SELECT sub.subcategory_id, sub.subcategory_name, menu.min_price, menu.max_price \
                        FROM subcategory sub LEFT JOIN \
                        (SELECT category_id, subcategory_id, min(price_ron) as min_price, max(price_ron) as max_price \
                        	FROM menu_items \
                        	GROUP by category_id, subcategory_id) menu \
                        	ON sub.subcategory_id = menu.subcategory_id \
                        	and sub.category_id = menu.category_id \
                        WHERE sub.category_id = %d  ";
    
    req.checkQuery('id', 'Invalid getparam').isInt();

    var errors = req.validationErrors();
        if (errors) {
        console.log("There have been validation errors")
        res.send(util.inspect(errors), 400);
        return;
    }
    
    var id_parameter = req.query.id;

    queryString = util.format(queryString, id_parameter);

    getQueryResult(queryString, function(err, data) { 
        if (err){
            console.log(err);
            res.send(JSON.stringify("error 500"));
        }
        
        callExchangeRateAPI(currency, data, function(err, rate){
            if (err){
                console.log(err);
            }
            var result = createGetSubcategoryMap(data, rate);
            res.contentType('application/json');
            res.send(JSON.stringify(result));
        });

    });
    
});


/**
 * @api {GET} /get_offers get a list with food item.
 * @apiName GetOffers
 * @apiDescription it retrieves all the food offers from the database filtered by the subcategory and category id
 * @apiParam {Number} [category_id] food main category.
 * @apiParam {Number} [subcategory_id] subcategory from the main category.
 * @apiSampleRequest https://mpsit-foody-catalincalotescu-1.c9users.io/get_offers?category_id=1&subcategory_id=4
 */
app.get('/get_offers', function(req, res, next)
{
    console.log(getDateTime() + " - /get_offers");
    req.checkQuery('category_id', 'Invalid getparam').isInt();
    req.checkQuery('subcategory_id', 'Invalid getparam').isInt();

    var errors = req.validationErrors();
        if (errors) {
        console.log("There have been validation errors")
        res.send(util.inspect(errors), 400);
        return;
    }
    
    var cat_id = req.query.category_id,
        subcat_id = req.query.subcategory_id,
        queryString = "SELECT menu.item_name, menu.price_ron, menu.description, r.name, r.phone, r.weblink \
                        FROM menu_items menu JOIN restaurants r \
	                        ON menu.restaurant_id = r.id \
                        WHERE menu.category_id = %d \
                        	AND menu.subcategory_id = %d;";
        
    queryString = util.format(queryString, cat_id, subcat_id);

    getQueryResult(queryString, function(err, data) { 
        if (err){
            console.log(err);
            res.send(JSON.stringify("error 500"));
        }
        
        callExchangeRateAPI(currency, data, function(err, rate){
            if (err){
                console.log(err);
            }
            var result = createItemsMap(data, rate);
            res.contentType('application/json');
            res.send(JSON.stringify(result)); 
        });

    });
});

/**
 * @api {GET} /admin renders the webpage
 * @apiName Getadmin
 * @apiDescription it renders the main page with 4 buttons (each one for one main food category), 
 * and each button has an ajax call to retrieve the subcategories
 * @apiSampleRequest https://mpsit-foody-catalincalotescu-1.c9users.io/admin
 */
app.get('/admin', function(req, res, next)
{
  res.render('admin.html', {
    title: 'Welcome'
  });
});

/**
* the default route which should match on all the unexpected calls
*/
app.use(function(req, res, next)
{
    var err = {"error": "not found"}
    res.send(JSON.stringify(err));
});

app.listen(process.env.PORT);


