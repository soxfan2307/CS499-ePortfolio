# Artifact Narrative: C++ Buffer Overflow Project (Enhancement 2)

## Description
The artifact `MyBufferOverflowApp.cpp` is a C++ application demonstrating numeric overflow/underflow in fixed-width data types. The original code was repetitive, hardcoded, and lacked abstraction. Enhancements transformed it into a modular, extensible program.

## Justification for Inclusion
I selected this artifact because it directly supports course outcomes for **algorithms and data structures**.  
Enhancements showcased:  
- Templated `run_test()` function for overflow/underflow.  
- `TestConfig` and `TestResult` structs for reusable, structured design.  
- Use of `std::numeric_limits` for type-safe boundary checks.  
- `std::vector` to manage multiple test cases.  
- User-driven menu for interactive selection of data types.  

These improvements elevated it from a simple demo to a flexible and professional-grade tool.

## Reflection on Enhancement
This enhancement deepened my knowledge of **templates, abstraction, and type safety** in C++.  
- **Challenges:** handling floating-point differences in `std::numeric_limits`, and template resolution errors.  
- **Feedback applied:** focused on modularity and usability through structured output and interactive features.  
- **Learning:** reinforced structured programming and usability in console-based apps.  

**Course Outcomes Met:** Algorithms & Data Structures (fully), Security (partially, via type-safe handling).  
