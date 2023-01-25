package com.event.example.springeventexample.order.infrastructure

import com.event.example.springeventexample.order.domain.Order
import com.event.example.springeventexample.order.domain.repository.OrderRepository
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
/*

@Repository
class JpaOrderRepository : OrderRepository {
    @PersistenceContext
    private lateinit var entityManager: EntityManager
    
    override fun findById(id: Long): Order = 
        entityManager.find(Order::class.java, id) ?: Order.NOT_EXIST

    override fun save(order: Order) {
        entityManager.persist(order)
    }

    override fun remove(order: Order) {
        entityManager.merge(order)
    }
}
*/
