package org.indusbc.ejb;

import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;
import org.indusbc.model.IdentityType;

/**
 *
 * @author root
 */
@Singleton
public class IdentityTypeEjb implements IdentityTypeEjbLocal {
    
    private static final Logger LOGGER=Logger.getLogger(IdentityTypeEjb.class.getName());
    
    @PersistenceContext(name = "indusBCPU")
    EntityManager em;

    public IdentityTypeEjb() {
    }

    @Override
    public List<IdentityType> findAll() {
        TypedQuery<IdentityType> tQ= em.createQuery("select it from IdentityType it", IdentityType.class);
        List<IdentityType> toReturn = tQ.getResultList();
        LOGGER.info(String.format("There are %d IdentityType(s)", toReturn.size()));
        return toReturn;
    }
}
