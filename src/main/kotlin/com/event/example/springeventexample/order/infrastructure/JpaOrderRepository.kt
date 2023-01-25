package com.event.example.springeventexample.order.infrastructure

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
