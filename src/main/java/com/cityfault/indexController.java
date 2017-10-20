package com.cityfault;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Bartek on 2017-10-16.
 */
@RestController
public class indexController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public ArrayList<Greeting> greeting() {
        ArrayList<Greeting> list = new ArrayList<Greeting>();
        list.add(new Greeting(counter.incrementAndGet(),
                String.format("%s", "Hello Bartek")));
        list.add(new Greeting(counter.incrementAndGet(),
                String.format("%s", "Hello Android")));
        return list;
    }
}
