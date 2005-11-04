package org.nakedobjects.object;

import org.nakedobjects.utility.Assert;
import org.nakedobjects.utility.DebugInfo;
import org.nakedobjects.utility.DebugString;
import org.nakedobjects.utility.NakedObjectConfiguration;
import org.nakedobjects.utility.NakedObjectRuntimeException;

import org.apache.log4j.Logger;

/**
 * A repository of all the NOF components that are shared by a running system
 */
public abstract class NakedObjects implements NakedObjectsComponent, DebugInfo {
    private static final Logger LOG = Logger.getLogger(NakedObjects.class);
    private static NakedObjects singleton;

    public static DebugInfo debug() {
        return getInstance();
    }

    /**
     * Returns the configuration component.
     */
    public static NakedObjectConfiguration getConfiguration() {
        return getInstance().configuration();
    }

    /**
     * Returns the current session.
     */
    public static Session getCurrentSession() {
        return getInstance().currentSession();
    }

    protected static NakedObjects getInstance() {
        return singleton;
    }

    /**
     * Return the object loader.
     */
    public static NakedObjectLoader getObjectLoader() {
        return getInstance().objectLoader();
    }

    /**
     * Returns the object manager.
     */
    public static NakedObjectPersistenceManager getPersistenceManager() {
        return getInstance().objectManager();
    }

    /*
     * Return the specification loader. 
     */
    public static NakedObjectSpecificationLoader getSpecificationLoader() {
        return getInstance().specificationLoader();
    }

    public static void reset() {
        singleton = null;
    }

    public void shutdown() {
        LOG.info("shutting down " + getInstance());
        objectManager().shutdown();
        objectLoader().shutdown();
        specificationLoader().shutdown();
        clearReferences();
    }

    protected NakedObjects() {
        // TODO remove once XAT test framework is updated
        reset();
        if (singleton != null) {
            throw new NakedObjectRuntimeException("Naked Objects Repository already set up");
        }
        singleton = this;
    }

    private void clearReferences() {
        setPersistenceManager(null);
        setObjectLoader(null);
        setSpecificationLoader(null);
        setConfiguration(null);
        setSession(null);
    }

    protected abstract NakedObjectConfiguration configuration();

    protected abstract Session currentSession();

    public String getDebugData() {
        DebugString debug = new DebugString();

        debug.appendln(0, "configuration", configuration());

        debug.appendln(0, "session", currentSession());

        debug.appendTitle("specification loader");
        NakedObjectSpecificationLoader loader = specificationLoader();
        debug.appendln(0, "instance", loader);
        NakedObjectSpecification[] specs = loader.allSpecifications();
        for (int i = 0; i < specs.length; i++) {
            debug.appendln(4, i + ")", specs[i].toString());
        }

        debug.append(objectManager());

        return debug.toString();
    }

    public void init() {
        LOG.info("initialising " + this);
        Assert.assertNotNull("no object manager set up", getPersistenceManager());
        Assert.assertNotNull("no configuration set up", getConfiguration());
        Assert.assertNotNull("no object loader set up", getObjectLoader());
        Assert.assertNotNull("no specification loader set up", getSpecificationLoader());

        getSpecificationLoader().init();
        getObjectLoader().init();
        getPersistenceManager().init();
    }

    protected abstract NakedObjectLoader objectLoader();

    protected abstract NakedObjectPersistenceManager objectManager();

    public abstract void setConfiguration(NakedObjectConfiguration configuration);

    public abstract void setObjectLoader(NakedObjectLoader loader);

    public abstract void setPersistenceManager(NakedObjectPersistenceManager objectManager);

    public abstract void setSession(Session session);

    public abstract void setSpecificationLoader(NakedObjectSpecificationLoader loader);

    protected abstract NakedObjectSpecificationLoader specificationLoader();

}

/*
 * Naked Objects - a framework that exposes behaviourally complete business
 * objects directly to the user. Copyright (C) 2000 - 2004 Naked Objects Group
 * Ltd
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * The authors can be contacted via www.nakedobjects.org (the registered address
 * of Naked Objects Group is Kingsway House, 123 Goldworth Road, Woking GU21
 * 1NR, UK).
 */