package com.rollingstone.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rollingstone.dao.jpa.RsMortgageDegreeTypeRepository;
import com.rollingstone.domain.DegreeType;

/*
 * Service class to do CRUD for User and Address through JPS Repository
 */
@Service
public class RsMortgageDegreeTypeService {

    private static final Logger log = LoggerFactory.getLogger(RsMortgageDegreeTypeService.class);

    @Autowired
    private RsMortgageDegreeTypeRepository degreeTypeRepository;

    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;

    public RsMortgageDegreeTypeService() {
    }

    public DegreeType createDegreeType(DegreeType degreeType) {
   
        return degreeTypeRepository.save(degreeType);
    }

    public DegreeType getDegreeType(long id) {
        return degreeTypeRepository.findOne(id);
    }

    public void updateDegreeType(DegreeType degreeType) {
    	degreeTypeRepository.save(degreeType);
    }

    public void deleteDegreeType(Long id) {
    	degreeTypeRepository.delete(id);
    }

    //http://goo.gl/7fxvVf
    public Page<DegreeType> getAllDegreeTypesByPage(Integer page, Integer size) {
        Page pageOfDegreeTypes = degreeTypeRepository.findAll(new PageRequest(page, size));
        
        // example of adding to the /metrics
        if (size > 50) {
            counterService.increment("com.rollingstone.getAll.largePayload");
        }
        return pageOfDegreeTypes;
    }
    
    public List<DegreeType> getAllDegreeTypes() {
        Iterable<DegreeType> pageOfDegreeTypes = degreeTypeRepository.findAll();
        
        List<DegreeType> degTypes = new ArrayList<DegreeType>();
        
        for (DegreeType degreeTypes : pageOfDegreeTypes){
        	degTypes.add(degreeTypes);
        }
    	log.info("In Real Service getAllDegreeTypes  size :"+degTypes.size());

    	
        return degTypes;
    }
}
