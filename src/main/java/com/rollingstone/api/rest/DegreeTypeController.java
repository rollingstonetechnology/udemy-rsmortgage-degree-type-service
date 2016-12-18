package com.rollingstone.api.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rollingstone.domain.DegreeType;
import com.rollingstone.exception.HTTP400Exception;
import com.rollingstone.service.RsMortgageDegreeTypeService;
/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/rsmortgage-degreetype-service/v1/degreeType")
public class DegreeTypeController extends AbstractRestController {

    @Autowired
    private RsMortgageDegreeTypeService degreeTypeService;
  
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createDegreeType(@RequestBody DegreeType degreeType,
                                 HttpServletRequest request, HttpServletResponse response) {
    	DegreeType createdDegreeType = this.degreeTypeService.createDegreeType(degreeType);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdDegreeType.getId()).toString());
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Page<DegreeType> getAllDegreeTypesByPage(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.degreeTypeService.getAllDegreeTypesByPage(page, size);
    }
    
    @RequestMapping(value = "/all",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    List<DegreeType> getAllDegreeTypes(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.degreeTypeService.getAllDegreeTypes();
    }
    
    @RequestMapping("/simple/{id}")
	public DegreeType getSimpleDegreeType(@PathVariable("id") Long id) {
    	DegreeType degreeType = this.degreeTypeService.getDegreeType(id);
         checkResourceFound(degreeType);
         return degreeType;
	}

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    DegreeType getDegreeType(@PathVariable("id") Long id,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
    	DegreeType degreeType = this.degreeTypeService.getDegreeType(id);
        checkResourceFound(degreeType);
        return degreeType;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDegreeType(@PathVariable("id") Long id, @RequestBody DegreeType degreeType,
                                 HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.degreeTypeService.getDegreeType(id));
        if (id != degreeType.getId()) throw new HTTP400Exception("ID doesn't match!");
        this.degreeTypeService.updateDegreeType(degreeType);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDegreeType(@PathVariable("id") Long id, HttpServletRequest request,
                                 HttpServletResponse response) {
        checkResourceFound(this.degreeTypeService.getDegreeType(id));
        this.degreeTypeService.deleteDegreeType(id);
    }
}
