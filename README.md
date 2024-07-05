
# Selenium-Java-Trendyol-Testing

## Overview

This project provides automated testing examples for [Trendyol](https://www.trendyol.com), utilizing Selenium and Java. The goal is to ensure clean code and a clear test structure for efficient and reliable test automation.

## Features

- Automated tests for Trendyol website functionalities
- Structured using the Page Object Model (POM)
- Configurable for cross-browser testing and dynamic URL handling
- TestNG for test execution and reporting

## Project Structure

```
.
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   └── test
│       └── java
│           └── com
│               └── trendyol
│                   ├── pages
│                   ├── tests
│                   └── utils
│   └── resources
│       └── configuration.yaml
│       └── suite1.xml
├── test-output
├── .gitignore
├── README.md
└── pom.xml
```

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven
- Selenium WebDriver
- TestNG
- WebDriverManager

- See pom.xml for other requirements

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/arthas009/Selenium-Java-Trendyol-Testing.git
   cd Selenium-Java-Trendyol-Testing
   ```

2. Install the dependencies:
   ```sh
   mvn clean install
   ```

### Running Tests

To run the test suite, use:
```sh
mvn test
```

## Configuration

Adjust configuration settings in the `config.properties` file located in the `src/main/resources` directory. This includes browser configurations and base URL settings.

## Reporting

Test reports are generated using TestNG's built-in reporting. After running tests, you can find the reports in the `test-output` directory.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries, please contact [arthas009](https://github.com/arthas009).
