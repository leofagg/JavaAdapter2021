package myTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ listAdapterTest.class, mapAdapterTest.class, setAdapterTest.class, sublistTest.class })
public class TestSuite {

}
