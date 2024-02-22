package pe.belcorp.creditcard.orq.Util;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;

public class Security {
    public static String channelToken;

    public static String createAccessToken(String basic) {
        try {
            return channelToken ="Bearer " + SerenityRest.given().header("Authorization", basic)
                            .contentType(ContentType.URLENC)
                            .formParam("grant_type", "client_credentials")
                            .post("https://api-qa.belcorp.biz/oauth/token")
                            .then()
                            .extract().response().jsonPath().getString("access_token");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
     /*   Response response = SerenityRest.given().header("Authorization", basic)
                .contentType(ContentType.URLENC)
                .formParam("grant_type", "client_credentials")
                .post("https://api-qa.belcorp.biz/oauth/token");

    } */
}