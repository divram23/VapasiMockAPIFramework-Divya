package Utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("TestCase: "+result.getName()+" started");


    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Testcase Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Testcase Failed");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Testcase ignored");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("TEST EXECUTION STARTED");

    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("TESTING FINISHED");

    }
}
