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

package org.apache.isis.core.runtime.persistence.objectstore.transaction;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.apache.isis.core.metamodel.services.ServicesInjectorDefault;
import org.apache.isis.core.runtime.system.persistence.PersistenceSession;
import org.apache.isis.core.runtime.system.transaction.IsisTransaction;
import org.apache.isis.core.runtime.system.transaction.IsisTransactionManager;
import org.apache.isis.core.unittestsupport.jmock.auto.Mock;
import org.apache.isis.core.unittestsupport.jmocking.JUnitRuleMockery2;
import org.apache.isis.core.unittestsupport.jmocking.JUnitRuleMockery2.Mode;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ObjectStoreTransactionManager_StartTransactionTest {

    @Rule
    public JUnitRuleMockery2 context = JUnitRuleMockery2.createFor(Mode.INTERFACES_AND_CLASSES);

    
    @Mock
    private PersistenceSession mockPersistenceSession;
    @Mock
    private TransactionalResource mockObjectStore;


    private IsisTransactionManager transactionManager;

    @Before
    public void setUpTransactionManager() throws Exception {
        transactionManager = new IsisTransactionManager(mockPersistenceSession, mockObjectStore, new ServicesInjectorDefault());
    }

    @Before
    public void setUpExpectations() throws Exception {
        context.ignoring(mockPersistenceSession);
    }

    @Test
    public void startTransactionCreateTransactionIfNone() throws Exception {
        context.ignoring(mockObjectStore);

        assertThat(transactionManager.getTransaction(), is(nullValue()));
        transactionManager.startTransaction();
        assertThat(transactionManager.getTransaction(), is(not(nullValue())));
    }

    @Test
    public void startTransactionDoesNotOverwriteTransactionIfHasOne() throws Exception {
        context.ignoring(mockObjectStore);

        // cause a transaction to be created
        transactionManager.startTransaction();
        final IsisTransaction transactionAfterFirstStart = transactionManager.getTransaction();

        transactionManager.startTransaction();

        assertThat(transactionManager.getTransaction(), is(sameInstance(transactionAfterFirstStart)));
    }

    @Test
    public void startTransactionIncrementsTransactionLevel() throws Exception {
        context.ignoring(mockObjectStore);

        assertThat(transactionManager.getTransactionLevel(), is(0));
        transactionManager.startTransaction();
        assertThat(transactionManager.getTransactionLevel(), is(1));
    }

    @Test
    public void startTransactionCallsStartTransactionOnObjectStore() throws Exception {
        context.checking(new Expectations() {
            {
                one(mockObjectStore).startTransaction();
            }
        });

        transactionManager.startTransaction();
    }

}
