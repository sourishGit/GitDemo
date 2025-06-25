package sourishBakshiFrameworkAuto.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	int toTry=0;
	int maxTry=1;

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(toTry<maxTry)
		{
			toTry++;
			return true;
		}
		else
		{
			return false;
		}
			
	}

}
