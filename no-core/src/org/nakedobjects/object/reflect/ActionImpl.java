package org.nakedobjects.object.reflect;

import org.nakedobjects.object.Action;
import org.nakedobjects.object.ActionParameterSet;
import org.nakedobjects.object.Naked;
import org.nakedobjects.object.NakedObject;
import org.nakedobjects.object.NakedObjectSpecification;
import org.nakedobjects.object.NakedObjects;
import org.nakedobjects.object.control.DefaultHint;
import org.nakedobjects.object.control.Hint;

import org.apache.log4j.Logger;


public class ActionImpl extends AbstractNakedObjectMember implements Action {

    private final static Logger LOG = Logger.getLogger(Action.class);
    private ActionPeer reflectiveAdapter;

    public static Action.Type getType(String type) {
        Action.Type[] types = new Action.Type[] { Action.DEBUG, Action.EXPLORATION, Action.USER };
        for (int i = 0; i < types.length; i++) {
            if (types[i].getName().equals(type)) {
                return types[i];
            }
        }
        throw new IllegalArgumentException();
    }

    public ActionImpl(String className, String methodName, ActionPeer actionDelegate) {
        super(methodName, new MemberIdentifierImpl(className, methodName, actionDelegate.parameterTypes()));
        this.reflectiveAdapter = actionDelegate;
    }

    public Naked execute(final NakedObject object, final Naked[] parameters) {
        LOG.debug("execute action " + object + "." + getName());
        Naked[] params = parameters == null ?  new Naked[0] : parameters;
        Naked result = reflectiveAdapter.execute(getIdentifier(), object, params);
        return result;
    }

    public Hint getHint(NakedObject object, Naked[] parameters) {
        if (hasHint()) {
            if (parameters == null) {
                return reflectiveAdapter.getHint(getIdentifier(), object, new NakedObject[0]);
            } else {
                return reflectiveAdapter.getHint(getIdentifier(), object, parameters);
            }
        } else {
            return new DefaultHint(getLabel());
        }
    }

    /**
     * Return a label string that is specified in the About, if there is one, or
     * is derived from method name, if there is no About or its name is set to
     * null.
     */
    public String getLabel(NakedObject object) {
        Hint about = getHint(object, parameterStubs());

        return getLabel(about);
    }

    public int getParameterCount() {
        return reflectiveAdapter.getParameterCount();
    }

    public Action.Type getActionType() {
        return reflectiveAdapter.getType();
    }

    public Action.Target getActionTarget() {
        return reflectiveAdapter.getTarget();
    }

    public Object getExtension(Class cls) {
        return reflectiveAdapter.getExtension(null);
    }

    public boolean hasHint() {
        return reflectiveAdapter.hasHint();
    }

    /**
     * Returns true if the represented action returns something, else returns
     * false.
     */
    public boolean hasReturn() {
        return getReturnType() != null;
    }

    public NakedObjectSpecification[] parameters() {
        return reflectiveAdapter.parameterTypes();
    }

    public Naked[] parameterStubs() {
        Naked[] parameterValues;
        int paramCount = getParameterCount();
        parameterValues = new Naked[paramCount];
        NakedObjectSpecification[] parameters = parameters();
        for (int i = 0; i < paramCount; i++) {
            NakedObjectSpecification parameter = parameters[i];
            if (parameter.isValue()) {
                parameterValues[i] = NakedObjects.getObjectLoader().createValueInstance(parameter);
            } else {
                parameterValues[i] = null;
            }
        }
        return parameterValues;
    }

    public NakedObjectSpecification getReturnType() {
        return reflectiveAdapter.returnType();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Action [");
        sb.append(super.toString());
        sb.append(",type=");
        sb.append(getActionType());
        sb.append(",returns=");
        sb.append(getReturnType());
        sb.append(",parameters={");
        for (int i = 0; i < parameters().length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(parameters()[i]);
        }
        sb.append("}]");
        return sb.toString();
    }

    public ActionParameterSet getParameters(NakedObject object) {
        ActionParameterSet parameters = reflectiveAdapter.getParameters(getIdentifier(), object, parameterStubs());
        if(parameters != null) {
            parameters.checkParameters(getIdentifier().toString(), parameters());
        }
        return parameters;
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
