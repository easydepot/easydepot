package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import org.apache.commons.mail.*;
import static play.data.Form.*;
import play.data.validation.Constraints.*;

import java.util.*;
import java.io.File;

import views.html.*;
import models.*;


public class Application extends Controller {
    
    /**
     * Describes the hello form.
     */
    public static class EasyDepotRequest {
        @Required public String firstName;        
        

        @Required public String lastName;
        @Required public String email;
        @Required public Date dateOfBirth;
        @Required public File deposit;

       
    } 
    
    // -- Actions
  
    /**
     * Home page
     */
    public static Result index() {
        return ok(
            index.render(form(EasyDepotRequest.class))
        );
    }
  
  
      public static Result admin() {
      	return ok(
              admin.render(Record.all())
            );

      }
      

    /**
     * Handles the form submission.
     */
    public static Result submit() {

	 
	    Form<EasyDepotRequest> form = form(EasyDepotRequest.class).bindFromRequest();
        if(!form.field("email").valueOr("").isEmpty()) {
            if(!form.field("email").valueOr("").equals(form.field("emailconfirm").value())) {
                form.reject("emailconfirm", "Erreur dans votre email");
            }
        }
       

        
        if(form.hasErrors()) {
            return badRequest(index.render(form));
        } else {
            EasyDepotRequest data = form.get();
            notifyEasyDepot(data.firstName, data.lastName, data.deposit.getName(), data.email);
            Record.create(data.firstName, data.lastName, data.deposit, data.email);
            return ok(
              hello.render(data.firstName, data.lastName, data.deposit.getName())
            );
        }
    }
    
    /**
     * Email notification
     */
     public static void notifyEasyDepot(String firstname, String lastName, String file, String emailadress) {
try {


  // Create the email message
  SimpleEmail email = new SimpleEmail();
  email.setSmtpPort(465);
  email.setAuthenticator(new DefaultAuthenticator("julien.grosmlambert@mybusinesseducation.fr", "xxxxx"));
  email.setSSLOnConnect(true);
  email.setHostName("smtp.gmail.com");
  email.addTo("julien.grosmlambert@mybusinesseducation.fr", "EasyDepot");
  email.setFrom("julien.grosmlambert@mybusinesseducation.fr", "EasyDepot");
  email.setSubject("[EasyDepot] Nouveau dépot");
  email.setMsg(firstname + " " + lastName + "(" + emailadress + ") a déposé le fichier:" + file);
} catch (Exception e){
	e.printStackTrace();
}
 

 
     }
  
}
