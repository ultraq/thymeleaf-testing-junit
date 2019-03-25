
Changelog
=========

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
