// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.daiyida.api.service.resource;

import com.daiyida.api.domain.resource.Resource;
import com.daiyida.api.service.resource.ResourceService;
import java.util.List;

privileged aspect ResourceService_Roo_Service {
    
    public abstract long ResourceService.countAllResources();    
    public abstract void ResourceService.deleteResource(Resource resource);    
    public abstract Resource ResourceService.findResource(String id);    
    public abstract List<Resource> ResourceService.findAllResources();    
    public abstract List<Resource> ResourceService.findResourceEntries(int firstResult, int maxResults);    
    public abstract void ResourceService.saveResource(Resource resource);    
    public abstract Resource ResourceService.updateResource(Resource resource);    
}
