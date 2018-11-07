package source.kevtimov.landlordcommunicationapp.utils;

public class Constants {
    public static final String BASE_SERVER_URL_KRIS = "http://192.168.100.4:9090/api";

    public static final int SELECT_TENANT_REQUEST = 2005;

    public static final int ADD_PLACE_REQUEST = 20015;
    public static final int SELECT_PLACE_REQUEST = 200;
    public static final int REQUEST_CODE_GALLERY = 878;
    public static final String SHARED_PREFERENCES_KEY_USER_INFO = "UserInfo";
    public static final String CHANNEL_ID = "Rent";
    public static final int USER_MAXIMUM_PASS_SALT = 255;
    public static final int USER_MAXIMUM_PASS_HASH = 255;
    public static final int MINIMUM_EMAIL_LENGTH = 9;
    public static final int MAXIMUM_EMAIL_LENGTH = 45;
    public static final int USER_LAST_NAME_MINIMUM_LENGTH = 3;
    public static final int USER_LAST_NAME_MAXIMUM_LENGTH = 45;
    public static final int USER_FIRST_NAME_MINIMUM_LENGTH = 3;
    public static final int USER_FIRST_NAME_MAXIMUM_LENGTH = 45;
    public static final int USER_USERNAME_MINIMUM_LENGTH = 6;
    public static final int USER_USERNAME_MAXIMUM_LENGTH = 45;
    public static final int PLACE_ADDRESS_MINIMUM_LENGTH = 5;
    public static final int PLACE_ADDRESS_MAXIMUM_LENGTH = 55;
    public static final int PLACE_DESCRIPTION_MINIMUM_LENGTH = 10;
    public static final int PLACE_DESCRIPTION_MAXIMUM_LENGTH = 16777215;
    public static final int PLACE_TENANTID_MINIMUM_LENGTH = 1;
    public static final int PLACE_LANDLORDID_MINIMUM_LENGTH = 1;
    public static final int RENT_PLACEID_MINIMUM_LENGTH = 1;
    public static final double RENT_MINIMUM_TOTAL_AMOUNT = 100;
    public static final double RENT_MAXIMUM_TOTAL_AMOUNT = 99999.99;

    public static final double RENT_MINIMUM_REM_AMOUNT = 0.01;
    public static final double RENT_MAXIMUM_REM_AMOUNT = 99999.99;
    public static final int RENT_DUE_DATE_MINIMUM_LENGTH = 10;
    public static final int RENT_DUE_DATE_MAXIMUM_LENGTH = 10;
    public static final int CARD_USERID_MINIMUM_LENGTH = 1;
    public static final int CARD_BRAND_MINIMUM_LENGTH = 4;
    public static final int CARD_BRAND_MAXIMUM_LENGTH = 45;
    public static final int CARD_TYPE_MINIMUM_LENGTH = 5;
    public static final int CARD_TYPE_MAXIMUM_LENGTH = 45;

    public static final double CARD_BALANCE_MINIMUM_LENGTH = 1;
    public static final double CARD_BALANCE_MAXIMUM_LENGTH = 9999999.99;
    public static final int CARD_NUMBER_MINIMUM_LENGTH = 16;
    public static final int CARD_NUMBER_MAXIMUM_LENGTH = 16;
    public static final int CARD_CVV_MINIMUM_LENGTH = 3;
    public static final int CARD_CVV_MAXIMUM_LENGTH = 3;
    public static final int PAYMENT_USERID_MINIMUM_LENGTH = 1;
    public static final int PAYMENT_RENTID_MINIMUM_LENGTH = 1;
    public static final int PAYMENT_CARDID_MINIMUM_LENGTH = 1;
    public static final int PAYMENT_PLACEID_MINIMUM_LENGTH = 1;
    public static final double PAYMENT_AMOUNT_MINIMUM_LENGTH = 0;
    public static final double PAYMENT_AMOUNT_MAXIMUM_LENGTH = 99999.99;
    public static final int PAYMENT_DATE_MINIMUM_LENGTH = 10;
    public static final int PAYMENT_DATE_MAXIMUM_LENGTH = 10;
    public static final int RATING_VOTEFORID_MINIMUM_LENGTH = 1;
    public static final int RATING_VOTEFROMID_MINIMUM_LENGTH = 1;
    public static final double RATING_AMOUNT_MINIMUM_LENGTH = 0;
    public static final double RATING_AMOUNT_MAXIMUM_LENGTH = 5;
}
