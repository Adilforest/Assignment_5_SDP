# Software Design Patterns — Assignment 5 (AITU)

![Java](https://img.shields.io/badge/Java-21-007396?logo=openjdk&logoColor=white)

## Overview

A turn-based console combat game ("Goblin's Nightmare") written in Java as part of the Software Design Patterns course at Astana IT University. The assignment demonstrates several GoF behavioral patterns applied to a simple game loop: a hero fights a goblin, choosing an attack style at the start and an action each turn.

The entry point for the game is `game.SimpleGameLoop` (not `Main`).

## Patterns / Concepts implemented

- **Strategy** — `AttackStrategy` interface with three concrete implementations (`MeleeStrategy`, `RangedStrategy`, `MagicStrategy`). The player picks one at startup; `Character` delegates all attack calls to the injected strategy. Fully wired and active at runtime.

- **Template Method** — abstract `GameAction` defines the fixed skeleton `executeAction()` (prepare → perform → finish); concrete subclasses `AttackAction`, `DefendAction`, and `HealAction` each override only `performAction()`. Fully wired and active at runtime.

- **State** — `CharacterState` interface with three concrete states (`NormalState`, `PoweredUpState`, `DefeatedState`) that vary attack/heal/defend behavior per state. Declared and complete as classes, but not yet wired into the `Character` class (no `state` field or transitions in the runtime loop).

- **Visitor** — `CharacterVisitor` interface and `HealingVisitor` implementation skeleton. Declared but not wired into the runtime flow (`visit()` body is empty).

- **OOP fundamentals** — abstraction via interfaces, inheritance (`GameAction` hierarchy), encapsulation in `Character`, and polymorphism throughout.

## Project structure

```
src/
├── Main.java                          # IntelliJ default stub (not the game entry point)
├── game/
│   └── SimpleGameLoop.java            # Game entry point (main method)
├── actions/
│   ├── GameAction.java                # Abstract base — Template Method
│   ├── AttackAction.java
│   ├── DefendAction.java
│   └── HealAction.java
└── characters/
    ├── Character.java                 # Domain entity; holds AttackStrategy
    ├── strategies/
    │   ├── AttackStrategy.java        # Strategy interface
    │   ├── MeleeStrategy.java         # 5 damage
    │   ├── RangedStrategy.java        # 4 damage
    │   └── MagicStrategy.java         # 6 damage
    ├── states/
    │   ├── CharacterState.java        # State interface
    │   ├── NormalState.java
    │   ├── PoweredUpState.java
    │   └── DefeatedState.java
    └── visitor/
        ├── CharacterVisitor.java      # Visitor interface
        └── HealingVisitor.java
```

## How to run

**From the command line** (requires JDK 21+):

```bash
# Compile all sources
mkdir -p out
find src -name "*.java" | xargs javac -d out

# Run the game
java -cp out game.SimpleGameLoop
```

**From IntelliJ IDEA:**

Open the project, then run `game.SimpleGameLoop` (right-click → Run).

**Gameplay:**

1. Enter a name for your hero.
2. Pick an attack strategy (1 = Melee, 2 = Ranged, 3 = Magic).
3. Each turn choose an action: 1 = Attack, 2 = Defend, 3 = Heal.
4. Reduce the goblin's HP to zero before yours runs out.

---

Adil Ormanov — [GitHub](https://github.com/Adilforest)
