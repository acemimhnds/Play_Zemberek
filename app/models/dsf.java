package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class dsf extends Model {
    String kok;
    
    public dsf(String val){
        this.kok=val;        
        
    }
    
    
    
}
