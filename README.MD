1. Build a fat jar with ./gradlew clean build shadowJar
2. Run the created jar file ./build/libs/spark-streaming-test-all.jar with `java -jar build/libs/spark-streaming-test-all.jar`
3. Uncomment line 18 in `Application.java` to see the infinte loop error.
