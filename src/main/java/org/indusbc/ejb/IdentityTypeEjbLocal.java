package org.indusbc.ejb;

import jakarta.ejb.Local;
import java.util.List;
import org.indusbc.model.IdentityType;

/**
 *
 * @author root
 */
@Local
public interface IdentityTypeEjbLocal {
    
    public List<IdentityType> findAll();
    
}
