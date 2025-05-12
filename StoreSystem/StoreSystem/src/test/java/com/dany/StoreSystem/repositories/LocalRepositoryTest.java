package com.dany.StoreSystem.repositories;

import com.dany.StoreSystem.entities.Customer;
import com.dany.StoreSystem.entities.Local;
import com.dany.StoreSystem.entities.Manager;
import com.dany.StoreSystem.entities.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LocalRepositoryTest {

    @Autowired
    private LocalRepository localRepository;

    @Test
    public void saveLocal(){
        Manager manager = Manager.builder()
                .firstName("Juan")
                .lastName("Perez")
                .build();

        Local local = Local.builder()
                .name("PetShop")
                .floor("Second Floor")
                .manager(manager)
                .build();
        localRepository.save(local);
    }

    @Test
    public void findAllLocals(){
        List<Local> localList = localRepository.findAll();
        System.out.println("localList = " + localList);
    }

    @Test
    public void saveLocalWithOrders(){

        Manager manager = Manager.builder()
                .firstName("Juana")
                .lastName("Rodriguez")
                .build();

        Order order = Order.builder()
                .description("Entrada Pelicula 1 Sala 2 en 2D")
                .price(6.50)
                .build();

        Order order2 = Order.builder()
                .description("Entrada Pelicula 2 Sala 3 en 3D")
                .price(8.50)
                .build();

        Local local = Local.builder()
                .name("Cinema")
                .floor("Third Floor")
                .manager(manager)
                .orderList(List.of(order,order2))
                .build();

        localRepository.save(local);

    }

    @Test
    public void findAllLocalsWithOrders(){
        List<Local> localList = localRepository.findAll();
        System.out.println("localList = " + localList);
    }

    @Test
    public void saveLocalWithCustomer(){

        Manager manager = Manager.builder()
                .firstName("Edu")
                .lastName("Mango")
                .build();

        Customer customer = Customer.builder()
                .firstName("Jose")
                .lastName("Jhonson")
                .email("Jose@ejemplo.com")
                .build();

        Customer customer2 = Customer.builder()
                .firstName("Gabi")
                .lastName("Rodriguez")
                .email("Gabi@ejemplo.com")
                .build();


        Local local = Local.builder()
                .name("RestoBar")
                .floor("First Floor")
                .manager(manager)
                .customerList(List.of(customer,customer2))
                .build();

        localRepository.save(local);
    }

    @Test
    public void findAllLocalsWithCustomers(){
        List<Local> localList = localRepository.findAll();
        System.out.println("localList = " + localList);
    }

    @Test
    public void findCustomersByLocal(){
        Local local = localRepository.findById(2L).get();
        List<Customer> customerList = local.getCustomerList();
        System.out.println("customerList = " + customerList);
    }
}