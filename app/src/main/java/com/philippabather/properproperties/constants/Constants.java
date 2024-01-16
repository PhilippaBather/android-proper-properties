package com.philippabather.properproperties.constants;

/**
 * Constants - constantes para Strings y carácteres.
 *
 * @author Philippa Bather
 */
public class Constants {

    public static final char tick = '\u2713';
    public static final char cross = '\u274C';
    public static final char euro = '\u20AC';

    // bundle arguments
    public static final String BUNDLE_ARGUMENT_PARCELABLE_LIST_RENTALS = "rentals";
    public static final String BUNDLE_ARGUMENT_PARCELABLE_LIST_SALES = "sales";
    public static final String BUNDLE_ARGUMENT_RENTAL = "rental";
    public static final String BUNDLE_ARGUMENT_SALE = "sale";
    public static final String BUNDLE_ARGUMENT_RENTAL_ID = "rentalId";
    public static final String BUNDLE_ARGUMENT_SALE_ID = "saleId";

    // intent extras
    public static final String INTENT_EXTRA_PROPERTY = "property";
    public static final String INTENT_EXTRA_PROPRIETOR_ID = "proprietorId";
    public static final String INTENT_EXTRA_PROPERTY_STATUS = "propertyStatus";
    public static final String INTENT_EXTRA_RENTAL_ID = "rentalPropertyId";
    public static final String INTENT_EXTRA_SALE_ID = "salePropertyId";


    // PropertyType Strings: inglés y español
    public static final String PROPERTY_TYPE_COMMERICAL_EN = "COMMERCIAL";
    public static final String PROPERTY_TYPE_COMMERICAL_ES = "LOCAL";
    public static final String PROPERTY_TYPE_FLAT_EN = "FLAT";
    public static final String PROPERTY_TYPE_FLAT_ES = "PISO";
    public static final String PROPERTY_TYPE_HOUSE_EN = "HOUSE";
    public static final String PROPERTY_TYPE_HOUSE_ES = "CASA";

    // información para el usuario
    public static final String LOGIN_ERROR = "ERROR LOGGING IN";
    public static final String SERVER_ERROR = "Se ha producido un error al connectar con el servidor.";

    // log
    public static final String LOG_CREDENTIALS_INVALID = "Credentials invalid";
    public static final String LOG_PROPERTY_STATUS_ERROR = "Property Status not recognised.";
    public static final String LOG_TAG_FRAGMENT_ERROR = "fragmentError";
}
