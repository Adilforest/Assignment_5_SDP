# Software Design Patterns — Assignment 5 (AITU)

![Java](https://img.shields.io/badge/Java-21-007396?logo=openjdk&logoColor=white)

## Overview

A turn-based console combat game ("Goblin's Nightmare") written in Java as part of the Software Design Patterns course at Astana IT University. The assignment demonstrates four GoF behavioral patterns applied to a simple game loop: a hero fights a goblin, choosing an attack style at the start and an action each turn.

The entry point for the game is `game.SimpleGameLoop` (not `Main`).

## Patterns implemented

All four patterns are fully wired and active at runtime.

### Strategy
`AttackStrategy` interface with three concrete implementations (`MeleeStrategy`, `RangedStrategy`, `MagicStrategy`). The player picks one at startup; `Character` delegates all attack calls to the injected strategy. Each strategy deals a different amount of damage (5 / 4 / 6).

### Template Method
Abstract `GameAction` defines the fixed skeleton `executeAction()` — prepare → perform → finish. Concrete subclasses `AttackAction`, `DefendAction`, and `HealAction` each override only `performAction()`. Every hero action in the loop goes through this skeleton.

### State
`CharacterState` interface with three concrete states:

| State | Behaviour |
|---|---|
| `NormalState` | Default — standard attack narration, heals 5 HP, standard defend. |
| `PoweredUpState` | Activated when the goblin drops to ≤ 50 % health — enhanced narration, heals 10 HP, reinforced defend. |
| `DefeatedState` | Set on death — blocks all actions with an appropriate message. |

`Character` holds a `CharacterState` field (initialised to `NormalState`). Every call to `attack()`, `heal()`, or `defend()` is routed through the active state first. The game loop drives transitions: `NormalState → PoweredUpState` when the goblin falls to half health, and `→ DefeatedState` on death.

### Visitor
`CharacterVisitor` interface with `HealingVisitor` as the concrete visitor. `HealingVisitor.visit(Character)` restores a configurable amount of HP and prints a confirmation line. `Character.accept(CharacterVisitor)` enables double-dispatch. The game loop invokes `hero.accept(blessingVisitor)` after every heal turn, granting a bonus restoration on top of the state-driven heal.

### OOP fundamentals
Abstraction via interfaces, inheritance (`GameAction` hierarchy), encapsulation in `Character`, and polymorphism throughout.

## Project structure

```
src/
├── Main.java                          # IntelliJ default stub (not the game entry point)
├── game/
│   └── SimpleGameLoop.java            # Game entry point (main method)
├── actions/
│   ├── GameAction.java                # Abstract base — Template Method skeleton
│   ├── AttackAction.java
│   ├── DefendAction.java
│   └── HealAction.java
└── characters/
    ├── Character.java                 # Domain entity; holds strategy, state, accept()
    ├── strategies/
    │   ├── AttackStrategy.java        # Strategy interface
    │   ├── MeleeStrategy.java         # 5 damage
    │   ├── RangedStrategy.java        # 4 damage
    │   └── MagicStrategy.java         # 6 damage
    ├── states/
    │   ├── CharacterState.java        # State interface
    │   ├── NormalState.java           # Default — heals 5 HP
    │   ├── PoweredUpState.java        # Triggered at half goblin HP — heals 10 HP
    │   └── DefeatedState.java         # Terminal — all actions blocked
    └── visitor/
        ├── CharacterVisitor.java      # Visitor interface
        └── HealingVisitor.java        # Restores configurable HP; called on every heal
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
4. The hero powers up automatically when the goblin reaches half health.
5. Reduce the goblin's HP to zero before yours runs out.

---

Adil Ormanov — [GitHub](https://github.com/Adilforest)
