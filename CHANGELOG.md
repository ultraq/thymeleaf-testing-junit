
Changelog
=========

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
