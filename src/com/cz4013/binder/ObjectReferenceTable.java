package com.cz4013.binder;

import java.util.HashMap;

/**
 * Created by danielseetoh on 4/1/17.
 * This is the table for the server to register the remote object references.
 * The client will retrieve the remote object references based on the names of the classes.
 */
public class ObjectReferenceTable {

    private HashMap<String, String> objectReferenceHashMap;

    public ObjectReferenceTable () {
        this.objectReferenceHashMap = new HashMap<String, String>();
    }

    /**
     * Add a name and object reference into the hashmap. If name already exists, will replace object. This is in case the server restarts.
     * The object reference should be in the form of "IPAddress,PORT,ObjectHashCode"
     * @param name
     * @param remoteObjRef
     * @return
     * @throws NameExistsException
     */
    public String addObjectReference(String name, String remoteObjRef) throws NameExistsException{
        if (this.objectReferenceHashMap.containsKey(name)){
            this.objectReferenceHashMap.replace(name, remoteObjRef);
        } else {
            this.objectReferenceHashMap.put(name, remoteObjRef);
        }
        return "Success adding object to reference table.";
    }

    /**
     * Get an object reference from the name. If it does not exist, throw a NameDoesNotExistException.
     * @param name
     * @return
     * @throws NameDoesNotExistException
     */
    public String getObjectReference(String name) throws NameDoesNotExistException{
        if (this.objectReferenceHashMap.containsKey(name)) {
            return "Success " + this.objectReferenceHashMap.get(name);
        } else {
            throw new NameDoesNotExistException("Failure Remote Reference Name does not exist");
        }
    }

}
