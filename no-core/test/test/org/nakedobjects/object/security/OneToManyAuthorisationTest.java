package test.org.nakedobjects.object.security;

import org.nakedobjects.object.MemberIdentifier;
import org.nakedobjects.object.NakedObject;
import org.nakedobjects.object.control.Hint;
import org.nakedobjects.object.reflect.MemberIdentifierImpl;
import org.nakedobjects.object.repository.NakedObjectsClient;
import org.nakedobjects.object.security.OneToManyAuthorisation;

import junit.framework.TestCase;
import test.org.nakedobjects.object.control.MockHint;
import test.org.nakedobjects.object.reflect.DummyNakedObject;
import test.org.nakedobjects.object.reflect.DummyOneToManyPeer;

public class OneToManyAuthorisationTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(OneToManyAuthorisationTest.class);
    }

    public void testAlwaysHashint() {
        OneToManyAuthorisation oneToOne = new OneToManyAuthorisation(null, null);
        assertTrue(oneToOne.hasHint());
    }
    
    public void testGetHint() {
        new NakedObjectsClient();
        
        DummyOneToManyPeer peer = new DummyOneToManyPeer();
        MockAuthorisationManager manager = new MockAuthorisationManager();
        OneToManyAuthorisation oneToMany = new OneToManyAuthorisation(peer, manager);
        
        NakedObject object = new DummyNakedObject();
        NakedObject associate = new DummyNakedObject();
        MemberIdentifier identifier = new MemberIdentifierImpl("cls", "fld");
        
        peer.expect("getHint " + identifier + " " + object + " " + associate  + " true");
        MockHint hint = new MockHint();
        peer.setupHint(hint);
        manager.setupUsable(true);
        manager.setupVisible(true);

        Hint returnedHint = oneToMany.getHint(identifier, object, associate, true);
        assertEquals(hint, returnedHint);
        
        peer.verify();
    }
}


/*
Naked Objects - a framework that exposes behaviourally complete
business objects directly to the user.
Copyright (C) 2000 - 2005  Naked Objects Group Ltd

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

The authors can be contacted via www.nakedobjects.org (the
registered address of Naked Objects Group is Kingsway House, 123 Goldworth
Road, Woking GU21 1NR, UK).
*/