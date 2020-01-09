package repository.impl;

import model.Customer;
import repository.ICustomerRepository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Queue;

@Transactional
public class CustomerRepositoryImpl implements ICustomerRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = em.createNamedQuery("findAll").getResultList();
        return customers;
    }

    @Override
    public Customer findById(int id) {
        for (Customer c : findAll()) {
            if(c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void save(Customer customer) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("save");
        query.setParameter("cusfirstname",customer.getFirstName());
        query.setParameter("cuslastname",customer.getLastName());
        query.execute();
    }

    @Override
    public void remove(int id) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("delete");
        query.setParameter("cusid",id);
        query.execute();
    }

    @Override
    public void update(int id, Customer customer) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("update");
        query.setParameter("cusid",id);
        query.setParameter("cusfirstname",customer.getFirstName());
        query.setParameter("cuslastname",customer.getLastName());
        query.execute();
    }
}
