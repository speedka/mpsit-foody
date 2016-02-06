define({ "api": [
  {
    "type": "GET",
    "url": "/get_offers",
    "title": "get a list with food item.",
    "name": "GetOffers",
    "description": "<p>it retrieves all the food offers from the database filtered by the subcategory and category id</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "category_id",
            "description": "<p>food main category.</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "subcategory_id",
            "description": "<p>subcategory from the main category.</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "https://mpsit-foody-catalincalotescu-1.c9users.io/get_offers?category_id=1&subcategory_id=4"
      }
    ],
    "version": "0.0.0",
    "filename": "node_server/foody.js",
    "group": "_home_ubuntu_workspace_node_server_foody_js",
    "groupTitle": "_home_ubuntu_workspace_node_server_foody_js"
  },
  {
    "type": "GET",
    "url": "/get_subcategory",
    "title": "get a list with all subcategeries for a given main food category.",
    "name": "GetSubcategory",
    "description": "<p>get the subcategories of the food from the databases along with their range of price</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "id",
            "description": "<p>food main category.</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "https://mpsit-foody-catalincalotescu-1.c9users.io/get_subcategory?id=3"
      }
    ],
    "version": "0.0.0",
    "filename": "node_server/foody.js",
    "group": "_home_ubuntu_workspace_node_server_foody_js",
    "groupTitle": "_home_ubuntu_workspace_node_server_foody_js"
  },
  {
    "type": "GET",
    "url": "/admin",
    "title": "renders the webpage",
    "name": "Getadmin",
    "description": "<p>it renders the main page with 4 buttons (each one for one main food category), and each button has an ajax call to retrieve the subcategories</p>",
    "sampleRequest": [
      {
        "url": "https://mpsit-foody-catalincalotescu-1.c9users.io/admin"
      }
    ],
    "version": "0.0.0",
    "filename": "node_server/foody.js",
    "group": "_home_ubuntu_workspace_node_server_foody_js",
    "groupTitle": "_home_ubuntu_workspace_node_server_foody_js"
  }
] });
