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

import org.reflections.Reflections
import org.reflections.scanners.ResourcesScanner

/**
 * A Thymeleaf test file executor that runs every Thymeleaf testing file in the
 * classpath.
 * 
 * @author Emanuel Rabina
 */
@SuppressWarnings('AbstractClassWithoutAbstractMethod')
abstract class JUnitTestExecutorAll extends JUnitTestExecutor {

	/**
	 * Get all the {@code .thtest} files in the project classpath.
	 * 
	 * @return List of all the Thymeleaf testing files.
	 */
	static List<String> getThymeleafTestFiles() {

		return new Reflections('', new ResourcesScanner()).getResources(~/.+\.thtest/) as List
	}
}
