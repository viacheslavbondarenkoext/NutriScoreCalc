package com.nutriscorecalc.database;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Named
@Transactional
public class ProductRepository {

    @PersistenceContext
    private EntityManager em;

    public ProductEntity save(ProductEntity entity) {
        if (entity.id == null) {
            em.persist(entity);
            return entity;
        }
        return em.merge(entity);
    }

    public Optional<ProductEntity> findById(Long id) {
        return Optional.ofNullable(em.find(ProductEntity.class, id));
    }

    public List<ProductEntity> findAll() {
        return em.createQuery("select p from ProductEntity p", ProductEntity.class).getResultList();
    }

    public void deleteAll() {
        em.createQuery("delete from ProductEntity").executeUpdate();
    }
}

