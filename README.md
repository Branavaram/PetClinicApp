# PetClinicApp

A Java console application for managing a pet clinic: booking appointments, registering pet owners, and searching records.

**Status: work in progress.** The menu and structure are in place, but several core methods are not yet implemented.

## Overview

- **PetOwner** — not yet implemented
- **PetClinicApp** — holds pet owners and per-type slot availability (dog/cat/rabbit), and drives the console menu

## Menu options

1. Book Appointment — *not yet implemented*
2. Register Pet Owner — *not yet implemented*
3. Search Owner by Pet Name — *not yet implemented*
4. Save Data to File — *not yet implemented*
0. Exit

## Project structure

```
src/
├── PetClinicApp.java   # Entry point, menu, and clinic logic (in progress)
└── PetOwner.java        # Pet owner model (in progress)
```

## Running it

Requires Java 17+.

```bash
javac -d out src/PetClinicApp.java src/PetOwner.java
java -cp out PetClinicApp
```
