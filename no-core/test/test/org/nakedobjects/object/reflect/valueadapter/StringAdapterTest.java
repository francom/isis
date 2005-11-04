package test.org.nakedobjects.object.reflect.valueadapter;

import org.nakedobjects.object.reflect.valueadapter.StringAdapter;

import junit.framework.TestCase;


public class StringAdapterTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(StringAdapterTest.class);
    }

    private StringAdapter fpv;

    protected void setUp() throws Exception {
        fpv = new StringAdapter("text entry");
    }

    public void testStringValue() {
        assertEquals("text entry", fpv.stringValue());
    }

    public void testLengths() {
        assertEquals(0, fpv.getMaximumLength());
        assertEquals(0, fpv.getMinumumLength());
    }

    public void testOutputAsString() {
        assertEquals("text entry", fpv.titleString());
    }

    public void testParse() throws Exception {
        fpv.parseTextEntry("tRUe");
        assertEquals("tRUe", fpv.stringValue());

        fpv.parseTextEntry("fALse");
        assertEquals("fALse", fpv.stringValue());
    }
}

/*
 * Naked Objects - a framework that exposes behaviourally complete business
 * objects directly to the user. Copyright (C) 2000 - 2005 Naked Objects Group
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