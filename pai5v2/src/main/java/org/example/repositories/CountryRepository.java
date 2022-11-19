package org.example.repositories;

import org.example.entities.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, String> {
    List<Country> findByOrderByName();

    List<Country> findByContinentOrderByName(String continent);

    List<Country> findByPopulationBetweenOrderByPopulation(int minPop, int maxPop);

    List<Country> findByContinentAndSurfaceAreaLessThanOrderBySurfaceArea(String continent, double maxArea);

    List<Country> findByContinentAndSurfaceAreaGreaterThanOrderBySurfaceArea(String continent, double minArea);

    List<Country> findByContinentAndSurfaceAreaBetweenOrderBySurfaceArea(String continent, double minArea, double maxArea);
}