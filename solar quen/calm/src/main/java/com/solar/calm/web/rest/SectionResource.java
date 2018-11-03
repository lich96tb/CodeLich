package com.solar.calm.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.solar.calm.domain.Section;
import com.solar.calm.enums.ErrorType;
import com.solar.calm.repository.SectionRepository;
import com.solar.calm.repository.predicate.SectionPredicate;
import com.solar.calm.web.rest.errors.BadRequestAlertException;
import com.solar.calm.web.rest.param.SectionParams;
import com.solar.calm.web.rest.util.*;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Section.
 */
@RestController
@RequestMapping("/api")
public class SectionResource {

    private final Logger log = LoggerFactory.getLogger(SectionResource.class);

    private static final String ENTITY_NAME = "section";

    private final SectionRepository sectionRepository;
    private final ResultHelper resultHelper;
    private final Transformer transformer;

    public SectionResource(SectionRepository sectionRepository, ResultHelper resultHelper, Transformer transformer) {
        this.sectionRepository = sectionRepository;
        this.resultHelper = resultHelper;
        this.transformer = transformer;
    }

    /**
     * POST  /sections : Create a new section.
     *
     * @param section the section to create
     * @return the ResponseEntity with status 201 (Created) and with body the new section, or with status 400 (Bad Request) if the section has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sections")
    @Timed
    public ResponseEntity<Section> createSection(@RequestBody Section section) throws URISyntaxException {
        log.debug("REST request to save Section : {}", section);
        if (section.getId() != null) {
            throw new BadRequestAlertException("A new section cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Section result = sectionRepository.save(section);
        return ResponseEntity.created(new URI("/api/sections/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sections : Updates an existing section.
     *
     * @param section the section to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated section,
     * or with status 400 (Bad Request) if the section is not valid,
     * or with status 500 (Internal Server Error) if the section couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sections")
    @Timed
    public ResponseEntity<Section> updateSection(@RequestBody Section section) throws URISyntaxException {
        log.debug("REST request to update Section : {}", section);
        if (section.getId() == null) {
            return createSection(section);
        }
        Section result = sectionRepository.save(section);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, section.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sections : get all the sections.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sections in body
     */
    @GetMapping("/sections")
    @Timed
    public ResponseEntity<?> getAllSections(SectionParams params) {
        log.debug("REST request to get a page of Sections");
        try {
            List<Section> sites = new ArrayList<>();
            if (params.getPageNo() == null) {
                sites = (List<Section>) sectionRepository.findAll(SectionPredicate.filter(params), QueryHelper.getSort(params.getSortBy()));
                return new ResponseEntity<>(transformer.poPage2VO(sites, QueryHelper.getPageRequest(params),
                    Long.valueOf(sites.size())), HttpStatus.OK);
            }
            Page<Section> rawDataPage = sectionRepository.findAll(SectionPredicate.filter(params),
                QueryHelper.getPageRequest(params));
            return new ResponseEntity<>(transformer.poPage2VO(rawDataPage.getContent(),
                QueryHelper.getPageRequest(params), rawDataPage.getTotalElements()), HttpStatus.OK);
        } catch (Exception e) {
            return resultHelper.errorResp(log, e, ErrorType.UNKNOWN, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * GET  /sections/:id : get the "id" section.
     *
     * @param id the id of the section to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the section, or with status 404 (Not Found)
     */
    @GetMapping("/sections/{id}")
    @Timed
    public ResponseEntity<Section> getSection(@PathVariable String id) {
        log.debug("REST request to get Section : {}", id);
        Section section = sectionRepository.findById(id).orElse(null);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(section));
    }

    /**
     * DELETE  /sections/:id : delete the "id" section.
     *
     * @param id the id of the section to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sections/{id}")
    @Timed
    public ResponseEntity<Void> deleteSection(@PathVariable String id) {
        log.debug("REST request to delete Section : {}", id);
        sectionRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
