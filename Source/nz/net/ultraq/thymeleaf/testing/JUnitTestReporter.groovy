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
import org.thymeleaf.testing.templateengine.testable.ITestIterator
import org.thymeleaf.testing.templateengine.testable.ITestParallelizer
import org.thymeleaf.testing.templateengine.testable.ITestResult
import org.thymeleaf.testing.templateengine.testable.ITestSequence

/**
 * Provides access to the last test result so it can be interrogated by the test
 * executor.  Just wraps the {@link ConsoleTestReporter} otherwise.
 * 
 * @author Emanuel Rabina
 */
class JUnitTestReporter implements ITestReporter {

	private final ConsoleTestReporter testReporter = new ConsoleTestReporter()
	private ITestResult lastResult

	/**
	 * Return the result of the last test execution.
	 * 
	 * @return The last test result.
	 */
	ITestResult getLastResult() {

		return lastResult
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void iterationEnd(String executionId, int nestingLevel, ITestIterator iterator,
		int iterationNumber, int okTests, int totalTests, long executionTimeNanos) {

		testReporter.iterationEnd(executionId, nestingLevel, iterator, iterationNumber,
				okTests, totalTests, executionTimeNanos)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void iterationStart(String executionId, int nestingLevel, ITestIterator iterator,
		int iterationNumber) {

		testReporter.iterationStart(executionId, nestingLevel, iterator, iterationNumber)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void iteratorEnd(String executionId, int nestingLevel, ITestIterator iterator,
		int okTests, int totalTests, long executionTimeNanos) {

		testReporter.iteratorEnd(executionId, nestingLevel, iterator, okTests, totalTests,
				executionTimeNanos)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void iteratorStart(String executionId, int nestingLevel, ITestIterator iterator) {

		testReporter.iteratorStart(executionId, nestingLevel, iterator)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void parallelizerEnd(String executionId, int nestingLevel, ITestParallelizer parallelizer,
		int okTests, int totalTests, long executionTimeNanos) {

		testReporter.parallelizerEnd(executionId, nestingLevel, parallelizer, okTests, totalTests,
				executionTimeNanos)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void parallelizerStart(String executionId, int nestingLevel, ITestParallelizer parallelizer) {

		testReporter.parallelizerStart(executionId, nestingLevel, parallelizer)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void parallelThreadEnd(String executionId, int nestingLevel, ITestParallelizer parallelizer,
		int threadNumber, int okTests, int totalTests, long executionTimeNanos) {

		testReporter.parallelThreadEnd(executionId, nestingLevel, parallelizer, threadNumber, okTests,
				totalTests, executionTimeNanos)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void parallelThreadStart(String executionId, int nestingLevel, ITestParallelizer parallelizer,
		int threadNumber) {

		testReporter.parallelThreadStart(executionId, nestingLevel, parallelizer, threadNumber)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void sequenceEnd(String executionId, int nestingLevel, ITestSequence sequence,
		int okTests, int totalTests, long executionTimeNanos) {

		testReporter.sequenceEnd(executionId, nestingLevel, sequence, okTests, totalTests,
				executionTimeNanos)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void sequenceStart(String executionId, int nestingLevel, ITestSequence sequence) {

		testReporter.sequenceStart(executionId, nestingLevel, sequence)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void executionStart(String executionId) {

		testReporter.executionStart(executionId)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void executionEnd(String executionId, int okTests, int totalTests, long executionTimeNanos) {

		testReporter.executionEnd(executionId, okTests, totalTests, executionTimeNanos)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void testStart(String executionId, int nestingLevel, ITest test, String testName) {

		testReporter.testStart(executionId, nestingLevel, test, testName)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void testEnd(String executionId, int nestingLevel, ITest test, String testName, ITestResult result, long executionTimeNanos) {

		lastResult = result
		testReporter.testEnd(executionId, nestingLevel, test, testName, result, executionTimeNanos)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	boolean isAllOK() {

		return testReporter.isAllOK()
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	long getTotalExecutionTimeMs() {

		return testReporter.getTotalExecutionTimeMs()
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	Set<String> getAllTestNames() {

		return testReporter.getAllTestNames()
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	ITestResult getResultByTestName(String testName) {

		return testReporter.getResultByTestName(testName)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	long getExecutionTimeMsByTestName(String testName) {

		return testReporter.getExecutionTimeMsByTestName(testName)
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	void reset() {

		testReporter.reset()
	}
}
