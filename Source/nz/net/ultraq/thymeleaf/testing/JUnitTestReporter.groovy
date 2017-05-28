/* 
 * Copyright 2015, Emanuel Rabina (http://www.ultraq.net.nz/)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nz.net.ultraq.thymeleaf.testing

import org.thymeleaf.testing.templateengine.report.ConsoleTestReporter
import org.thymeleaf.testing.templateengine.report.ITestReporter
import org.thymeleaf.testing.templateengine.testable.ITest
import org.thymeleaf.testing.templateengine.testable.ITestResult

/**
 * Provides access to the last test result so it can be interrogated by the test
 * executor.  Just wraps the {@link ConsoleTestReporter} otherwise.
 * 
 * @author Emanuel Rabina
 */
class JUnitTestReporter implements ITestReporter {

	/**
	 * The result of the last test execution.
	 */
	ITestResult lastResult

	@Delegate
	private final ITestReporter testReporter

	/**
	 * Constructor, configure this custom reporter to use the given underlying
	 * test reporter as the delegate.
	 * 
	 * @param testReporter
	 */
	JUnitTestReporter(ITestReporter testReporter) {

		this.testReporter = testReporter
	}

	@Override
	void testEnd(String executionId, int nestingLevel, ITest test, String testName, ITestResult result, long executionTimeNanos) {

		lastResult = result
		testReporter.testEnd(executionId, nestingLevel, test, testName, result, executionTimeNanos)
	}
}
