
Thymeleaf Testing JUnit
=======================

A Thymeleaf Testing / JUnit bridge to treat each Thymeleaf test file as a JUnit
test.

 - Current version: 1.1.1
 - Released: 2 May 2015


Installation
------------

Minimum of Java 6 and a Thymeleaf 2.1 test environment required.

### Standalone distribution
Copy the JAR from [the latest release bundle](https://github.com/ultraq/thymeleaf-testing-junit/releases),
placing it in the classpath of your code, or build the project from the source
code here on GitHub.

### For Maven and Maven-compatible dependency managers
Add a dependency to your project with the following co-ordinates:

 - GroupId: `nz.net.ultraq.thymeleaf`
 - ArtifactId: `thymeleaf-testing-junit`
 - Version: `1.1.1`


Usage
-----

Create a class in your tests directory that extends one of the test executor
classes in the `nz.net.ultraq.thymeleaf.testing` package:

 - Extending `JUnitTestExecutorAll` is the quickest way to get all of your
   Thymeleaf test files executed with minimal configuration.
 - Extending `JUnitTestExecutor` requires you to specify your own `@Parameter`-annotated
   [parameterized test](https://github.com/junit-team/junit/wiki/Parameterized-tests)
   method.  This method should return the list of Thymeleaf test files you wish
   to execute.  This can be made easier utilizing the `reflections` instance
   that is configured to search the classpath for resource files.

Either class also requires you to implement 1 abstract method, `getTestDialects`.
This method should return a list of dialects you want to include in testing.
Note that you'll have to include one of Thymeleaf's `StandardDialect` or
`SpringStandardDialect` at the minimum.

With that done, run JUnit such that it includes the class you created.  The
parameterized test class will be discovered, search for the test files, and
execute them as individual JUnit tests.  Voilà! :)


Changelog
---------

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
