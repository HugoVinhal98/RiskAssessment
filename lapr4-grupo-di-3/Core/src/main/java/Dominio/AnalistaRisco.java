/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import Persistencia.AnalistaRiscoRepositorioJPAImpl;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author GilTrindade
 */
@Entity
public class AnalistaRisco implements Serializable {

    /**
     * Id auto gerado que identifica uma analista de risco.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private NomeUtilizador nomeUtilizador;

    @Embedded
    private Password password;

    @Embedded
    private Email email;

    /**
     *
     * @param nomeUtilizador
     * @param password
     * @param email
     */
    public AnalistaRisco(NomeUtilizador nomeUtilizador, Password password, Email email) {
        this.nomeUtilizador = nomeUtilizador;
        this.password = password;
        this.email = email;
    }

    /**
     *
     */
    public AnalistaRisco() {
    }

    /**
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return nomeUtilizador
     */
    public NomeUtilizador getNomeUtilizador() {
        return nomeUtilizador;
    }

    /**
     *
     * @return password
     */
    public Password getPassword() {
        return password;
    }

    /**
     *
     * @return email
     */
    public Email getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "AnalistaRisco{" + "nomeUtilizador=" + nomeUtilizador + '}';
    }

    /**
     * Método para validar a password mediante os critérios definidos.(tamanho,
     * maiusculas, minusculas e numeros).
     *
     * @param password
     * @return
     */
    public boolean validarPassword(String password) {

        boolean valid = true;
        if (password.length() > 20 || password.length() < 8) {
            System.out.println("Password deve ter entre 8 a 20 caratéres.");
            valid = false;
        }

        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars)) {
            System.out.println("Password deve conter pelo menos uma maiúscula");
            valid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars)) {
            System.out.println("Password deve conter pelo menos uma minúcula");
            valid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers)) {
            System.out.println("Password deve conter pelo menos um número.");
            valid = false;
        }

        if (valid) {
            System.out.println("Password é válida");
        }
        return valid;
    }

    /**
     * Método para validar o email.
     *
     * @param email
     * @return
     */
    public boolean validarEmail(String email) {

        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);

    }

    /**
     * Método para validar o login.
     *
     * @param email
     * @param password
     * @return
     */
    public String validarLogin(String email, String password) {
        AnalistaRiscoRepositorioJPAImpl repo = new AnalistaRiscoRepositorioJPAImpl();
        List<AnalistaRisco> array = repo.findAll();
        String nome = "";
        for (int i = 0; i < array.size(); i++) {
            AnalistaRisco ar = array.get(i);
            if (ar.getEmail().getEmail().equalsIgnoreCase(email) && ar.getPassword().getPassword().equalsIgnoreCase(password)) {
                nome = ar.getNomeUtilizador().getNomeUtilizador();
            }

        }
        return nome;

    }

}
