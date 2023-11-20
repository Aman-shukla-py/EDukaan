package com.example.edukaan.utils;

public class Constants {

    public static String API_BASE_URL = "https://ecommobile2023.000webhostapp.com";
    public static String GET_PRODUCTS_URL = API_BASE_URL + "/services/listProduct";
    public static String GET_CATEGORIES_URL = API_BASE_URL + "/services/listCategory";
    public static String GET_PRODUCT_DETAILS_URL = API_BASE_URL + "/services/getProductDetails?id=";
    public static String POST_ORDER_URL = API_BASE_URL + "/services/submitProductOrder";

    public static String NEWS_IMAGE_URL = API_BASE_URL + "/uploads/news/";

    public static String GET_OFFERS_URL = API_BASE_URL + "/services/listFeaturedNews";
    public static String PAYMENT_URL = API_BASE_URL + "/services/paymentPage?code=";
    public static String CATEGORIES_IMAGE_URL = API_BASE_URL + "/uploads/category/";
    public static String PRODUCTS_IMAGE_URL = API_BASE_URL + "/uploads/product/";
    public static String ORDER_HISTORY_URL = API_BASE_URL + "/services/getAllProductOrderByPage?page=1&limit=1000&q=";

}
