# Transactions CSV challenges

## Tech Stacks

- java 8
- maven 3
- intellij

### Sub libraries

- picocli for command line parsing
- okhttp for fetch exchange rate API
- fastjson alibaba for processing JSON return from API
- junit 5 for boundary test context

## Setup for MacOS

### Java 8

```
    brew tap adoptopenjdk/openjdk
    brew install --cask adoptopenjdk8
```

![alt text](docs/java-8-macos-brew-setup.png "Java 8 MacOS brew setup")

### Maven 3.8.6

```
    brew install maven
```

![alt text](docs/maven3.8.6-macOS-brew-setup.png "Maven 3.8.6 MacOS brew setup")


## Run project by commandline

```shell

    mvn clean install


```