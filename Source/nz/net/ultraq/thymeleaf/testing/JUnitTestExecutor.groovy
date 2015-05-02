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
import org.reflections.Reflections
import org.reflections.scanners.ResourcesScanner
import org.thymeleaf.dialect.IDialect
import org.thymeleaf.testing.templateengine.engine.TestExecutor
import static org.junit.Assert.assertTrue

/**
 * A barebones Thymeleaf test file executor, requires that subclasses specify
 * their own <tt>@Parameters</tt> annotated method that returns the list of
 * Thymeleaf test files to have executed.
 * 
 * @author Emanuel Rabina
 */
@RunWith(Parameterized.class)
abstract class JUnitTestExecutor {

	protected static Reflections reflections = new Reflections('', new ResourcesScanner())
	private static TestExecutor testExecutor	// TODO: Not executed in parallel... for now

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
				reporter: new JUnitTestReporter()
			)
		}
		return testExecutor
	}
}
