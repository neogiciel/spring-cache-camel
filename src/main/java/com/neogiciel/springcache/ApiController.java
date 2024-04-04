package com.neogiciel.springcache;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.metrics.StartupStep.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neogiciel.springcache.util.Trace;

@RestController
@RequestMapping("/cache")
public class ApiController {
    
    // Auto-wiring the CacheManager within your service
    //@Autowired
    private CacheManager cacheManager;
  
    /*
     * test
    */
    @GetMapping(value = "/test",produces="application/json") 
    //@Cacheable(value = "myCache", key = "'api-test'")
    @Cacheable(value = "test")
    public String test() throws InterruptedException {
        Trace.info("Appel REST test cache");
     
         //the current time in milliseconds
         long start = System.currentTimeMillis();
        // stop the main thread of the program for 5000 milliseconds (5 seconds)
        Thread.sleep(5000);
        Trace.info("The thread is paused for " + (System.currentTimeMillis() - start) + " milliseconds");


     
        return getJSON("test", "test").toString();
        //return Map.of("test", "test");
    }

    /*
     * test
    */
    @GetMapping(value = "/invalidate",produces="application/json") 
    //@CacheEvict(value = "myCache", allEntries = true)
    public String invalidate() {
        Trace.info("Appel REST invalidate cache");
        refreshCache();
        return getJSON("invalidate", "ok").toString();
        //return Map.of("test", "test");
    }


     /*
      * getJSON
      */
     public JSONObject getJSON(String value,String key){
        JSONObject obj = new JSONObject();
        obj.put(value, key);
        return obj;
     }

    /*
     * refreshCache
     */
    public void refreshCache() {

        Cache cache = cacheManager.getCache("myCache");
        if (cache != null) {
            cache.clear(); // Clears all entries from the cache, effectively refreshing it
        }

    }

    
  

}
