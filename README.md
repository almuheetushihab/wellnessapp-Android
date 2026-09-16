# 🧘 WellnessApp — Mindful Living & Ambient Video Experience

![Kotlin](https://img.shields.io/badge/Kotlin-100%25-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.6+-4285F4?style=for-the-badge&logo=android&logoColor=white)
![Media3 ExoPlayer](https://img.shields.io/badge/Media3-ExoPlayer-FF0000?style=for-the-badge&logo=youtube&logoColor=white)
![Min SDK](https://img.shields.io/badge/Min%20SDK-24+-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-Clean%20%2B%20MVVM-0052CC?style=for-the-badge&logo=android)

> **WellnessApp** is a modern, award-winning iOS-inspired Android application designed for mental well-being, mindfulness, yoga, and relaxation. Built from the ground up using **Jetpack Compose**, **Shared Element Transitions**, **Media3 / ExoPlayer**, and **Clean Architecture**.

---

## 📋 Table of Contents
- [Project Vision](#-project-vision)
- [Key Features](#-key-features)
- [Design Philosophy & UI/UX](#-design-philosophy--uiux)
- [App Architecture](#-app-architecture)
- [Tech Stack](#-tech-stack)
- [Getting Started](#-getting-started)
- [Project Presentation Pitch](#-project-presentation-pitch)
- [License](#-license)

---

## 👁️ Project Vision

### The Problem
Traditional health and wellness apps often rely on rigid, mechanical grids and heavy text-based layouts. This creates cognitive friction and fails to deliver the calming atmosphere users seek when attempting to reduce stress.

### The Solution
**WellnessApp** reimagines the mindfulness experience by combining ambient design aesthetics with fluid Android performance:
- **Instant Serenity**: Users are greeted with a bright, soothing pastel gradient canvas and organic floating video cards upon opening the app.
- **Immersive Content Delivery**: Short, high-definition video sessions categorized into **Yoga**, **Meditation**, **Soundscapes**, and **Breathing**.
- **Fluid Micro-Interactions**: Seamless Shared Element Transitions morph floating cards into full-screen video players without jarring layout shifts.

---

## ✨ Key Features

### 🌿 1. Curated Mindful Categories
- **Yoga**: Fluid morning flows and full-body stretch routines.
- **Meditation**: Deep inner peace and mindfulness sessions.
- **Soundscape**: Nature, forest river, and ocean wave acoustics with ambient visuals.
- **Breathing**: Guided breathwork resets and box breathing exercises.

### 🎨 2. Dynamic Floating Masonry Layout
- Cards are scattered naturally across horizontal zones (**Left**, **Center**, **Right**) instead of strict linear columns.
- Built using Compose `BoxWithConstraints` to ensure responsive, full-width distribution across all screen sizes.

### 🃏 3. Glassmorphic Video Cards
- **Scattered Organic Rotation**: Each card features a deterministic random rotation angle (between `-4.0°` and `+4.0°`) for a natural, floating aesthetic.
- **Solid Glass-Edge Border**: `1.dp` solid white border (`BorderStroke`) simulating precision glass edges.
- **Heavy Rounded Corners**: `32.dp` radius for a soft, premium feel.
- **Diffused Glowing Shadows**: Soft drop shadows with violet (`#7C4DFF`) and cyan (`#00B0FF`) ambient tints.
- **Glassmorphism Bottom Tag**: Semi-transparent gradient overlay displaying category tags and titles.

### 🔮 4. Floating Frosted Glass Filter Bar
- Disconnected from the screen bottom with `20.dp` margin for a floating effect over video content.
- Pill-shaped container (`CircleShape`) wrapped in frosted glass (`Color.White.copy(alpha = 0.6f)`) with a `1.dp` solid white border.
- Selected pills feature a vibrant Indigo gradient (`#6366F1` → `#4F46E5`), bold white typography, and glowing elevation shadows.

### 🎬 5. Shared Element Transitions & Media Playback
- **Compose Experimental Shared Elements**: Morphing transitions linking floating cards seamlessly to full-screen video views.
- **AndroidX Media3 ExoPlayer**: High-performance video streaming with automatic muting in preview cards and unmuted audio in full-screen detail view.

---

## 🎨 Design Philosophy & UI/UX

```
┌─────────────────────────────────────────────────────────┐
│              Bright Pastel Canvas Gradient              │
│       Soft Peach ➔ Light Lavender ➔ Pale Sky Cyan       │
│                                                         │
│     ┌───────────┐                     ┌───────────┐     │
│     │ Floating  │                     │ Floating  │     │
│     │ Card (-3°)│   ┌───────────┐     │ Card (+2°)│     │
│     └───────────┘   │ Floating  │     └───────────┘     │
│                     │ Card (+4°)│                       │
│                     └───────────┘                       │
│                                                         │
│       ┌─────────────────────────────────────────┐       │
│       │  🔮 Floating Frosted Glass Filter Bar   │       │
│       └─────────────────────────────────────────┘       │
└─────────────────────────────────────────────────────────┘
```

The app's design system adheres to four core principles:
1. **Calm Palette**: Bright pastel linear gradients (`Soft Peach`, `Blushing Pink`, `Light Lavender`, `Pale Cyan`) combined with soft radial ambient lighting spots.
2. **Organic Motion**: Gentle card rotations simulate physical floating cards on water or air.
3. **Glassmorphism**: Semi-transparent white surfaces with high-contrast borders evoke liquid glass interfaces.
4. **Focused Navigation**: Floating pill-shaped filter bar keeps navigation accessible while maximizing background visibility.

---

## 📐 App Architecture

WellnessApp follows **Clean Architecture** principles combined with the **MVVM (Model-View-ViewModel)** pattern and **Repository Pattern** to maintain separation of concerns, scalability, and testability.

```text
com.example.wellnessapp
├── data/
│   └── model/                  # Core Data Models (WellnessVideo)
├── di/
│   └── AppModule.kt            # Hilt Dependency Injection Module
├── domain/
│   └── usecase/
│       └── GetFilteredVideosUseCase.kt   # Business Logic & Filtering
├── repository/
│   └── VideoRepository.kt      # Data Repository Interface & Implementation
├── ui/
│   ├── components/
│   │   ├── FloatingMasonryView.kt   # Dynamic Floating Grid & Pastel Canvas
│   │   ├── FloatingVideoCard.kt    # Glassmorphic Card with Rotation & Glow Shadows
│   │   ├── BottomFilterBar.kt      # Floating Frosted Glass Bar
│   │   └── ExoPlayerView.kt        # AndroidX Media3 Video Player Component
│   ├── screens/
│   │   ├── HomeScreen.kt           # Main Floating Screen Layout
│   │   └── VideoDetailScreen.kt    # Fullscreen Detail View with Shared Element
│   └── theme/                  # Design System Colors, Typography, & Theme
└── viewmodel/
    └── HomeViewModel.kt        # State Management with StateFlow
```

---

## 🛠️ Tech Stack

| Component | Technology | Description |
| :--- | :--- | :--- |
| **Language** | Kotlin 100% | Primary programming language |
| **UI Framework** | Jetpack Compose | Modern declarative UI toolkit |
| **Design System** | Material 3 & Custom Glassmorphism | Custom styling, themes, and glassmorphic components |
| **Animation** | Compose Shared Element Transitions | `SharedTransitionLayout` & `AnimatedContent` |
| **Video Engine** | AndroidX Media3 ExoPlayer | `androidx.media3:media3-exoplayer` |
| **Architecture** | Clean Architecture + MVVM | Scalable, maintainable, and testable design |
| **Dependency Injection** | Hilt | Google's recommended DI library for Android |
| **Asynchronous Stream** | Kotlin Coroutines & `StateFlow` | Reactive UI state management |

---

## 🚀 Getting Started

### Prerequisites
- **Android Studio**: Jellyfish (2023.3.1) or newer
- **JDK**: 17
- **Android SDK Target**: 34+
- **Min SDK Version**: 24 (Android 7.0 Nougat)

### Installation & Build

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/wellnessapp-android.git
   cd wellnessapp-android
   ```

2. **Open in Android Studio**:
   Open Android Studio and choose **Open an Existing Project**, selecting the `wellnessapp-android` folder.

3. **Build the Debug APK**:
   ```bash
   ./gradlew app:assembleDebug
   ```

4. **Run on Emulator / Physical Device**:
   Select your target device in Android Studio and click **Run** (or press `Shift + F10`).

---

## 🎙️ Project Presentation Pitch

When presenting or demonstrating **WellnessApp** to stakeholders, clients, or evaluators:

1. **The Hook**: *"Mental health apps should feel calm from the first second. WellnessApp replaces stressful, rigid lists with a soothing, ambient visual canvas."*
2. **The Innovation**: *"We built a custom Floating Masonry Engine using Jetpack Compose that scatters video cards dynamically across full screen widths with realistic glassmorphism, soft rotational tilt, and ambient glowing shadows."*
3. **The Fluidity**: *"When a user taps a card, Compose Shared Element Transitions seamlessly morph the thumbnail directly into a full-screen Media3 video player without screen flickers or abrupt navigation jumps."*
4. **The Tech Foundation**: *"Under the hood, it's powered by Clean Architecture, Google Hilt dependency injection, and Kotlin StateFlow for enterprise-grade stability."*

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.
