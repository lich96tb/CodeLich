package com.solar.calm.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.solar.calm.domain.Category;
import com.solar.calm.enums.ErrorType;
import com.solar.calm.repository.CategoryRepository;
import com.solar.calm.repository.predicate.CategoryPredicate;
import com.solar.calm.web.rest.errors.BadRequestAlertException;
import com.solar.calm.web.rest.param.CategoryParams;
import com.solar.calm.web.rest.util.HeaderUtil;
import com.solar.calm.web.rest.util.QueryHelper;
import com.solar.calm.web.rest.util.ResultHelper;
import com.solar.calm.web.rest.util.Transformer;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Category.
 */
@RestController
@RequestMapping("/api")
public class CategoryResource {

    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

    private static final String ENTITY_NAME = "category";

    private final CategoryRepository categoryRepository;
    private final ResultHelper resultHelper;
    private final Transformer transformer;

    public CategoryResource(CategoryRepository categoryRepository, ResultHelper resultHelper, Transformer transformer) {
        this.categoryRepository = categoryRepository;
        this.resultHelper = resultHelper;
        this.transformer = transformer;
    }

    /**
     * POST  /categories : Create a new category.
     *
     * @param category the category to create
     * @return the ResponseEntity with status 201 (Created) and with body the new category, or with status 400 (Bad Request) if the category has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/categories")
    @Timed
    public ResponseEntity<Category> createCategory(@RequestBody Category category) throws URISyntaxException {
        log.debug("REST request to save Category : {}", category);
        if (category.getId() != null) {
            throw new BadRequestAlertException("A new category cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Category result = categoryRepository.save(category);
        return ResponseEntity.created(new URI("/api/categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /categories : Updates an existing category.
     *
     * @param category the category to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated category,
     * or with status 400 (Bad Request) if the category is not valid,
     * or with status 500 (Internal Server Error) if the category couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/categories")
    @Timed
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) throws URISyntaxException {
        log.debug("REST request to update Category : {}", category);
        if (category.getId() == null) {
            return createCategory(category);
        }
        Category result = categoryRepository.save(category);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, category.getId().toString()))
            .body(result);
    }

    /**
     * GET  /categories : get all the categories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of categories in body
     */
    @GetMapping("/categories")
    @Timed
    public ResponseEntity<?> getAllCategories(CategoryParams params) {
        log.debug("REST request to get a page of Categories");
        try {
            List<Category> sites = new ArrayList<>();
            if (params.getPageNo() == null) {
                sites = (List<Category>) categoryRepository.findAll(CategoryPredicate.filter(params), QueryHelper.getSort(params.getSortBy()));
                return new ResponseEntity<>(transformer.poPage2VO(sites, QueryHelper.getPageRequest(params),
                    Long.valueOf(sites.size())), HttpStatus.OK);
            }
            Page<Category> rawDataPage = categoryRepository.findAll(CategoryPredicate.filter(params),
                QueryHelper.getPageRequest(params));
            return new ResponseEntity<>(transformer.poPage2VO(rawDataPage.getContent(),
                QueryHelper.getPageRequest(params), rawDataPage.getTotalElements()), HttpStatus.OK);
        } catch (Exception e) {
            return resultHelper.errorResp(log, e, ErrorType.UNKNOWN, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * GET  /categories/:id : get the "id" category.
     *
     * @param id the id of the category to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the category, or with status 404 (Not Found)
     */
    @GetMapping("/categories/{id}")
    @Timed
    public ResponseEntity<Category> getCategory(@PathVariable String id) {
        log.debug("REST request to get Category : {}", id);
        Category category = categoryRepository.findById(id).orElse(null);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(category));
    }

    /**
     * DELETE  /categories/:id : delete the "id" category.
     *
     * @param id the id of the category to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/categories/{id}")
    @Timed
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        log.debug("REST request to delete Category : {}", id);
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
