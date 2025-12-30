package org.example.dao;

import org.example.entities.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Queue;

public class CarDAOImpl implements CarDAO{

    private final SessionFactory sf;

    public CarDAOImpl(SessionFactory sf) {
        this.sf = sf;
    }


    @Override
    public void saveCar(Car c) {
        try(Session s = sf.openSession()){
            Transaction t = s.beginTransaction();

            s.persist(c);

            t.commit();
        }
    }

    @Override
    public Car getCar(long id) {
        try(Session s = sf.openSession()){
            return s.find(Car.class,id);
        }
    }

    @Override
    public List<Car> getAllCars() {
        try(Session s = sf.openSession()){
            Query<Car> q = s.createQuery("FROM Car",Car.class);
            return q.list();
        }
    }

    @Override
    public void updateCar(Car c) {
        try(Session s = sf.openSession()){
            Transaction t = s.beginTransaction();

            Car car = s.find(Car.class,c.getId());
            car.setName(c.getName());

            t.commit();
        }
    }

    @Override
    public void deleteCar(long id) {
        try(Session s = sf.openSession()){
            Transaction t = s.beginTransaction();

            Car c = s.find(Car.class,id);
            if(c != null){
                s.remove(c);
            }
            t.commit();
        }
    }
}
