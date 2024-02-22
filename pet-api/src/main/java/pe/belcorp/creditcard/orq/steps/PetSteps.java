package pe.belcorp.creditcard.orq.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import pe.belcorp.creditcard.orq.Util.Util;
import pe.belcorp.creditcard.orq.stepdefinitions.PetStepDefinition;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class PetSteps {


    PetStepDefinition petStepDefinition;


    private static String TEMPLATE_PET= "templates/pet.json";
    private static String TEMPLATE_UPDATE_PET= "templates/UpdatePet.json";
    private static String TEMPLATE_USER= "templates/user.json";
    private static String TEMPLATE_UPDATE_USER= "templates/UpdateUser.json";
    public String idPet;
    public String namePet;
    public String emailUser;
    public String idUser="77270572";
    public String nameUser="JorgeBaca";


    @Step("Creando registro de adopcion de una mascota")
    public void createPet(String petName){

        String body = Util.getTemplate(TEMPLATE_PET)
                .replace("{{pet_id}}", "1002")
                .replace("{{category_id}}","0")
                .replace("{{category_name}}","dog")
                .replace("{{name_pet}}",petName)
                .replace("{{photoUrls}}","https://cnnespanol.cnn.com/wp-content/uploads/2020/07/200703104728-labrador-retriever-stock-super-169.jpg?quality=100&strip=info&w=940&h=530&crop=1")
                .replace("{{tags_id}}","10")
                .replace("{{tags_id_name}}","Jorge")
                .replace("{{status}}","available");
        Response response = SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(body)
                .post( "https://petstore.swagger.io/v2/pet");
        System.out.println("[PET]: " + response.getBody().asString());
        idPet = response.then().assertThat().statusCode(200).extract().response().jsonPath().getString("id");
           }

    @Step("Consulta el registro de mascota")
    public void findPet() {
        Response response =  SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .get("https://petstore.swagger.io/v2/pet/"+idPet);
        System.out.println("[PET]: " + response.getBody().asString());
        namePet= response.then().assertThat().statusCode(200).extract().response().jsonPath().getString("name");
    }

    @Step("Consulta el registro de usuario dueño")
    public void findUser() {
        Response response =  SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .get("https://petstore.swagger.io/v2/user/"+nameUser);
        System.out.println("[USER]: " + response.getBody().asString());
        String code= response.then().assertThat().statusCode(200).extract().response().jsonPath().getString("200");
    }

    @Step("Actualizando información")
    public void updatePet(String petName){

        String body = Util.getTemplate(TEMPLATE_UPDATE_PET)
                .replace("{{pet_id}}", idPet)
                .replace("{{category_id}}","0")
                .replace("{{category_name}}","dog")
                .replace("{{name_pet}}",petName)
                .replace("{{photoUrls}}","https://cnnespanol.cnn.com/wp-content/uploads/2020/07/200703104728-labrador-retriever-stock-super-169.jpg?quality=100&strip=info&w=940&h=530&crop=1")
                .replace("{{tags_id}}","10")
                .replace("{{tags_id_name}}","Jorge")
                .replace("{{status}}","available");
        Response response = SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(body)
                .put( "https://petstore.swagger.io/v2/pet");

        System.out.println("[PET]: " + response.getBody().asString());
        namePet= response.then().assertThat().statusCode(200).extract().response().jsonPath().getString("name");

    }

    @Step("Eliminando el registro de mascota")
    public void deletePet() {
        Response response =  SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .delete("https://petstore.swagger.io/v2/pet/"+idPet);
        System.out.println("[PET]: " + response.getBody().asString());
        String code= response.then().assertThat().statusCode(200).extract().response().jsonPath().getString("code");
        assertThat(code, equalTo("200"));
    }

    @Step("Eliminando el registro de usuario")
    public void deleteUser() {
        Response response =  SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .delete("https://petstore.swagger.io/v2/user/"+nameUser);
        System.out.println("[USER]: " + response.getBody().asString());
        String code= response.then().assertThat().statusCode(200).extract().response().jsonPath().getString("code");
        assertThat(code, equalTo("200"));
    }

    @Step("Consultando el registro de mascota")
    public void verifyRegistryPetName() {
        assertThat(namePet, equalTo(petStepDefinition.namePet));
    }

    @Step("Consultando el registro de mascota actualizada")
    public void verifyUpdatePetName() {
        assertThat(namePet, equalTo(petStepDefinition.newNamePet));
    }


    @Step("Verificar el registro de mascota eliminado")
    public void verifyDeletePet() {
        Response response =  SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .get("https://petstore.swagger.io/v2/pet/"+idPet);
        System.out.println("[PET]: " + response.getBody().asString());
        String message= response.then().assertThat().statusCode(404).extract().response().jsonPath().getString("message");
        assertThat(message, equalTo("Pet not found"));
    }

    @Step("Verificar el registro de mascota eliminado")
    public void verifyDeleteUser() {
        Response response =  SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .get("https://petstore.swagger.io/v2/user/"+nameUser);
        System.out.println("[USER]: " + response.getBody().asString());
        String message= response.then().assertThat().statusCode(404).extract().response().jsonPath().getString("message");
        assertThat(message, equalTo("User not found"));
    }

    @Step("Creando registro de usuario dueño de una mascota")
    public void createUser(String email){

        String body = Util.getTemplate(TEMPLATE_USER)
                .replace("{{username}}",nameUser)
                .replace("{{firstName}}","Jean Paul")
                .replace("{{lastName}}","Baca")
                .replace("{{email}}",email)
                .replace("{{password}}","123456")
                .replace("{{phone}}","921447908");
        Response response = SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(body)
                .post( "https://petstore.swagger.io/v2/user");
        System.out.println("[USER]: " + response.getBody().asString());
        String code = response.then().assertThat().statusCode(200).extract().response().jsonPath().getString("code");
        assertThat(code, equalTo("200"));
        emailUser=email;
    }


    @Step("Actualizando el correo del dueño de la mascota")
    public void updateUser(String newEmail){

        String body = Util.getTemplate(TEMPLATE_UPDATE_USER)
                .replace("{{username}}",nameUser)
                .replace("{{firstName}}","Jean Paul")
                .replace("{{lastName}}","Baca")
                .replace("{{email}}",newEmail)
                .replace("{{password}}","123456")
                .replace("{{phone}}","921447908")
                .replace("{{userStatus}}","Jorge");
        Response response = SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(body)
                .put( "https://petstore.swagger.io/v2/user/"+nameUser);
        System.out.println("[USER]: " + response.getBody().asString());
        String messageUser = response.then().assertThat().statusCode(200).extract().response().jsonPath().getString("message");
        assertThat(idUser, equalTo(messageUser));

    }

}