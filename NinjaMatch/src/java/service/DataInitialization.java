/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.AdminAccountFacade;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import model.Address;
import model.AdminAccount;

/**
 *
 * @author yyun
 */
@Singleton
@Startup
public class DataInitialization {

    @Inject
    private AdminAccountFacade adminFacade;

    @PostConstruct
    public void init() {
        //initialize admin user,
        try {
            AdminAccount admin = adminFacade.findByUsername("admin");
            if (admin == null) {
                admin = new AdminAccount();
                admin.setUserName("admin");
                admin.setPassword("admin");
                admin.setFirstName("Ninja");
                admin.setLastname("Admin");
                admin.setGender("Male");
                admin.setBirthDate(new Date());
                admin.setRegisteredDate(new Date());
                admin.setAddress(new Address("1000 4 Street", "Fairfield", "IA", "52257"));
                adminFacade.create(admin);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}