# PetClinicApp

![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)
![Status](https://img.shields.io/badge/Status-WIP-orange)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

A Java console application for managing a pet clinic: booking appointments, registering pet owners, and searching records.

## Overview

- **PetOwner** — owner name, pet name, pet type, and booked slot number
- **PetClinicApp** — holds pet owners and per-type slot availability (dog/cat/rabbit), and drives the console menu

## Menu options

1. Book Appointment — pick a pet type and slot; validates the slot number and rejects double-booking
2. Register Pet Owner — collects owner/pet details and links them to a booked slot
3. Search Owner by Pet Name — case-insensitive search across registered owners
4. Save Data to File — writes all registered owners to `ClinicData.txt`
0. Exit

## Project structure

```
src/
├── PetClinicApp.java   # Entry point, menu, and clinic logic
└── PetOwner.java        # Pet owner model
```

## Running it

Requires Java 17+.

```bash
javac -d out src/PetClinicApp.java src/PetOwner.java
java -cp out PetClinicApp
```
