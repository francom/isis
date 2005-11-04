package test.org.nakedobjects.object.reflect;

import org.nakedobjects.object.MemberIdentifier;
import org.nakedobjects.object.Naked;
import org.nakedobjects.object.NakedObject;
import org.nakedobjects.object.NakedObjectSpecification;
import org.nakedobjects.object.control.Hint;
import org.nakedobjects.object.reflect.OneToOnePeer;
import org.nakedobjects.utility.UnexpectedCallException;

import junit.framework.Assert;


public class TestPojoReferencePeer implements OneToOnePeer {
    public void clearAssociation(MemberIdentifier identifier, NakedObject inObject, NakedObject associate) {}

    public Naked getAssociation(MemberIdentifier identifier, NakedObject inObject) {
        Assert.assertTrue(inObject.getObject()  instanceof TestPojo);

        Object reference = ((TestPojo) inObject).getReference();
        DummyNakedObject nakedObject = new DummyNakedObject();
        nakedObject.setupObject(reference);
        return nakedObject;
    }

    public Object getExtension(Class cls) {
        return null;
    }
    
    public Class[] getExtensions() {
        return new Class[0];
    }


    public Hint getHint(MemberIdentifier identifier, NakedObject object, Naked value) {
        return null;
    }

    public String getName() {
        return null;
    }

    public NakedObjectSpecification getType() {
        return null;
    }

    public boolean hasHint() {
        return false;
    }

    public void initAssociation(MemberIdentifier identifier, NakedObject inObject, NakedObject associate) {
        Assert.assertTrue(inObject.getObject() instanceof TestPojo);

        setAssociation(inObject, associate);
    }

    public void initValue(MemberIdentifier identifier, NakedObject inObject, Object associate) {
        throw new UnexpectedCallException();
    }

    public boolean isDerived() {
        return false;
    }

    public boolean isMandatory() {
        return false;
    }
    
    public boolean isEmpty(MemberIdentifier identifier, NakedObject inObject) {
        Assert.assertTrue(inObject.getObject()  instanceof TestPojo);
        return false;
    }

    public void setAssociation(MemberIdentifier identifier, NakedObject inObject, NakedObject associate) {
        Assert.assertTrue(inObject.getObject()  instanceof TestPojo);

        setAssociation(inObject, associate);
    }

    public void setValue(MemberIdentifier identifier, NakedObject inObject, Object associate) {
        throw new UnexpectedCallException();
    }

    private void setAssociation(NakedObject inObject, NakedObject associate) {
        ((TestPojo) inObject.getObject()).setReference(associate.getObject());
    }

    public boolean isObject() {
        return false;
    }
}

/*
 * Naked Objects - a framework that exposes behaviourally complete business objects directly to the
 * user. Copyright (C) 2000 - 2005 Naked Objects Group Ltd
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if
 * not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307 USA
 * 
 * The authors can be contacted via www.nakedobjects.org (the registered address of Naked Objects
 * Group is Kingsway House, 123 Goldworth Road, Woking GU21 1NR, UK).
 */