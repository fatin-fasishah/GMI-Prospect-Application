# GMI Prospect Application

An Android application developed for the **SPG 0562 – Android Programming Mini Project** at **German-Malaysian Institute (GMI)**.

The application helps prospective students explore GMI programmes, check their preliminary eligibility based on academic results, and find information to contact the institute.

## Project Overview

The GMI Prospect Application provides a simple way for prospective students to:

- Explore programmes offered by GMI.
- Check which programmes may match their academic qualifications.
- View information about GMI.
- Access GMI's contact details and campus location.
- Continue to the GMI online application portal when eligible.

> **Note:** Eligibility results are preliminary guidance based on the requirements implemented in this academic project. They should not be treated as official confirmation of admission. Applicants should verify current entry requirements with GMI.

## Main Features

### 1. Programme Catalogue

Browse the programme categories included in the application:

- **Diploma**
- **German A-Level Preparatory Programme (GAPP)**
- **German University Foundation Programme (GUFP)**

The catalogue includes programme information to help prospective students explore available study pathways.

### 2. Eligibility Checker

The Eligibility Checker allows users to select an academic qualification type and enter their subject grades. The qualification types described in the project report are:

- SPM
- O-Level
- IGCSE

After the user submits the required grades, the application displays programme matches. The results page separates programmes into:

- **Potential Match** – programmes whose general academic requirements may be met.
- **Not Matched Yet** – programmes for which one or more requirements have not been met.

Users can also reset the form and start another eligibility check.

Eligible prospects can continue to the [GMI Online Application Portal](https://gmi.vialing.com/oa/login).

### 3. About GMI

Provides background information about German-Malaysian Institute, its educational focus, and the programmes and services described in the application.

### 4. Contact GMI

Provides GMI contact information and quick-access actions, including:

- Call GMI
- Email GMI
- View campus location

## Technology

The project report documents the use of:

- **Java** – Android application logic and activity navigation.
- **XML** – Android user-interface layouts.
- **Android Studio** – Android development environment.

The report's source-code section includes examples from `MainActivity` and the main screen XML layout.

## Getting Started

To run the application, you will need the complete Android Studio project source code.

1. Install [Android Studio](https://developer.android.com/studio).
2. Clone or download this repository.
3. In Android Studio, select **Open** and choose the folder containing the Android project (the folder with the Gradle project files).
4. Allow Gradle to sync.
5. Connect an Android device with USB debugging enabled, or create an Android Virtual Device.
6. Select **Run** to build and launch the application.

If this repository contains only the project report PDF, add the complete Android Studio project before following these steps.

## Repository Structure

A suggested repository structure is:

```text
GMI-Prospect-Application/
├── README.md
├── Documentation/
│   └── GMI_Prospect_Application_Report.pdf
├── app/
│   └── (Android Studio application source code)
└── screenshots/
    └── (Application screenshots)
```

The `app/` and `screenshots/` folders are placeholders until those files are added to the repository.

## Project Documentation

The project report covers:

- Introduction
- Application flowchart
- Programme catalogue screenshots and explanations
- Eligibility Checker screenshots and explanations
- About GMI and Contact GMI screenshots and explanations
- Java class activity source-code excerpts
- XML layout source-code excerpt
- Conclusion and references

Place the report in the `Documentation/` folder and name it:

`GMI_Prospect_Application_Report.pdf`

## External Links

- [German-Malaysian Institute – Official Website](https://www.gmi.edu.my/)
- [GMI Diploma Programme Admission](https://www.gmi.edu.my/diploma-programme-admission/)
- [GMI Foundation Programme Admission](https://www.gmi.edu.my/foundation-programme-admission/)
- [GMI German A-Level Preparatory Programme (GAPP)](https://www.gmi.edu.my/pre-university-programme/german-a-level-preparatory-programme-gapp/)
- [GMI Online Application Portal](https://gmi.vialing.com/oa/login)
- [Android Developers](https://developer.android.com/)

## Academic Information

| Item | Details |
|---|---|
| Institution | German-Malaysian Institute (GMI) |
| Course Code | SPG 0562 |
| Course | Android Programming |
| Project | GMI Prospect Application |
| Academic Period | July 2026 |
| Programme / Group | SWE 3 |

## Contributors

- Ain Sofea Binti Ahmad Rizal
- Nur Fatin Fasishah Binti M. Rusdei@Rusdi

---

This project was developed for academic assessment as part of the SPG 0562 Android Programming mini project.
