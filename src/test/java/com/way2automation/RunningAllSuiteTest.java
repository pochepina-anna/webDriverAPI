package com.way2automation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Класс <class>RunningAllSuiteTest</class>
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CursorTest.class,
        DragnDropTest.class
})
public class RunningAllSuiteTest {

}

