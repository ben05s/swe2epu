package at.epu.test.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import at.epu.test.DataBindingTests;
import at.epu.test.MockDataTests;

@RunWith(Suite.class)
@SuiteClasses({ DataBindingTests.class, MockDataTests.class, DataObjectTest.class })
public class Milestone2Tests {

}
