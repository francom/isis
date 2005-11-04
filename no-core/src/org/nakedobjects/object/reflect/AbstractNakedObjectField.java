package org.nakedobjects.object.reflect;

import org.nakedobjects.object.MemberIdentifier;
import org.nakedobjects.object.Naked;
import org.nakedobjects.object.NakedObject;
import org.nakedobjects.object.NakedObjectField;
import org.nakedobjects.object.NakedObjectSpecification;


public abstract class AbstractNakedObjectField extends AbstractNakedObjectMember implements NakedObjectField {
    private final NakedObjectSpecification specification;

    public AbstractNakedObjectField(String name, NakedObjectSpecification type, MemberIdentifier identifier) {
        super(name, identifier);
        if (type == null) {
            throw new IllegalArgumentException("type cannot be null for " + name);
        }
        this.specification = type;
    }

    public abstract Naked get(NakedObject fromObject);

    /**
     * Return the specification of the object (or objects) that this field holds. For a value are one-to-one
     * reference this will be type that the accessor returns. For a collection it will be the type of element,
     * not the type of collection.
     */
    public NakedObjectSpecification getSpecification() {
        return specification;
    }

    /**
     * Returns true if this field is for a collection
     */
    public boolean isCollection() {
        return false;
    }

    /**
     * Returns true if this field is derived - is calculated from other data in the object - and should
     * therefore not be editable nor persisted.
     */
    // TODO confirm that Value is the only type that can be derived. If so move it?
    public abstract boolean isDerived();

    public abstract boolean isEmpty(NakedObject adapter);

    /**
     * Returns true if this field is for an object, not a collection.
     */
    public boolean isObject() {
        return false;
    }

    /**
     * Returns true if this field is for a value
     */
    public boolean isValue() {
	    return false;
    }

    public boolean isMandatory() {
        return false;
    }

    public abstract Class[] getExtensions();
}

/*
 * Naked Objects - a framework that exposes behaviourally complete business objects directly to the user.
 * Copyright (C) 2000 - 2005 Naked Objects Group Ltd
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General
 * Public License as published by the Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if not, write to
 * the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * The authors can be contacted via www.nakedobjects.org (the registered address of Naked Objects Group is
 * Kingsway House, 123 Goldworth Road, Woking GU21 1NR, UK).
 */