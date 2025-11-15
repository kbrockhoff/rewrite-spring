/*
 * Copyright 2025 the original author or authors.
 * <p>
 * Licensed under the Moderne Source Available License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://docs.moderne.io/licensing/moderne-source-available-license
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.java.awspring;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.gradle.Assertions.buildGradle;
import static org.openrewrite.gradle.Assertions.buildGradleKts;
import static org.openrewrite.java.Assertions.mavenProject;
import static org.openrewrite.java.awspring.TestUtils.loadCodeFile;
import static org.openrewrite.maven.Assertions.pomXml;

class MigrateSpringCloudAwsToAwSpringTest implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipeFromResource(
          "/META-INF/rewrite/awspring-2.yml", "" +
            "org.openrewrite.java.awspring.MigrateSpringCloudAwsToAwSpring"
        );
    }

    @Test
    void shouldConvertMavenFromSpringCloud2020() {
        rewriteRun(
          pomXml(
            loadCodeFile(getClass(), "spring-cloud-2020-pom-before.xml"),
            loadCodeFile(getClass(), "spring-cloud-2020-pom-after.xml")
          ));
    }

    @Test
    void shouldConvertMavenFromSpringCloud2021() {
        rewriteRun(
          pomXml(
            loadCodeFile(getClass(), "spring-cloud-2021-pom-before.xml"),
            loadCodeFile(getClass(), "spring-cloud-2021-pom-after.xml")
          ));
    }

    @Test
    void shouldConvertGradleGroovyFromSpringCloud() {
        rewriteRun(
          mavenProject("project",
            buildGradle(
              loadCodeFile(getClass(), "spring-cloud-2022-before.gradle"),
              loadCodeFile(getClass(), "spring-cloud-2022-after.gradle")
            )
          ));
    }

    @Test
    void shouldConvertGradleKtsFromSpringCloud() {
        rewriteRun(
          mavenProject("project",
            buildGradleKts(
              loadCodeFile(getClass(), "spring-cloud-hoxton-before.gradle.kts"),
              loadCodeFile(getClass(), "spring-cloud-hoxton-after.gradle.kts")
            )
          ));
    }

}
