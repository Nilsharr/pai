package org.example.controllers;

import org.example.entities.Country;
import org.example.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CountryController {

    @Autowired
    public CountryRepository countryRepository;

    @RequestMapping(value = {"/", "/countryList"})
    @ResponseBody
    public String countryList() {
        return getFormattedCountryList(countryRepository.findByOrderByName());
    }

    @RequestMapping("/countryList/{continent}")
    @ResponseBody
    public String countryListByContinentAndArea(@PathVariable(value = "continent") String continent,
                                                @RequestParam(value = "min-area", required = false) Double minArea, @RequestParam(value = "max-area", required = false) Double maxArea) {
        if (minArea != null && maxArea != null) {
            return getFormattedCountryList(countryRepository.findByContinentAndSurfaceAreaBetweenOrderBySurfaceArea(continent, minArea, maxArea));
        }
        if (minArea != null) {
            return getFormattedCountryList(countryRepository.findByContinentAndSurfaceAreaGreaterThanOrderBySurfaceArea(continent, minArea));
        }
        if (maxArea != null) {
            return getFormattedCountryList(countryRepository.findByContinentAndSurfaceAreaLessThanOrderBySurfaceArea(continent, maxArea));
        }
        return getFormattedCountryList(countryRepository.findByContinentOrderByName(continent));
    }

    @RequestMapping("/countryListByPopulationBetween/{minPop}/{maxPop}")
    @ResponseBody
    public String countryListByPopulationBetween(@PathVariable(value = "minPop") int minPop, @PathVariable(value = "maxPop") int maxPop) {
        return getFormattedCountryList(countryRepository.findByPopulationBetweenOrderByPopulation(minPop, maxPop));
    }

    private String getFormattedCountryList(List<Country> countries) {
        StringBuilder sb = new StringBuilder();
        for (Country i : countries) {
            sb.append(i).append("<br>");
        }
        return sb.toString();
    }
}
