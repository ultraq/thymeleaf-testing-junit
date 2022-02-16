
Thymeleaf Testing JUnit
=======================

[![Build Status](https://github.com/ultraq/thymeleaf-testing-junit/actions/workflows/build.yml/badge.svg)](https://github.com/ultraq/thymeleaf-testing-junit/actions)
[![codecov](https://codecov.io/gh/ultraq/thymeleaf-testing-junit/branch/main/graph/badge.svg?token=SKCSj20MAC)](https://codecov.io/gh/ultraq/thymeleaf-testing-junit)
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
 - Version: `5.0.0`

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
    List<IDialect> dialects = new ArrayList<>();
    dialects.add(new StandardDialect()); // See note below
    dialects.add(new MyDialect());
    return dialects;
  }
}
```

> One of Thymeleaf's standard dialects, `StandardDialect` or `SpringStandardDialect`,
> is required so it can perform tests.

### Extending `JUnitTestExecutor`

If you need some control over which Thymeleaf test files get run, you can extend
this class and then specify your own `getThymeleafTestFiles` method, which is a
factory method that returns the list of files to execute:

```java
public class MyDialectTestExecutor extends JUnitTestExecutor {

  public static List<String> getThymeleafTestFiles() {
  	List<String> thymeleafTestFiles = new ArrayList<>();
  	thymeleafTestFiles.add("com/mysite/myproject/MyTest1.thtest");
  	thymeleafTestFiles.add("com/mysite/myproject/MyTest2.thtest");
  	return thymeleafTestFiles;
  }
}
```

Extending this class is most useful if you need to configure a different set of
dialects for different tests.
