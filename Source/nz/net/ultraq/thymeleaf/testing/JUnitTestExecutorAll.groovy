/* 
 * Copyright 2015, Emanuel Rabina (http://www.ultraq.net.nz/)
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

package nz.net.ultraq.thymeleaf.testing

import org.junit.runners.Parameterized.Parameters
import org.reflections.Reflections
import org.reflections.scanners.ResourcesScanner

/**
 * A Thymeleaf test file executor that runs every Thymeleaf testing file in the
 * classpath.
 * 
 * @author Emanuel Rabina
 */
abstract class JUnitTestExecutorAll extends JUnitTestExecutor {

	private static Reflections reflections = new Reflections('', new ResourcesScanner())

	/**
	 * Get all the <tt>.thtest</tt> files in the project classpath.
	 * 
	 * @return List of all the Thymeleaf testing files.
	 * @throws URISyntaxException
	 */
	@Parameters(name = "{0}")
	static List<String> listAllThymeleafTestFiles() throws URISyntaxException {

		return reflections.getResources(~/.+\\.thtest/) as List
	}
}
