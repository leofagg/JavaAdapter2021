package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestSuite.class);
        for (Failure failure : result.getFailures())
            System.out.println(failure.toString());
        if (result.wasSuccessful())
            System.out.println("Il test ha dato esito positivo :)");
        else
            System.out.println("Il test ha dato esito negativo.");
        if (result.getFailureCount() > 1)
            System.out.println("Sono falliti " + result.getFailureCount() + " test.");
        if (result.getFailureCount() == 1)
            System.out.println("È fallito 1 test.");
        System.out.println("Sono stati eseguiti: " + result.getRunCount() + " test.");
    }
}