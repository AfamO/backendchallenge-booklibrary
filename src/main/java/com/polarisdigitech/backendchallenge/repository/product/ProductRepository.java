
package com.polarisdigitech.backendchallenge.repository.product;

import com.polarisdigitech.backendchallenge.model.product.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.*;
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
            entityManager.merge(tClass);
        return (T)tClass;
    }

    public void findCarByYearWithNamedStored(int year) {
        StoredProcedureQuery storedProcedureFindByYearQuery =
                entityManager.createNamedStoredProcedureQuery("findByYearProcedure");
        StoredProcedureQuery storedProcedureQuery =
                storedProcedureFindByYearQuery.setParameter("p_year", year);
      List<Car> carList  =  storedProcedureQuery.getResultList();
      log.info("The storedProcedure queried result =={}",carList);
    }

    public List<Car> findCarByYearWithNoNameStoredProcedure(int year) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("FIND_CAR_BY_YEAR", Car.class);
        storedProcedure.registerStoredProcedureParameter("yearParam",Integer.class, ParameterMode.IN)
                .setParameter("yearParam",year);
        return storedProcedure.getResultList();

    }

    @Transactional
    public int countTotalProductsGivenAPrice(int price){
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("findTotalProductsByPrice");
        storedProcedureQuery.setParameter("price_in",price);
        return storedProcedureQuery.executeUpdate();

    }
}
