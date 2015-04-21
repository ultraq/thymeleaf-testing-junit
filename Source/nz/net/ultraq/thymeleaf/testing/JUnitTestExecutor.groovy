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

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter
import org.junit.runners.Parameterized.Parameters
import org.reflections.Reflections
import org.reflections.scanners.ResourcesScanner
import org.thymeleaf.dialect.IDialect
import org.thymeleaf.testing.templateengine.engine.TestExecutor
import org.thymeleaf.testing.templateengine.report.ConsoleTestReporter
import static org.junit.Assert.assertTrue

import java.util.regex.Pattern

/**
 * A parameterized JUnit test class that is run over every Thymeleaf testing
 * file (.thtest) in the test directory.
 * 
 * @author Emanuel Rabina
 */
@RunWith(Parameterized.class)
abstract class JUnitTestExecutor {

	// TODO: Not executed in parallel... for now
	private static TestExecutor testExecutor

	static {
		// Modify the ConsoleTestReporter to make it more useful for JUnit
		ConsoleTestReporter.metaClass {
			lastResult = null
			testEnd = { executionId, nestingLevel, test, testName, result, executionTimeNanos ->
				lastResult = result
				delegate.testEnd(executionId, nestingLevel, test, testName, result, executionTimeNanos)
			}
		}
	}

	@Parameter
	public String testPath

	/**
	 * Run the Thymeleaf test executor over the test file, asserting the result
	 * was OK (execution result matched the expected output).
	 */
	@Test
	void executeThymeleafTestFile() {

		def testExecutor = getTestExecutor()
		testExecutor.execute("classpath:${testPath}")
		assertTrue(testExecutor.reporter.lastResult.isOK())
	}

	/**
	 * Get all the <tt>.thtest</tt> files in the project classpath.
	 *
	 * @return List of all the Thymeleaf testing files.
	 * @throws URISyntaxException
	 */
	@Parameters(name = "{0}")
	static List<String> findThymeleafTestFiles() throws URISyntaxException {

		return new Reflections('', new ResourcesScanner()).getResources(Pattern.compile('.+\\.thtest')) as List
	}

	/**
	 * Return a list of additional dialects to use in testing.  The Thymeleaf
	 * Standard dialect is always included.
	 * 
	 * @return List of additional dialects to test with.
	 */
	protected abstract List<? extends IDialect> getTestDialects()

	/**
	 * Returns the test executor for running the Thymeleaf tests, creating one
	 * if it doesn't already exist (singleton executor).
	 * 
	 * @return A Thymeleaf test executor for use w/ JUnit.
	 */
	private TestExecutor getTestExecutor() {

		if (!testExecutor) {
			testExecutor = new TestExecutor(
				dialects: testDialects,
				reporter: new ConsoleTestReporter()
			)
		}
		return testExecutor
	}
}
