package org.indusbc.ejb;

import jakarta.ejb.Local;
import org.indusbc.model.Admin;

/**
 *
 * @author root
 */
@Local
public interface AdminEjbLocal {
    
    public Admin createAdmin(Admin admin);
    public Admin findAdmin();
    public Admin changeAdminPassword(Admin admin);
    
}
