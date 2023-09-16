package org.indusbc.ejb;

import jakarta.ejb.AccessTimeout;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.Local;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import java.util.concurrent.Future;

/**
 *
 * @author root
 */
@Local
public interface AllocationsEjbLocal {
    
    @Asynchronous
    @Lock(LockType.READ)
    @AccessTimeout(-1)
    public Future<String> performAllocations(String allocationJob, String granularity);
    
}
