# Android App with Database Integration

## Overview
This Android application is a fully functional weight-tracking app that demonstrates how databases can be securely and effectively integrated into mobile software. The app allows users to log in, record their weight entries with dates, and view past entries in a simple table format. 

The database component, implemented through `DatabaseHelper.java`, ensures data is stored securely, retrieved efficiently, and protected against common vulnerabilities. The enhancement expanded beyond the helper class itself to include multiple activities and supporting files, creating a completed, production-ready application.

## Skills Demonstrated
- **Databases**: Designed and implemented efficient SQLite schemas for persistent storage.  
- **Secure Coding**: Applied parameterized queries to protect against SQL injection.  
- **Software Engineering**: Refactored multiple activities to integrate database functionality across the app.  
- **User Experience**: Enabled smooth data input, storage, and display through structured activity flow.  
- **Testing & Debugging**: Validated database operations and error handling under real usage scenarios.  

## Enhancement
The enhancement for this artifact focused on **transforming the database integration into a secure, complete feature across the entire app**:  
- Replaced raw SQL queries with parameterized queries for improved security.  
- Added robust error handling for database transactions.  
- Refactored methods in `DatabaseHelper.java` for readability and modularity.  
- Integrated database functionality into activities responsible for login, weight entry, and data display.  
- Introduced a `DataAdapter` class to handle structured retrieval and presentation of stored records.  

## Enhanced Application Files
- [`MainActivity.java`](../AndroidApp/MainActivity.java) → Entry point for user interaction, navigates between login, data entry, and data display.  
- [`LoginActivity.java`](../AndroidApp/LoginActivity.java) → Handles secure user authentication and validation.  
- [`DataDisplayActivity.java`](../AndroidApp/DataDisplayActivity.java) → Retrieves and displays stored weight entries in a user-friendly tabular format.  
- [`AddWeightActivity.java`](../AndroidApp/AddWeightActivity.java) → Allows users to input new weight and date records into the database.  
- [`DataAdapter.java`](../AndroidApp/DataAdapter.java) → Adapter for managing and presenting database records in a structured list/table.  
- [`DatabaseHelper.java`](DatabaseHelper.java) → Core database class managing schema creation, inserts, queries, and secure operations.  

## Summary
This artifact represents the **completed Android app** enhanced to showcase secure and effective database integration. It demonstrates my ability to apply **database best practices**, ensure **security in user data handling**, and deliver a maintainable, user-friendly mobile application.
