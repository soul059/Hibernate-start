package org.example.entities;

import jakarta.persistence.*;
import org.hibernate.sql.results.graph.collection.internal.EagerCollectionFetch;

//@Base : for providing base layout
// use object property name as default column name
//@Table : for giving table name
// use object name as table name default
@Entity
public class Car {


    // auto generation of primary key (user never provides key it's always auto generated)
    // use @GeneratedValue

    /*  TYPES of auto generation
        * how to write @GeneratedValue( strategy = GenerationType.<type> )
        * Default its AUTO

       --> SEQUENCE : creates new table to store last sequence write as <default table name>_seq
            * it generate few key values at start (1 - 50) and stores last generated value into table
               * use DB sequence object
       --> IDENTITY : use of default database "auto_increment feature" if database provides it
       --> AUTO     : hibernate will deside bast strategy from IDENTITY & SEQUENCE
       --> TABLE    : legacy DBs without SEQUENCE & IDENTITY
             * work same as SEQUENCE
    */

    /*  custom  generator
            @GenericGenerator(
                name = "my-gen",
                strategy = "com.example.MyCustomIdGenerator"
            )
        OR
        using separate tabel that defined by you
            @TableGenerator(
                name = "car_gen",
                table = "carid_gen",
                pkColumnName = "gen_name",
                valueColumnName = "id",
                pkColumnValue = "car_id",
                initialValue = 1000,
                allocationSize = 1
            )

        initialValue = 1000 → First ID will start at 1000.
        allocationSize = 1 → Increment by 1 each time
    */
    @Id @GeneratedValue @Column(name = "car_id")
    private long id;

    // if not provided name it creates column by object property name
    // by default null (value can be null)
    // if used nullable true *default true* only column removed not the data
    @Column(name = "car_name",nullable = false)

    //@Transient : ignored fild by hbm2ddl.auto (no update by anything)

    //@Temporal : date conversation of java object - sql data
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    /*
        cascade : it's effect if anything happens to car class it reflected to owner (delete)
            - ALL,DETCH,MERGE,PERSIST,REMOVE,REFRESH
        fetch : load owner ifo while loading car
            - EAGER(default in one to one), LAZY(default in many-many, one-many and many-one)


    */
    @JoinColumn(name = "car_owner")
    private CarOwner owner;
    /*
        many-one : use annotation @ManyToOne options are same
            = bidirectional many-one: you need to use map datatype in many side and use @OneToMany
                 - it has one extra property (mappedBy = "<car class FK>") owner of relationship

        many-many : both has multiple elements so we will use set on both side
            * it creates another table with default name (<Table one name>_<Table two name>)
                Table A  ----- mapping Table ----- Table B
            --> use @ManyToMany on property in owner class
            * column name will be <Table name>_<Table primary key>

            Join columns name use @JoinTable(name,joinClumns(write owner fk) = { @JoinColumn annotation here},inverseJoinColumns(fk of owner's objects) = { @JoinColumn annotation here})

            = bidirectional use these in both classes
    */
    public long getId() {
        return id;
    }

    public CarOwner getOwner() {
        return owner;
    }

    public void setOwner(CarOwner owner) {
        this.owner = owner;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
