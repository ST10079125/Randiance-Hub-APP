# Randiance-Hub-APP ( Beauty & Wellness Booking App )

## 📘 Overview
**RadianceHub** is a modern Android-based beauty and wellness booking platform that connects clients with salons and beauticians. The application allows users to explore services, view professional profiles, schedule appointments, and make secure payments — all from one easy-to-use mobile app.

The app bridges the gap between customers and service providers by offering a reliable, secure, and user-friendly experience, replacing manual booking systems with an accessible digital solution that supports **offline functionality**, **notifications**, and **multi-language access**.

<img width="500" height="1000" alt="1 log" src="https://github.com/user-attachments/assets/9645a00e-904b-40b0-85ec-b420243936ec" />
<img width="500" height="1000" alt="2" src="https://github.com/user-attachments/assets/f1c9cd89-179a-4550-9999-bcabca9a6f05" />
<img width="500" height="1000" alt="3" src="https://github.com/user-attachments/assets/d804414b-fd49-4bd2-bc4b-2dcc69c522ff" />
<img width="500" height="1000" alt="4" src="https://github.com/user-attachments/assets/8e32d526-4e94-4690-9ab8-7385abf56e17" />
<img width="500" height="1000" alt="7" src="https://github.com/user-attachments/assets/2e1f5e3f-fdc4-446c-87d4-d9fc7740e35c" />

---

## 🎯 Purpose of the Application
The purpose of **RadianceHub** is to modernize how beauty and wellness services are accessed in South Africa by providing a secure digital environment where clients and service providers can connect seamlessly.

### **Core Objectives**
- Simplify service discovery and appointment scheduling.
- Enable user registration with encrypted credentials and SSO.
- Provide beauty professionals with management and booking tools.
- Offer multilingual support (English and isiZulu).
- Include offline functionality and push notifications.
- Prepare the app for Google Play Store publication.

---

## ⚙️ Functional Features
1. **User Registration & Login**
   - Firebase Authentication with password encryption.
   - Single Sign-On (SSO) using Google accounts.
   - Session tokens maintain secure user state.

2. **Service Browsing & Booking**
   - View categorized services (*Hair, Nails, Facials, Massage*).
   - Book appointments, reschedule, or cancel.
   - Confirmation screen before final submission.

3. **Offline Mode with Sync**
   - Uses **RoomDB** to store bookings locally.
   - Automatically syncs data when internet reconnects.

4. **Push Notifications**
   - Firebase Cloud Messaging (FCM) sends real-time updates for bookings and reminders.

5. **Multilingual Support**
   - Supports English and isiZulu.
   - Users can select their preferred language in Settings.

6. **Payments**
   - Integrated “Pay at Salon” and online payment simulation.
   - PCI-DSS-compliant encryption for card details.

7. **Technician Profiles & Ratings**
   - Beauticians display portfolios, images, and client feedback.

8. **User Settings**
   - Users can update profile, change password, and language preferences.

---

## 🎨 Design Considerations
### **User Interface (UI)**
- Clean, minimal, and mobile-first layout created in **Figma**.
- Intuitive bottom navigation with icons for *Home*, *Bookings*, *Notifications*, and *Profile*.
- Color palette emphasizes calm and professionalism (pastel pink, white, lavender).

### **User Experience (UX)**
- ≤ 3 taps for any core task.
- Input validation prevents crashes from invalid entries.
- Real-time feedback for every booking or action.
- Adaptive layouts for various screen sizes.

---

## 🧠 Technical Design
| Aspect | Implementation |
|--------|----------------|
| **Language** | Kotlin |
| **IDE** | Android Studio |
| **Database** | RoomDB (Offline) + Firebase (Online) |
| **Authentication** | Firebase Auth with SSO |
| **Backend API** | REST API connection for services |
| **Notifications** | Firebase Cloud Messaging |
| **Version Control** | Git & GitHub |
| **CI/CD** | GitHub Actions |
| **Design** | Figma UI Mockups |

---

## 🧩 GitHub & Version Control
### **Version Control**
- Repository initialized with README.
- Project files committed progressively (authentication, booking, offline sync, notifications, etc.).
- Final tag: **`Final-POE`** marks submission version.

### **Sample Commit History**
1. Initial Commit – RadianceHub App Setup
2. Added Authentication & SSO
3. Integrated REST API and RoomDB Sync
4. Implemented Push Notifications
5. Added Multi-Language Support (English, Sepedi & Venda)
6. UI Enhancements & Final Assets
7. Configured GitHub Actions Workflow
8. Publishing Preparation and README Update
9. Final POE Tag Submission

---

## ⚙️ Continuous Integration – GitHub Actions
**Workflow File:** `.github/workflows/build.yml`
```yaml
name: Build Android App

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v3

      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Grant execute permission for Gradle
        run: chmod +x gradlew

      - name: Build with Gradle
        run: ./gradlew build

      - name: Run Tests
        run: ./gradlew test

