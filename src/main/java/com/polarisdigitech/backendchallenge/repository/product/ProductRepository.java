
package com.polarisdigitech.backendchallenge.repository.product;

import com.polarisdigitech.backendchallenge.model.product.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
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

    public <T> List<T> searchDBForGivenDateRange(Class<T> tClass, LocalDateTime fromDate, LocalDateTime toDate){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);

        Root<T> root = criteriaQuery.from(tClass);
        //Predicate itemNamePredicate = criteriaBuilder.equal(itemRoot.get("name"), name);
        //Predicate itemPricePredicate = criteriaBuilder.equal(itemRoot.get("price"),price);
        log.info("My fromDate=={}",fromDate);
        log.info("My toDate=={}",toDate);
        Predicate fromDateTime = criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"),fromDate);
        Predicate toDateTime = criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"),toDate);
        criteriaQuery.where(fromDateTime,toDateTime);

        TypedQuery<T> itemTypedQuery = entityManager.createQuery(criteriaQuery);
        return itemTypedQuery.getResultList();
    }
    public <T> List<T> findInTableWithNameLike(Class<T> tClass,String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> tRoot = criteriaQuery.from(tClass);
        log.info("name To search=={}",name);
        Predicate likeNamePredicate = criteriaBuilder.like(tRoot.get("name"), "%"+name+"%");
        criteriaQuery.where(likeNamePredicate);

        TypedQuery<T> tTypedQuery = entityManager.createQuery(criteriaQuery);
        return tTypedQuery.getResultList();
    }

    public  <T,K> List<T> findAllByIds(Class<T> tClass, Collection<K> ids){
        log.info("The name of given class is {}",tClass.getSimpleName());
        TypedQuery<T>  tTypedQuery = entityManager.createQuery("SELECT t FROM "+tClass.getSimpleName()+" t WHERE t.id IN :ids", tClass);
        tTypedQuery.setParameter("ids",ids);

        return (List<T>) tTypedQuery.getResultList();
    }
    @Transactional
    public <T> T save(final T tClass){
        /**
        //Is it already in managed state?
        boolean IsManaged = entityManager.contains(tClass);
        if(IsManaged){
            log.info("This object is already in persistent context and will be updated instead");
        }
        */
        log.info("Before saving, entity name::{}",tClass);
        if (tClass instanceof List){
            List<T> bulkLists = (List<T>)tClass;
            bulkLists.stream().forEach((t)->entityManager.persist(t));
        }
        else
            entityManager.persist(tClass);
        return (T)tClass;
    }
}
