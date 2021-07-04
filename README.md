
Thymeleaf Testing JUnit
=======================

[![Build Status](https://travis-ci.com/ultraq/thymeleaf-testing-junit.svg?branch=main)](https://travis-ci.com/ultraq/thymeleaf-testing-junit)
[![Coverage Status](https://coveralls.io/repos/github/ultraq/thymeleaf-testing-junit/badge.svg?branch=main)](https://coveralls.io/github/ultraq/thymeleaf-testing-junit?branch=main)
[![Maven Central](https://img.shields.io/maven-central/v/nz.net.ultraq.thymeleaf/thymeleaf-testing-junit.svg?maxAge=3600)](http://search.maven.org/#search|ga|1|g%3A%22nz.net.ultraq.thymeleaf%22%20AND%20a%3A%22thymeleaf-testing-junit%22)

A Thymeleaf Testing / JUnit bridge to treat each Thymeleaf test file as a JUnit
test.


Installation
------------

Minimum of Java 8 and Thymeleaf 3.0 required.

### For Maven and Maven-compatible dependency managers

Add a dependency to your project with the following co-ordinates:

 - GroupId: `nz.net.ultraq.thymeleaf`
 - ArtifactId: `thymeleaf-testing-junit`
 - Version: `3.0.1`

Check the [project releases](https://github.com/ultraq/thymeleaf-testing-unit/releases)
for a list of available versions.  Each release page also includes a
downloadable JAR if you want to manually add it to your project classpath.


Usage
-----

Create a class in your tests directory that extends one of the test executor
classes in the `nz.net.ultraq.thymeleaf.testing` package.  Examples are below.

When you've done that, run JUnit such that it includes the class(es) you created.
JUnit's parameterized test magic will take care of the rest, executing your
Thymeleaf test files as individual JUnit tests.  Voilà! :)

### Extending `JUnitTestExecutorAll`

Extending this class is the quickest way to get all your Thymeleaf test files
executed with minimal configuration: implement the required abstract method that
returns a list of all the dialects you want your tests to use.

```java
public class MyDialectTestExecutor extends JUnitTestExecutorAll {

  @Override
  public List<IDialect> getTestDialects() {
    List<IDialect> dialects = new List<>();
    dialects.add(new StandardDialect()); // See note below
    dialects.add(new MyDialect());
    return dialects;
  }
}
```

> One of Thymeleaf's standard dialects, `StandardDialect` or `SpringStandardDialect`,
> is required so it can perform tests.

### Extending `JUnitTestExecutor`

If you need some control over which Thymeleaf testing files get run, you can
extend this class like above, but then specify your own `@Parameter`-annotated
[parameterized test](https://github.com/junit-team/junit/wiki/Parameterized-tests)
method.  This method should return a list of paths of Thymeleaf test files you
wish to execute.

```java
public class MyDialectTestExecutor extends JUnitTestExecutor {

  @Parameters(name = "{0}")
  public static List<String> listSpecificTests() {
    return new ArrayList<>(
      "com/mysite/myproject/MyTest1.thtest",
      "com/mysite/myproject/MyTest2.thtest"
    );
  }
}
```

Extending this class lets you select only the test files you want tested using
this test executor, which can be useful if you need to configure a different set
of dialects for different tests.
