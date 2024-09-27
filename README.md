# JsonData Processor README

## Overview

`JsonData Processor` is a Java application that reads a JSON array of calculation formulas, evaluates the formulas, and outputs the results in JSON format. The program uses libraries such as **JSON Simple** for JSON parsing and **Exp4j** for evaluating mathematical expressions. It takes in predefined formulas, processes them in a specific order, and generates a result JSON array containing evaluated values for each calculation field.

## Features

- **JSON Parsing**: Parses JSON input with calculation formulas.
- **Formula Evaluation**: Evaluates mathematical expressions using the `Exp4j` library.
- **Result Generation**: Outputs a JSON array with the results of each formula.
- **Handles Dependencies**: Processes formulas that depend on the results of previous calculations.

## Installation

1. Clone the repository:
   ```bash
   git clone [https://github.com/your-username/JsonDataProcessor.git](https://github.com/deepanshu1531/JSON-Data-Processing)
