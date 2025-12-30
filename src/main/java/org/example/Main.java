package org.example;

import org.example.dao.CarDAO;
import org.example.dao.CarDAOImpl;
import org.example.entities.Car;
import org.example.entities.CarOwner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();
//        cfg.addAnnotatedClass(org.example.entities.Car.class);

        // old way to create object mapping si write <classname>.hbm.xml file fore each entity
        // new way is that you create it with annotation

        // here implemented with annotation

        /* any one of file need to have in resource directory
             hibernate.cfg.xml | hibernate.properties

             at build time it's placed under root directory
         */
        /*
         --> for hibernate.cfg.xml
            use config.configure();
         --> for hibernate.properties
            use config.addAnnotatedClass(<entity class which is annotated with annotations (package)>)
        */
        SessionFactory sf = cfg.buildSessionFactory();

        // usage of Car DAO (Data Access Object)
//        CarDAO cdao = new CarDAOImpl(sf); // why dao separates logic of DB from business logic
        // just another design pattern
        // use any methods you like passing theses args we created

        try (Session s = sf.openSession()){
            s.beginTransaction();

//            CarOwner co = new CarOwner();
//            co.setName("hello");
//            Car c3 = new Car();
//            c3.setName("hib.cfg.car3");
//            c3.setOwner(co);
//            co.setCar(c3);
//
//            s.persist(c3);
            // if your using bidirectional you can use persist with oc object it will update car

            Car c3 = s.find(Car.class,1);
            System.out.println("car object : \nID:" + c3.getId() +"\tNAME:"+ c3.getName() +"\t owner Name:" + c3.getOwner().getName());



            // Operation on Database Records (CURD)
            /*
                save : session.persist(<object>)
                update : persist also updates it if it changed before closing transection
                read : session.find(<object class> , id) used to load object if we didn't know matching row exist
                delete : session.remove(<object>)

                merge : session.merge() copy state of detached object to managed persistent instance and returns persistent object
                detach : remove object from persistent object from session without affecting DB
                clear : empty persistence context & detach all its entities
                flush : detect change in persistence object and synchronize with DB (using insert,update,delete)


                refresh : retrieve current state of object session.refresh(<object>)
                            session.refresh() reloads the current state of an entity from the database, discarding any changes made in memory but not yet persisted.
                          * There is no method called
                locking : session.lock()
                          * No database update/check
                          * Concurrency control: Useful when you want to ensure consistency in concurrent transactions by applying a lock mode.
            */

            // persistence context
            /*
                cash memory, holds unique mapping from the identifier of the entity instance to instance itself
                -> states
                    1. transient  : not associated with persistence contex.
                        * short of in program memory
                    2. persistent : associated with persistence context.
                        * short of device memory(not in program that writen by me)
                    3. detached   : previously persistent in another session,but currently not associated with persistence contex
                        * short of data goes to DB

                - any modification done before session.close() will reflect to DB

                while removing persistent will remove it form transient but in DB it will remove it while session.close()

                session.merge() will attach detached state to persistent

                --> state transition diagram

                                persist()
                                -------->               clear()
                    Transient -           - Persistent ----------> Detached
                                <--------               close()
                                remove()
            */
            s.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            sf.close();
        }
    }
}