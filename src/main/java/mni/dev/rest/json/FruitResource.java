package mni.dev.rest.json;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Set;

import java.math.BigInteger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import mni.dev.model.Fruit;

@Path("/api/fruits")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FruitResource {

    private final Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    public FruitResource(){
        fruits.add(new Fruit(new BigInteger("1"), "Apple", "Winter Fruit"));
    }

    @GET
    @Path("/getDetailFruit/{id}")
    public Fruit getDetailFruit(@PathParam("id") BigInteger id){
        Optional<Fruit> any = fruits.stream().filter(fruit -> fruit.getId().compareTo(id) == 0).findAny();
        return any.orElse(null);
    }

    @GET
    @Path("/getFruits")
    public Set<Fruit> getFruits(){
        return fruits;
    }

    @POST
    @Path("/addFruit")
    public Set<Fruit> add(Fruit fruit) {
        fruits.add(fruit);
        return fruits;
    }

    @DELETE
    @Path("/deleteFruit/{id}")
    public Set<Fruit> delete(@PathParam("id") BigInteger id) {
        fruits.removeIf(existingFruit -> existingFruit.getId().compareTo(id) == 0);
        return fruits;
    }
}