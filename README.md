# PetClinicApp

![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)
![Status](https://img.shields.io/badge/Status-MVP-brightgreen)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

A Java console application for managing a pet clinic: booking appointments, registering pet owners, searching records, cancelling bookings, and persisting data across restarts.

## Overview

- **PetOwner** — owner name, pet name, pet type, and booked slot number
- **PetClinicApp** — holds pet owners and per-type slot availability (dog/cat/rabbit), drives the console menu, and handles file persistence

## Menu options

1. **Book Appointment** — pick a pet type and slot; validates the slot number and rejects double-booking
2. **Register Pet Owner** — collects owner/pet details and links them to a booked slot
3. **Search Owner by Pet Name** — case-insensitive search across registered owners
4. **Save Data to File** — writes all registered owners to `ClinicData.txt`
5. **List All Appointments** — prints every booked appointment in a numbered list
6. **Cancel Appointment** — cancels by pet name and frees the slot for re-booking
0. **Exit**

## Persistence

On startup the app looks for `ClinicData.txt` in the working directory and reloads any previously saved appointments, rebuilding slot state so the same slot cannot be double-booked after a restart. Malformed lines are skipped rather than crashing the load.

## Project structure

```
src/
├── PetClinicApp.java   # Entry point, menu, clinic logic, file I/O
└── PetOwner.java       # Pet owner model
```

## Running it

Requires Java 17+.

```bash
javac -d out src/PetClinicApp.java src/PetOwner.java
java -cp out PetClinicApp
```

## License

MIT — see [LICENSE](LICENSE).
