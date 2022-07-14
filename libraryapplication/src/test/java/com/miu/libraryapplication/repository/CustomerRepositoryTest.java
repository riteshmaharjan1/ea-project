package com.miu.libraryapplication.repository;

import com.miu.libraryapplication.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ICustomerRepository customerRepository;

    @Test
    public void whenSearchByName_thenReturnCustomerOfThatName(){
        //given
        Customer customer = new Customer("Ritesh", "ritesh@gmail.com");
        entityManager.persist(customer);
        entityManager.flush();

        // when
        Customer found = (customerRepository.findCustomerByName("Ritesh"));

        // then
        assertThat(customer.getCustomerNumber()).isEqualTo(found.getCustomerNumber());
    }
}
