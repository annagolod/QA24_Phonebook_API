package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RegistrationTestsRA {
    String endPoint = "user/registration/usernamepassword";

    @BeforeMethod
    public void preCondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }

    @Test
    public void registrationSuccess(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        AuthRequestDTO auth = AuthRequestDTO.builder()
                .username("tretam" + i + "@gmail.com")
                .password("Phone54321#")
                .build();
        String token = given()
                .body(auth)
                .contentType(ContentType.JSON)
                .when()
                .post(endPoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("token");
    }

    @Test
    public void registrationWrongEmail(){

        AuthRequestDTO auth = AuthRequestDTO.builder()
                .username("tretamgmail.com")
                .password("Phone54321#")
                .build();
        given()
                .body(auth)
                .contentType(ContentType.JSON)
                .when()
                .post(endPoint)
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("message.username", containsString("must be a well-formed email address"));

    }

    @Test
    public void registrationDuplicate(){

        AuthRequestDTO auth = AuthRequestDTO.builder()
                .username("tretam0810@gmail.com")
                .password("Phone54321#")
                .build();
        given()
                .body(auth)
                .contentType(ContentType.JSON)
                .when()
                .post(endPoint)
                .then()
                .assertThat().statusCode(409)
                .assertThat().body("message", containsString("User already exists"));

    }
}
