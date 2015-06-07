
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
method.  This method should return the list of Thymeleaf test files you wish to
execute.  This can be made easier utilizing the `reflections` instance that is
configured to search the classpath for resource files.

```java
public class MyDialectTestExecutor extends JUnitTestExecutor {

  @Parameters(name = "{0}")
  public static List<String> listSpecificTests() throws URISyntaxException {
    return new ArrayList<>(
      reflections.getResources(Pattern.compile('TestPrefix-.*\\.thtest'))
    );
  }
}
```

> `reflections` is an instance of [the reflections library](https://github.com/ronmamo/reflections),
> so check out that project and docs if you wish to use it in other ways than
> just shown here.

Extending this class lets you select only the test files you want tested using
this test executor, which can be useful if you need to configure a different set
of dialects for different tests.


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
