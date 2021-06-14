package com.example.petclinic.services.map;

import com.example.petclinic.model.Visit;
import com.example.petclinic.services.VisitService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VisitServiceMap extends AbstractMapService<Visit, Long> implements VisitService {
    @Override
    public Visit save(Visit visit) {
        if(visit.getPet()==null||visit.getPet().getOwner()==null||visit.getPet().getPetType()==null||visit.getPet().getId()==null
                ||visit.getPet().getOwner().getId()==null){
            throw new RuntimeException("pet is missing data");
        }
        return super.save(visit);
    }

    @Override
    public Visit findById(Long id)  {
        return super.findById(id);
    }

    @Override
    public Set<Visit> findAll()  {
        return super.findAll();
    }

    @Override
    public void deleteById(Long aLong)  {
        super.deleteByID(aLong);
    }

    @Override
    public void delete(Visit object) {

        super.delete(object);
    }
}
