
package com.polarisdigitech.backendchallenge.repository.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
@Slf4j
public class ProductRepository {

    @Autowired
    @Qualifier("productDbEntityManager")
    @PersistenceContext(unitName = "productDbEntityManager")
    private  EntityManager entityManager;



    public <T> T findOne(Class<T> tClass, Long id){
        T obj = entityManager.find(tClass,id);
        if (!StringUtils.isEmpty(obj)){
            entityManager.detach(obj);
            return obj;
        }
        return null;
    }

    public  <T,K> List<T> findAllByIds(Class<T> tClass, Collection<K> ids){
        log.info("The name of given class is {}",tClass.getSimpleName());
        TypedQuery<T>  tTypedQuery = entityManager.createQuery("SELECT t FROM "+tClass.getSimpleName()+" t WHERE t.id IN :ids", tClass);
        tTypedQuery.setParameter("ids",ids);
        //ResultUtil.fetchList(tTypedQuery);

        return (List<T>) tTypedQuery.getResultList();
    }
    @Transactional
    public <T> T save(final T tClass){
        log.info("Saving:::{}",tClass);
        entityManager.persist(tClass);

        return tClass;
    }
}
