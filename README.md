# Setup

The following are required to build this project:

* Maven 3.6.2 would be preferable
* JDK 11

For ease of installation I recommend installing sdkman.io. The following commands can be used to install the required tools:
* `sdk install java 11.0.4-amzn`
* `sdk install maven 3.6.2`

To build the project use the following:

`mvn clean install`

# Code coverage

As part of the build process the openclover plugin has been add. Under:

`target/site/clover` there will be an `index.html` which will have the report.

As such, the plugin is configured to fail if the coverage falls below the expected compliance level
