# add plugin
 <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-dependency-plugin</artifactId>
      <executions>
          <execution>
              <id>copy-dependencies</id>
              <phase>prepare-package</phase>
              <goals>
                  <goal>copy-dependencies</goal>
              </goals>
              <configuration>
                  <outputDirectory>${project.build.directory}/lib</outputDirectory>
                  <overWriteReleases>false</overWriteReleases>
                  <overWriteSnapshots>false</overWriteSnapshots>
                  <overWriteIfNewer>true</overWriteIfNewer>
              </configuration>
          </execution>
      </executions>
  </plugin>

  # other plugin need adition

    <plugin>
    <!-- Build an executable JAR -->
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <version>2.4</version>
    <configuration>
    <archive>
    <manifest>
     <addClasspath>true</addClasspath>
     <classpathPrefix>lib/</classpathPrefix>
     <mainClass>pe.edu.upeu.app.App</mainClass>
    </manifest>
    </archive>
    </configuration>
    </plugin>