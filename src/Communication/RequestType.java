package Communication;

import java.io.Serializable;

public enum RequestType implements Serializable {

    CHECK_USER,
    USER_LOGIN,
    USER_SIGNUP,
    USER_PROFILE,
    USER_NAME,
    USER_LASTNAME,
    USER_NUMBER,
    USER_NEW_PASS,

    NEW_AD,
    ADD_AD_TO_FAVORITES,
    GET_FAVORITE_ADS,
    REMOVE_AD_FROM_FAVORITES,

    GET_ALL_ADS;
}
