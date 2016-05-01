
Thymeleaf Testing JUnit
=======================

[![Build Status](https://travis-ci.org/ultraq/thymeleaf-testing-junit.svg?branch=master)](https://travis-ci.org/ultraq/thymeleaf-testing-junit)
[![GitHub Release](https://img.shields.io/github/release/ultraq/thymeleaf-testing-junit.svg?maxAge=3600)](https://github.com/ultraq/thymeleaf-testing-junit/releases/latest)
[![Maven Central](https://img.shields.io/maven-central/v/nz.net.ultraq.thymeleaf/thymeleaf-testing-junit.svg?maxAge=3600)](http://search.maven.org/#search|ga|1|g%3A%22nz.net.ultraq.thymeleaf%22%20AND%20a%3A%22thymeleaf-testing-junit%22)
[![License](https://img.shields.io/github/license/ultraq/thymeleaf-testing-junit.svg?maxAge=2592000)](https://github.com/ultraq/thymeleaf-testing-junit/blob/master/LICENSE.txt)

A Thymeleaf Testing / JUnit bridge to treat each Thymeleaf test file as a JUnit
test.


Installation
------------

Minimum of Java 6 and a Thymeleaf 2.1 test environment required.

### Standalone distribution
Copy the JAR from [one of the release bundles](https://github.com/ultraq/thymeleaf-testing-junit/releases),
placing it in the classpath of your code, or build the project from the source
code here on GitHub.

### For Maven and Maven-compatible dependency managers
Add a dependency to your project with the following co-ordinates:

 - GroupId: `nz.net.ultraq.thymeleaf`
 - ArtifactId: `thymeleaf-testing-junit`
 - Version: (as per the badges above)


Usage
-----

Create a class in your tests directory that extends one of the test executor
classes in the `nz.net.ultraq.thymeleaf.testing` package.  Examples are below.

When you've done that, run JUnit such that it includes the class(es) you created.
JUnit's parameterized test magic will take care of the rest, executing your
Thymeleaf test files will be executed as individual JUnit tests.  Voilà! :)

### Extending `JUnitTestExecutorAll`

Extending this class is the quickest way to get all your Thymeleaf test files
executed with minimal configuration: just implement the required abstract method
that returns a list of all the dialects you want your tests to use.

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

As well as implementing the abstract method like in the `JUnitTestExecutorAll`
example above, you also need to specify your own `@Parameter`-annotated
[parameterized test](https://github.com/junit-team/junit/wiki/Parameterized-tests)
method.  This method should return paths for the list of Thymeleaf test files
you wish to execute.

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
