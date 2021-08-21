/* 
 * Copyright 2017, Emanuel Rabina (http://www.ultraq.net.nz/)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License")
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

package nz.net.ultraq.thymeleaf.testing.junit

import org.junit.jupiter.api.Test
import org.thymeleaf.dialect.IDialect
import org.thymeleaf.standard.StandardDialect
import org.thymeleaf.testing.templateengine.report.ConsoleTestReporter
import org.thymeleaf.testing.templateengine.report.ITestReporter

/**
 * Tests for the {@link JUnitTestExecutor} class.
 * 
 * @author Emanuel Rabina
 */
class JUnitTestExecutorTests {

	/**
	 * Test that the executor will execute a Thymeleaf test file.
	 */
	@Test
	void executeTestFile() {

		// Create a text executor that captures STDOUT
		def output = ''

		def testExecutor = new JUnitTestExecutor() {
			List<? extends IDialect> testDialects = [
			  new StandardDialect()
			]
			ITestReporter testReporter = new ConsoleTestReporter() {
				@Override
				protected void output(String line, boolean error) {
					output += line
				}
			}
		}
		testExecutor.executeThymeleafTestFile('nz/net/ultraq/thymeleaf/testing/tests/BasicTestFile.thtest')

		assert output.contains('[EXECUTION:START]')
		assert output.contains('nz/net/ultraq/thymeleaf/testing/tests/BasicTestFile.thtest-001')
		assert output.contains('Test executed OK')
		assert output.contains('[EXECUTION:END]')
	}
}
