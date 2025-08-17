// MyBufferOverflowApp.cpp : Enhanced version with file export for test results

#include <iostream>
#include <limits>
#include <vector>
#include <type_traits>
#include <typeinfo>
#include <string>
#include <functional>
#include <fstream>

// Struct for holding test configuration
template<typename T>
struct TestConfig {
    T start;
    T step;
    unsigned long int steps;
    std::string testName;
};

// Struct for storing results
template<typename T>
struct TestResult {
    std::string testName;
    T result;
    bool overflowOccurred;
};

// Function to run a test
template<typename T>
TestResult<T> run_test(const TestConfig<T>& config, std::function<T(T, T)> operation, std::function<bool(T, T)> overflow_check) {
    TestResult<T> result;
    result.testName = config.testName;
    result.overflowOccurred = false;
    T temp = config.start;

    for (unsigned long int i = 0; i < config.steps; ++i) {
        if (overflow_check(temp, config.step)) {
            result.result = std::is_floating_point<T>::value ? std::numeric_limits<T>::lowest() : std::numeric_limits<T>::min();
            result.overflowOccurred = true;
            return result;
        }
        temp = operation(temp, config.step);
    }

    result.result = temp;
    return result;
}

// Overflow detection
template<typename T>
bool will_overflow(T a, T b) {
    if constexpr (std::is_integral<T>::value) {
        return (b > 0) && (a > std::numeric_limits<T>::max() - b);
    } else {
        return false;
    }
}

// Underflow detection
template<typename T>
bool will_underflow(T a, T b) {
    if constexpr (std::is_integral<T>::value) {
        return (a < std::numeric_limits<T>::min() + b);
    } else {
        return (a < std::numeric_limits<T>::lowest() + b);
    }
}

// Export results to file
template<typename T>
void export_results(const std::string& label, const std::vector<TestResult<T>>& results, std::ofstream& file) {
    file << "\n*** " << label << " for Type = " << typeid(T).name() << " ***\n";
    for (const auto& res : results) {
        file << "\t" << res.testName << ": ";
        if (res.overflowOccurred)
            file << "Overflow/Underflow detected! ";
        file << +res.result << std::endl;
    }
}

// Run the tests and store results
template<typename T>
void run_tests(const std::string& label, bool isOverflow, std::ofstream& file) {
    std::cout << "\n*** " << label << " for Type = " << typeid(T).name() << " ***\n";

    std::vector<TestConfig<T>> configs = {
        {isOverflow ? static_cast<T>(0) : std::numeric_limits<T>::max(),
         static_cast<T>(std::numeric_limits<T>::max() / static_cast<T>(5)),
         5,
         "Normal Operation"},
        {isOverflow ? static_cast<T>(0) : std::numeric_limits<T>::max(),
         static_cast<T>(std::numeric_limits<T>::max() / static_cast<T>(5)),
         6,
         "With Overflow/Underflow"}
    };

    std::vector<TestResult<T>> results;

    for (const auto& config : configs) {
        std::function<T(T, T)> op = isOverflow
            ? [](T a, T b) { return a + b; }
            : [](T a, T b) { return a - b; };
        std::function<bool(T, T)> check = isOverflow ? will_overflow<T> : will_underflow<T>;
        results.push_back(run_test<T>(config, op, check));
    }

    for (const auto& res : results) {
        std::cout << "\t" << res.testName << ": ";
        if (res.overflowOccurred)
            std::cout << "Overflow/Underflow detected! ";
        std::cout << +res.result << std::endl;
    }

    export_results<T>(label, results, file);
}

// Interactive menu
void interactive_test_menu() {
    std::ofstream outFile("overflow_test_results.txt");
    if (!outFile) {
        std::cerr << "Error opening file for writing results.\n";
        return;
    }

    std::cout << "Select data type to test:\n";
    std::cout << "1. char\n";
    std::cout << "2. int\n";
    std::cout << "3. unsigned int\n";
    std::cout << "4. float\n";
    std::cout << "5. Run all\n";
    std::cout << "Choice: ";

    int choice;
    std::cin >> choice;

    switch (choice) {
        case 1:
            run_tests<char>("Overflow Test", true, outFile);
            run_tests<char>("Underflow Test", false, outFile);
            break;
        case 2:
            run_tests<int>("Overflow Test", true, outFile);
            run_tests<int>("Underflow Test", false, outFile);
            break;
        case 3:
            run_tests<unsigned int>("Overflow Test", true, outFile);
            run_tests<unsigned int>("Underflow Test", false, outFile);
            break;
        case 4:
            run_tests<float>("Overflow Test", true, outFile);
            run_tests<float>("Underflow Test", false, outFile);
            break;
        case 5:
            run_tests<char>("Overflow Test", true, outFile);
            run_tests<char>("Underflow Test", false, outFile);
            run_tests<int>("Overflow Test", true, outFile);
            run_tests<int>("Underflow Test", false, outFile);
            run_tests<unsigned int>("Overflow Test", true, outFile);
            run_tests<unsigned int>("Underflow Test", false, outFile);
            run_tests<float>("Overflow Test", true, outFile);
            run_tests<float>("Underflow Test", false, outFile);
            break;
        default:
            std::cout << "Invalid choice. Exiting.\n";
    }

    outFile << "\nAll Tests Complete!\n";
    outFile.close();
}

int main() {
    const std::string star_line = std::string(50, '*');
    std::cout << "Starting Numeric Overflow / Underflow Tests!" << std::endl;
    std::cout << star_line << std::endl;

    interactive_test_menu();

    std::cout << "\nAll Tests Complete! Results saved to 'overflow_test_results.txt'." << std::endl;
    return 0;
}

