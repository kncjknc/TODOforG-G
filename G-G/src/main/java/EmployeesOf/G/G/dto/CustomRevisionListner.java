package EmployeesOf.G.G.dto;

import EmployeesOf.G.G.model.CustomRevisionEntity;
import org.hibernate.envers.RevisionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

public class CustomRevisionListner implements RevisionListener {

    Logger logger = LoggerFactory.getLogger(CustomRevisionListner.class);


    @Override
    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
        customRevisionEntity.setModifierUser(getUsername());
        customRevisionEntity.setDate(new Date());
    }


    private String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication !=null && authentication.isAuthenticated())
            return authentication.getName();
        return "anonymous";
    }

}
