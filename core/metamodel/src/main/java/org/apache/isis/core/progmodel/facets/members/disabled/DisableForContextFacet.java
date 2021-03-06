/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.isis.core.progmodel.facets.members.disabled;

import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.core.metamodel.facetapi.Facet;
import org.apache.isis.core.metamodel.interactions.DisablingInteractionAdvisor;

/**
 * Disable a property, collection or action based on the state of the target
 * {@link ObjectAdapter object}.
 * 
 * <p>
 * In the standard Apache Isis Programming Model, corresponds to invoking the
 * <tt>disableXxx</tt> support method for the member.
 */
public interface DisableForContextFacet extends Facet, DisablingInteractionAdvisor {

    /**
     * The reason this object is disabled, or <tt>null</tt> otherwise.
     */
    public String disabledReason(ObjectAdapter object);

}
