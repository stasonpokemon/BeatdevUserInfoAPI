package com.beatdev.api.util.swagger;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * OpenApi constants class.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenApiConstants {

    public static final String USER_UUID = "1ee7a912-dc39-46aa-bd7b-561b2e984bb5";

    public static final String USER_UUID_DESCRIPTION = "User's identification number";

    public static final String USER_USERNAME = "username123";

    public static final String USER_USERNAME_DESCRIPTION = "User's username";

    public static final String USER_PASSWORD = "password123";

    public static final String USER_PASSWORD_DESCRIPTION = "User's password";

    public static final String USER_EMAIL = "example_email@gmail.com";

    public static final String USER_EMAIL_DESCRIPTION = "User's email";

    public static final String USER_ONLINE_STATUS = "ONLINE";

    public static final String USER_OFFLINE_STATUS = "OFFLINE";

    public static final String USER_STATUS_DESCRIPTION = "User stats";

    public static final String USER_IMAGE_LINK = "https://avatars.mds.yandex.net/i?id=aa3e10c6aa0fbcd2d0face272c" +
            "a6d10cc6fb5f59-8498011-images-thumbs&n=13";

    public static final String USER_IMAGE_LINK_DESCRIPTION = "Link with user's photo";


    public static final String CONFIG_CONTACT_EMAIL = "stasonpokemon@icloud.com";

    public static final String CONFIG_CONTACT_NAME = "Stanislau Trebnikau";

    public static final String CONFIG_CONTACT_WEBSITE = "https://www.linkedin.com/in/stanislau-trebnikau/";


    public static final String CONFIG_INFO_TITLE = "BeatDevUserInfo RESTFull application for BeatDev";

    public static final String CONFIG_INFO_VERSION = "Version 1.0.0";

    public static final String CONFIG_INFO_DESCRIPTION =
            "This application provides endpoints for working with user information. ATTENTION!!! " +
                    "This application simulate the delay in accessing the server(5 seconds)";

    public static final String CONFIG_INFO_TERMS_OF_SERVICE = "https://github.com/stasonpokemon/BeatdevUserInfoAPI";


}
