package at.epu.test.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import at.epu.test.DataFilterProviderTests;
import at.epu.test.DataObjectFactoryTests;
import at.epu.test.DialogManagerTests;
import at.epu.test.JSONManagerTests;
import at.epu.test.MockDataProviderTests;
import at.epu.test.PDFManagerTests;

@RunWith(Suite.class)
@SuiteClasses({Milestone2Tests.class,
	DataObjectFactoryTests.class,
	JSONManagerTests.class,
	MockDataProviderTests.class,
	PDFManagerTests.class,
	DataFilterProviderTests.class,
	DialogManagerTests.class})
public class BackofficeTests {

}
