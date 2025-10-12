package com.nutriscorecalc.web;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Minimal Quarkus REST endpoint for NutriScoreCalc web-quarkus module.
 */
@Path("/hello")
public class HelloResource {
    /**
     * Returns a hello message for testing the Quarkus setup.
     * @return Hello message
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus!";
    }
}

