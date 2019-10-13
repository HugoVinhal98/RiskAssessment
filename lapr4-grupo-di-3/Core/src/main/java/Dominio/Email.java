/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author GilTrindade
 */
@Embeddable
public class Email implements Serializable {
    
    private String Email;

    public Email(String Email) {
        this.Email = Email;
    }
    

    public Email() {
    }

    public String getEmail() {
        return Email;
    }
    
}
