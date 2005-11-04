package test.org.nakedobjects.object.persistence.defaults;

import org.nakedobjects.object.persistence.SerialOid;

import junit.framework.TestCase;

public class SerialOidTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(SerialOidTest.class);
    }
    
    public void testEquals() {
        SerialOid oid1 = new SerialOid(123);
        SerialOid oid2 = new SerialOid(123);
        SerialOid oid3 = new SerialOid(321);
        
        assertTrue(oid1.equals(oid2));
        assertTrue(oid2.equals(oid1));
        
        assertFalse(oid1.equals(oid3));
        assertFalse(oid3.equals(oid1));
    }
    
    public void testHashCode() {
        SerialOid oid1 = new SerialOid(123);
        SerialOid oid2 = new SerialOid(123);
        SerialOid oid3 = new SerialOid(321);
        
        assertEquals(oid1.hashCode(), oid2.hashCode());
        assertFalse(oid1.hashCode() == oid3.hashCode());
    }
    
    public void testStringAsHex() {
        assertEquals("OID#7B", new SerialOid(123).toString());
    }
    

}


/*
Naked Objects - a framework that exposes behaviourally complete
business objects directly to the user.
Copyright (C) 2000 - 2004  Naked Objects Group Ltd

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