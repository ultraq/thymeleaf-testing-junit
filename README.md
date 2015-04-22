
Thymeleaf Testing JUnit
=======================

A Thymeleaf Testing / JUnit bridge to treat each Thymeleaf test file as a JUnit
test.

 - Current version: 1.0.1
 - Released: 22 April 2015


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
 - Version: `1.0.1`


Usage
-----

Create a class in your tests directory that extends `nz.net.ultraq.thymeleaf.testing.JUnitTestExecutor`,
implementing the 1 abstract method, `getTestDialects`.  This method should
return a list of dialects you want to include in testing.  Note that you'll have
to include one of Thymeleaf's `StandardDialect` or `SpringStandardDialect` as
the minimum.

What this does is extend a [JUnit parameterized test class](https://github.com/junit-team/junit/wiki/Parameterized-tests)
that is already set up to search your classpath for Thymeleaf test files
(`.thtest`).  All it needed was what dialects to test with, which you've now
provided.

With that done, run JUnit such that it includes the class you created.  The
parameterized test class will be discovered, search for `.thtest` files, and
execute them as individual JUnit tests.  Voilà! :)


Changelog
---------

### 1.0.1
 - Reintroduce 'lastResult' querying - it's more accurate for figuring-out the
   status of a single test as other methods are affected by failed executions!

### 1.0.0
 - Initial release, extracted as a standalone project from the
   [Thymeleaf Layout Dialect](https://github.com/ultraq/thymeleaf-layout-dialect)
