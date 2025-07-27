//                                              "જય શ્રી ગણેશ"

/*
    1. Static Filtering : we write our filtering logic in Bean.(@JsonIgnore)

    2. Dynamic Filtering : for the same Bean, we will have different filtering logic in different REST Api.
*/

package com.rest.webservices.restful_web_services.filtering;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

//    http://localhost:8080/filtering
    @GetMapping("/filtering")
    public MappingJacksonValue filtering() { // only - field1, filed3 - are visible
        var someBean = new SomeBean("value1", "value2", "value3");

        // Dynamic Filtering
        return SetFilters.getMappingJacksonValueObject(someBean, "field1", "field3");
    }

//    http://localhost:8080/filtering-list
    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList() {// only - filed2 - are visible
        List<SomeBean> someBeanList = Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6"));
        // Dynamic Filtering - Here SetFilters is CUSTOM class, check it d.filtering logic!
        return SetFilters.getMappingJacksonValueObject(someBeanList, "field2"); // return MappingJacksonValue
    }

}
