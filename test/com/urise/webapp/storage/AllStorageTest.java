package com.urise.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ListStorageTest.class,MapStorageTest.class,
        SortedStorageTest.class, ArrayStorageTest.class})
public class AllStorageTest {
}
