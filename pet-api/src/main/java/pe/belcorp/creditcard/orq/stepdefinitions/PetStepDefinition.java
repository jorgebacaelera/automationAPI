package pe.belcorp.creditcard.orq.stepdefinitions;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import net.thucydides.core.annotations.Steps;
import pe.belcorp.creditcard.orq.steps.PetSteps;

public class PetStepDefinition {

    @Steps
    PetSteps petSteps;

    public static String namePet="";
    public static String newNamePet="";
    public static String newEmail="";


    @Dado("que una persona realiza una adopción de una mascota con nombre (.*)")
    public void setPet(String nombre){
        namePet=nombre;
        petSteps.createPet(nombre);
        petSteps.findPet();
        petSteps.verifyRegistryPetName();
    }

    @Y("el dueño se registra en el sistema de la veterinaria con correo (.*)")
    public void createUser(String correo){
        petSteps.createUser(correo);
        petSteps.findUser();
    }

    @Cuando ("el cliente desea actualizar el nombre (.*) de su mascota")
    public void updatePet(String nombre){
        newNamePet=nombre;
        petSteps.updatePet(nombre);
    }

    @Entonces("el sistema Pet deberá actualizar la información correctamente")
    public void confirmPet(){
        petSteps.findPet();
        petSteps.verifyUpdatePetName();
    }

    @Y("el dueño desea actualizar su correo (.*)")
    public void updateEmail(String newEmail){
        petSteps.updateUser(newEmail);
    }

    @Y("la veterinaria deberá eliminar el registro de la mascota y del dueño")
    public void deletePet(){
        petSteps.deletePet();
        petSteps.verifyDeletePet();
        petSteps.deleteUser();
        petSteps.verifyDeleteUser();
    }

}
