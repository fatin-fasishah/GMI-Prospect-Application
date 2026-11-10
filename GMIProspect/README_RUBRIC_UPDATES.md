# GMIProspect – Updated Source Project

## Changes in this revision

1. Added a reusable top-level `Programme` model with title, category, department, duration, fee, description and URL fields.
2. Updated `ProgrammesActivity` to use the top-level model instead of a nested duplicate class.
3. Moved 69 literal XML `text`, `hint` and `contentDescription` values into `res/values/strings.xml`.
4. Preserved the existing screen designs, colours, navigation and programme catalogue content.

## Important checks before submission

- Open the `GMIProspect` folder in Android Studio and run **Build > Make Project**.
- Test the app on an emulator/device, especially the programme catalogue, eligibility checker, enquiry links and application portal.
- The eligibility rules in `EligibilityActivity.java` are application-configured rules. Verify each one against the latest official GMI admission requirements before presenting the checker as accurate.
- The source ZIP does not include machine-specific `.gradle`, `.idea`, `build` or `local.properties` files. Android Studio will regenerate these locally.

## Build verification status

XML parsing and string-resource reference checks passed in the preparation environment. A fresh Gradle build could not be run because Gradle 9.4.1 was not available locally and the environment could not reach `services.gradle.org`. Please run the build in Android Studio.
