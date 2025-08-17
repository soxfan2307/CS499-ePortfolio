# Artifact Narrative: Android App with Database Integration (Enhancement 3)

## Description
The artifact enhanced here was `DatabaseHelper.java` and its integration across the Android weight tracking app. Originally, it showed basic SQLite operations but lacked strong interaction and secure data handling. Enhancements expanded to include `AddWeightActivity.java`, `DataAdapter.java`, `MainActivity.java`, `LoginActivity.java`, and `DataDisplayActivity.java`.

## Justification for Inclusion
I chose this artifact because it highlights **database integration, user authentication, and secure data handling**—all critical skills in mobile development.  
Enhancements showcased:  
- Login system with persistent sessions using SharedPreferences.  
- Foreign key relationships for users and weight entries.  
- New `AddWeightActivity` for secure weight/date entry.  
- Sorted display of entries in `DataDisplayActivity` using a `CursorAdapter`.  
- Secure parameterized queries in `DatabaseHelper.java`.  

These improvements demonstrate relational database design, Android UI/UX best practices, and secure data management.

## Reflection on Enhancement
Enhancing this artifact taught me how to securely connect **user authentication** with user-generated data.  
- **Challenges:** handling type mismatches, refining schema integrity, and debugging RecyclerView/adapter logic.  
- **Feedback applied:** improved validation and error handling; added real-time updates with RecyclerView and CursorAdapter.  
- **Learning:** reinforced database schema design, secure query implementation, and scalable UI integration.  

**Course Outcomes Met:** Databases (fully), Software Design & Engineering (fully), Algorithms & Data Structures (partially through session management and sorted retrieval).  
