package com.polarisdigitech.backendchallenge.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
        name = "findByYearProcedure",
        procedureName = "Find_Car_By_Year",
        resultClasses = {Car.class},
        parameters = {
        @StoredProcedureParameter(
                name = "p_year",
                type = Integer.class,
                mode = ParameterMode.IN
        )
        }
        )
})
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String model;
    private Integer year;



}
