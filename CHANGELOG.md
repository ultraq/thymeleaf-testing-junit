
Changelog
=========

### 5.2.0
 - Upgrade Groovy to 4.0.6
 - Work with Thymeleaf 3.1.0, using a `JavaxServletTestWebExchangeBuilder` as
   the web exchange implementation which is how Thymeleaf 3.0 would have worked.
   To use a jakarta EE version instead, implement your own `JUnitTestExecutor`,
   and include something like:

   ```groovy
   private JakartaServletTestWebExchangeBuilder webExchangeBuilder = JakartaServletTestWebExchangeBuilder.create()

   @Override
   protected IProcessingContextBuilder getTestProcessingContextBuilder() {
     return new WebProcessingContextBuilder(webExchangeBuilder)
   }
   ```

### 5.1.0
 - Upgrade Groovy to 4.0.4
 - Upgrade Reflections to 0.10.2
 - Updated groovydocs to include links to Thymeleaf and Thymeleaf Testing
   classes when used

### 5.0.0
 - Added an `Automatic-Module-Name` of `nz.net.ultraq.thymeleaf.testing.junit`,
   with classes moved into that package name too.
 - Upgraded Groovy to 4.0.0

### 4.0.0
 - Now uses the JUnit platform to work with JUnit 5.  JUnit 4 still works but
   needs to be updated to use the JUnit platform w/ the "vintage" engine.
   ([#7](https://github.com/ultraq/thymeleaf-testing-junit/issues/7))

### 3.0.1
 - Fix generated POM - Groovy is needed as a compile dependency

### 3.0.0
 - Minimum supported Java version is now Java 8
 - Upgrade Groovy to 3.0.3
 - Added a protected `JUnitTestExecutor.getTestProcessingContextBuilder` method
   that can be overridden to provide a custom implementation, namely the Spring
   ones provided by the Thymeleaf Testing library.

### 2.2.0
 - Update gradle and build scripts
 - Listen to codenarc suggestions to make code more groovy

### 2.1.0
 - Let devs specify the Thymeleaf test reporter to use, defaulting to the
   `ConsoleTestReporter` as in previous versions.
   ([#6](https://github.com/ultraq/thymeleaf-testing-junit/issues/6))

### 2.0.0
 - Support for Thymeleaf 3.0

### 1.2.1
 - Fix double-backslash in 'all test files' regex since moving to Groovy slashy
   strings.

### 1.2.0
 - Remove `reflections` instance from class hierarchy, letting consumers choose
   whatever means they want for resolving Thymeleaf test files
   ([#4](https://github.com/ultraq/thymeleaf-testing-junit/issues/4))

### 1.1.1
 - Fix the sharing of Thymeleaf test executors - tests fail when more than 1
   JUnit test executor was in the mix otherwise.

### 1.1.0
 - Split the test executor into one for finding and running all Thymeleaf test
   files in a project, and another that lets devs specify the regex patterns for
   nominating specific test files.

### 1.0.1
 - Reintroduce 'lastResult' querying - it's more accurate for figuring-out the
   status of a single test as other methods are affected by failed executions!

### 1.0.0
 - Initial release, extracted as a standalone project from the
   [Thymeleaf Layout Dialect](https://github.com/ultraq/thymeleaf-layout-dialect)
