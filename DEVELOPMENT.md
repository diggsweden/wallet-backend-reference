<!--
SPDX-FileCopyrightText: 2025 Digg - Agency for Digital Government

SPDX-License-Identifier: CC0-1.0
-->

# Development Guide

This guide outlines core essentials you need to know for developing in this project.
This guide outlines core essentials for developing in this project.

## Table of Contents

- [Setup and Configuration](#setup-and-configuration)
  - [IDE Setup](#ide-setup)
  - [Consuming SNAPSHOTS](#consuming-snapshots-from-maven-central)
- [Development Workflow](#development-workflow)
  - [Testing and Verification](#testing-format-and-lint)
  - [Documentation](#documentation)
  - [Pull Request Process](#pull-request-workflow)

## Setup and Configuration

### IDE Setup

#### VSCode

 1. Install [Checkstyle For Java](https://marketplace.visualstudio.com/items?itemName=shengchen.vscode-checkstyle)
 2. Open workspace settings - settings.json (for example with Ctrl+Shift+P → Preferences: Workspace Settings (JSON)) and add:

    ```json
    "[java]": {
        "editor.defaultFormatter": "redhat.java",
    },
    "java.format.settings.url": "development/format/eclipse-java-google-style.xml",
    "java.format.settings.profile": "GoogleStyle",
    "editor.formatOnSave": true,
    "java.checkstyle.configuration": "development/lint/google_checks.xml",
    "java.checkstyle.version": "1x.xx.x"
    ```

#### IntelliJ

 1. **Code Style**
    - Settings → `Editor → Code Style → Java`
    - Click gear → `Import Scheme → Eclipse XML Profile`
    - Select `development/format/eclipse-java-google-style.xml`

 2. **Checkstyle**
    - Install "CheckStyle-IDEA" plugin
    - Settings → `Tools → Checkstyle`
    - Click the built-in Google Style Check

## Consuming SNAPSHOTS from Maven Central

Configure your pom.xml file with:

```xml
<repositories>
  <repository>
    <name>Central Portal Snapshots</name>
    <id>central-portal-snapshots</id>
    <url>https://central.sonatype.com/repository/maven-snapshots/</url>
    <releases>
      <enabled>false</enabled>
    </releases>
    <snapshots>
      <enabled>true</enabled>
    </snapshots>
  </repository>
</repositories>
```

## Development Workflow

### Testing, Format and Lint

Run Maven verification:

```shell
mvn clean verify
```

### Documentation

Generate Javadocs:

```shell
mvn javadoc:javadoc
```

View documentation in your browser:

```shell
<browser> target/reports/apidocs/index.html
```

### Pull Request Workflow

When submitting a PR, CI will automatically run several checks. To avoid surprises, run these checks locally first.

#### Pull Request Workflow Prerequisites

- [Podman](https://podman.io/)

#### Running Code Quality Checks Locally

1. Run the quality check script:

   ```shell
   ./development/code_quality.sh
   ```

2. Fix any identified issues

3. Update your PR with fixes

4. Verify CI passes in the updated PR

#### Quality Check Details

- **Linting with megalinter**: BASH, Markdown, YAML, GitHub Actions, security scanning
- **License Compliance**: REUSE tool ensures proper copyright information
- **Commit Structure**: Conform checks commit messages for changelog generation
- **Dependency Analysis**: Scans for vulnerabilities, outdated packages, and license issues
- **OpenSSF Scorecard**: Validates security best practices

#### Handling Failed Checks

If any checks fail in the CI pipeline:

1. Review the CI error logs
2. Run checks locally to reproduce the issues
3. Make necessary fixes in your local environment
4. Update your Pull Request
5. Verify all checks pass in the updated PR
