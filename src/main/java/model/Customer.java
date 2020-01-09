package model;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@NamedQuery(
        name = "findAll",
        query = "select c from Customer c"
)
@NamedStoredProcedureQuery(
        name = "save",
        procedureName = "save",
        parameters = {
                @StoredProcedureParameter(name = "cusfirstname", mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(name = "cuslastname", mode = ParameterMode.IN, type = String.class)
        }
)
@NamedStoredProcedureQuery(
        name = "update",
        procedureName = "updateCus",
        parameters = {
                @StoredProcedureParameter(name = "cusid",mode = ParameterMode.IN,type = Integer.class),
                @StoredProcedureParameter(name = "cusfirstname",mode = ParameterMode.IN,type = String.class),
                @StoredProcedureParameter(name = "cuslastname",mode = ParameterMode.IN,type = String.class)
        }
)
@NamedStoredProcedureQuery(
        name = "delete",
        procedureName = "removeCus",
        parameters = {
                @StoredProcedureParameter(name = "cusid",mode = ParameterMode.IN,type = Integer.class)
        }
)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;

    public Customer(){}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d,firstName='%s',lastName='%s']",id,firstName,lastName);
    }
}
