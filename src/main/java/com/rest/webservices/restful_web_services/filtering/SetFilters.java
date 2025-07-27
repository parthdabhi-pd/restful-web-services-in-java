package com.rest.webservices.restful_web_services.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

public class SetFilters {

    public SetFilters () {}

    public static MappingJacksonValue getMappingJacksonValueObject (Object object, String... filterOutAllExcept) {

        // Dynamic Filtering
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(object);

        // helper to create filters
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(filterOutAllExcept);
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        // set the filters
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
}
