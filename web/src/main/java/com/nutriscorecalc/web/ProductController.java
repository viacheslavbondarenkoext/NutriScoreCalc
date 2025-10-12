package com.nutriscorecalc.web;

import com.nutriscorecalc.database.ProductEntity;
import com.nutriscorecalc.database.ProductRepository;
import com.nutriscorecalc.logic.NutriScoreCalculator;
import com.nutriscorecalc.logic.dto.Product;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {

    private final ProductRepository repo;
    private final NutriScoreCalculator calculator;

    @Inject
    public ProductController(ProductRepository repo, NutriScoreCalculator calculator) {
        this.repo = repo;
        this.calculator = calculator;
    }

    @GET
    public List<Product> list() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        return repo.findById(id)
                .map(e -> Response.ok(toDto(e)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Product dto) {
        ProductEntity entity = toEntity(dto);
        ProductEntity saved = repo.save(entity);
        Product out = toDto(saved);
        out.nutriScore = calculator.calculate(out);
        URI location = URI.create("/api/products/" + saved.id);
        return Response.created(location).entity(out).build();
    }

    private Product toDto(ProductEntity e) {
        Product p = new Product(e.id, e.name, e.energyKj, e.sugar, e.saturatedFat, e.sodium, e.fiber, e.protein, e.fruitsPercentage);
        p.nutriScore = calculator.calculate(p);
        return p;
    }

    private ProductEntity toEntity(Product p) {
        ProductEntity e = new ProductEntity(p.name, p.energyKj, p.sugar, p.saturatedFat, p.sodium, p.fiber, p.protein, p.fruitsPercentage);
        e.id = p.id;
        return e;
    }
}

