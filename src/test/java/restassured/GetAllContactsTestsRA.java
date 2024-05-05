package restassured;

import com.jayway.restassured.RestAssured;
import dto.ContactDTO;
import dto.GetAllContactsDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class GetAllContactsTestsRA {

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoidHJldGFtMDgxMEBnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTcxNTUyMjQ2NiwiaWF0IjoxNzE0OTIyNDY2fQ.Q_cLbccOOz07ETBCtnZW63Npk3koHu3qS3s3gWe-6ns";
    @BeforeMethod
    public void preCondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }

    @Test
    public void getAllContactsSuccess(){
        GetAllContactsDTO contactsDTO = given()
                .header("Authorizaton", token)
                .when()
                .get("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .response()
                .as(GetAllContactsDTO.class);
        List<ContactDTO> list = contactsDTO.getContacts();
        for(ContactDTO contact : list){
            System.out.println(contact.getId());
            System.out.println(contact.getEmail());
            System.out.println("Size of list--> " + list.size());
        }

    }

    @Test
    public void getAllContactsWrongToken(){

        //homework
    }
}
