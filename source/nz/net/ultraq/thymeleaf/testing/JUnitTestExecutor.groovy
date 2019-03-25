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
import org.thymeleaf.dialect.IDialect
import org.thymeleaf.testing.templateengine.engine.TestExecutor
import org.thymeleaf.testing.templateengine.report.ConsoleTestReporter
import org.thymeleaf.testing.templateengine.report.ITestReporter
import static org.junit.Assert.*

/**
 * A barebones Thymeleaf test file executor, requires that subclasses specify
 * their own {@link Parameters} annotated method that returns the list of
 * Thymeleaf test files to have executed.
 * 
 * @author Emanuel Rabina
 */
@RunWith(Parameterized)
abstract class JUnitTestExecutor {

	@Lazy
	@SuppressWarnings('PrivateFieldCouldBeFinal')
	private TestExecutor testExecutor = {
		return new TestExecutor(
			dialects: testDialects,
			reporter: new JUnitTestReporter(testReporter)
		)
	} ()

	@Parameter
	String testPath

	/**
	 * Run the Thymeleaf test executor over the test file, asserting the result
	 * was OK (execution result matched the expected output).
	 */
	@Test
	void executeThymeleafTestFile() {

		testExecutor.execute("classpath:${testPath}")
		assertTrue(testExecutor.reporter.lastResult.isOK())
	}

	/**
	 * Return a list of additional dialects to use in testing.  The Thymeleaf
	 * Standard dialect is always included.
	 * 
	 * @return List of additional dialects to test with.
	 */
	protected abstract List<? extends IDialect> getTestDialects()

	/**
	 * Extension point for providing a custom reporter to act as delegate to this
	 * library's reporter which tracks test executions as a single test.  Uses a
	 * {@link ConsoleTestReporter} if not overridden.
	 * 
	 * @return Test reporter to use in execution.
	 */
	protected ITestReporter getTestReporter() {

		return new ConsoleTestReporter()
	}
}
