# NutriScoreCalc
Application to calculate nutri score

## Build and run (Windows)
The commands below assume you have Java (JDK 11+) and Maven installed and available on your PATH. Run them from a Windows `cmd.exe` prompt opened at the project root.

1) Build the project (from project root):

```cmd
mvn clean package
```

2) Run the produced JAR. This repository contains multiple modules; the main web application JAR is produced by the `web-spring` module. Run it with:

```cmd
java -jar web-spring\target\web-spring-1.0-SNAPSHOT.jar
```

If you only want to build a single module (for example `web-spring`), run:

```cmd
mvn -pl web-spring -am clean package
```

Notes:
- If a different module produces the artifact you want to run, change the path to the appropriate `target\<artifact>.jar`.
- On first build Maven will download dependencies; subsequent builds are faster.
