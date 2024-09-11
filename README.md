# Data Processing Application

This project is a Java-based application for loading, processing, and consolidating data from various sources. The
application is designed to handle multiple datasets, clean outliers, and generate consolidated output. It is
particularly suited for sports-related data.

## Features

- **Data Loading**: Load data from multiple sources and formats using the `DataLoader` class.
- **Data Cleaning**: Automatically clean data by removing outliers with the `DataOutliersCleaner`.
- **Data Processing**: Process and consolidate data using the `DataProcessor` class.
- **Custom Exception Handling**: Robust error handling for data loading errors with `DataLoadException`.
- **Detailed Models**: Use detailed models like `DetailedHeartRateSample`, `Lap`, and `Sample` for fine-grained data
  analysis.
- **Command-Line Interface**: Easily run the application and configure settings via command-line arguments.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.
- Gradle for build automation.

### Installation

1. Clone the repository:

    ```sh
    git clone https://github.com/pzajko97/airow-project.git
    ```

2. Build the project using Gradle:

    ```sh
    ./gradlew build
    ```

### Usage

To run the application, use the following command:

```sh
./gradlew run --args="--summary=<path_to_summary_file> --laps=<path_to_laps_file> --samples=<path_to_samples_file> --output=<path_to_output_file>"
```

To get additional help, use the following command:
To run the application, use the following command:

```sh
./gradlew run --args="--h"