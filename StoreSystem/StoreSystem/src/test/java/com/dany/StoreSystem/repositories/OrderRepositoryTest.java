package com.dany.StoreSystem.repositories;

import com.dany.StoreSystem.entities.Local;
import com.dany.StoreSystem.entities.Manager;
import com.dany.StoreSystem.entities.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void saveOrder(){

        Manager manager = Manager.builder()
                .firstName("Nicolas")
                .lastName("Lopez")
                .build();

        Local local = Local.builder()
                .name("Binco")
                .floor("Fourth Floor")
                .manager(manager)
                .build();

        Order order = Order.builder()
                .description("Camisa de tirantes blanca")
                .price(10.0)
                .local(local)
                .build();
        orderRepository.save(order);
    }

    @Test
    public void findAllOrdersPaging(){
        Pageable firstPageWithThreeRecords = PageRequest.of(0,3);
        List<Order> orderList = orderRepository.findAll(firstPageWithThreeRecords).getContent();
        long totalElements = orderRepository.findAll(firstPageWithThreeRecords).getTotalElements();
        long totalPages = orderRepository.findAll(firstPageWithThreeRecords).getTotalPages();
        System.out.println("orderList = " + orderList);
        System.out.println("totalElements = " + totalElements);
        System.out.println("totalPages = " + totalPages);
    }

    @Test
    public void findAllOrdersWithSorting(){
        Pageable sortByPrice = PageRequest.of(0,3, Sort.by("price"));
        Pageable sortByPriceDesc = PageRequest.of(0,3, Sort.by("price").descending());
        List<Order> orderList = orderRepository.findAll(sortByPriceDesc).getContent();
        System.out.println("orderList = " + orderList);
    }

    @Test
    public void findAllOrdersDescriptionContainig(){
        Pageable pageable = PageRequest.of(0,5);
        List<Order> orderList = orderRepository.findByDescriptionContaining("En",pageable).getContent();
        System.out.println("orderList = " + orderList);
    }

}