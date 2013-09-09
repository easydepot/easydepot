package models;

import javax.validation.*;
import java.util.*;
import java.io.File;
import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

import play.data.validation.Constraints.*;

@Entity
public class Record extends Model {
   
  @Id
  public Long id;
  @Required
  public String firstName;
  @Required
  public String lastName;
  @Required
  public Date dateOfBirth;
  @Required
  public String email;
  @Required
  public File deposit; 
  
  public static Finder<Long,Record> find = new Finder(
    Long.class, Record.class
  );

  
  public static List<Record> all() {
    return find.all();
  }
  
  public static void create(String firstName, String lastName, File file, String email) {
  	Record record = new Record();
  	record.firstName= firstName;
  	record.lastName = lastName;
  	record.deposit = file;
  	record.email =email;
  	record.save();
  }
  
  
    
}