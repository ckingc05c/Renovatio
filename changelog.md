# Changelog
## [1.0.5.45.6.21] - 2025-09-21
### Changed
- Updated **Resistance** damage reduction formula:
    - Old formula: `20% * level` (Resistance I = 20%, II = 40%, etc.).
    - New formula: `(0.80 ^ level)` (exponential scaling).
        - Resistance I → 20% reduction (80% taken).
        - Resistance II → 36% reduction (64% taken).
        - Resistance III → ~49% reduction (51% taken).
        - Resistance IV → ~59% reduction (41% taken).
    - Prevents Resistance from granting full damage immunity at high levels.
- Added stat tracking for resisted damage:
    - **DAMAGE_RESISTED** for defenders with Resistance.
    - **DAMAGE_DEALT_RESISTED** for attackers hitting a resistant target.
## [1.0.5.45.6.20] - 2025-09-21
- ### Removed
- Reverted **Resistance** scaling Changes
## [1.0.5.45.6.19] - 2025-09-21
### Changed
- Reworked **Armor Durability Formula**:
    - New formula: `(toolDurability / 24) * pieceBaseValue + (160 / 3)`.
    - Durability is now rounded to the nearest **4**.
    - This change boosts the durability of lower-tier armors (like Iron and Copper) while keeping higher-tier armors (Diamond, Netherite) balanced.

## [1.0.5.45.6.18] - 2025-09-19
### Added
- Added framework for **Under Armor Trinket Sets**.
- Added **Damage Boost** effect, which increases attack damage using the formula: `4 + 2 * amplifier`.

### Changed
- Adjusted **Strength** effect: damage bonus changed from **+30% per level** to **+20% per level**.
- Adjusted **Weakness** effect: damage reduction changed from **-40% per level** to **-30% per level**, with a maximum reduction of **-99%** at levels above 4.


## [1.0.5.45.6.12] - 2025-07-21
### Changed
- Updated the `/stage` command with more robust functionality:
  - `/stage set <stage> [lock|unlock]` to set the stage and optionally lock or unlock progression.
  - `/stage lock` and `/stage unlock` to control stage progression locking.
  - `/stage get` to view the current stage and its lock status.

## [1.0.5.45.6.4] - 2025-07-17
### Changed
- Changed formula for ranged weapon stats for all Bow and Crossbow types.

## [1.0.5.45.6.3] - 2025-07-17
### Added
- Added Wooden, Golden, Iron, Refined Iron, Diamond, and Netherite Longbows.

### Removed
- Removed Longbow and Velocity Longbow from test items.

## [1.0.5.45.6.2] - 2025-07-16
### Added
- Added Velocity Longbow to test items. (Velocity = 5)

## [1.0.5.45.6.1] - 2025-07-16
### Added
- Added Velocity Longbow to test items. (Velocity = 1)

## [1.0.5.45.6] - 2025-07-16
### Changed
- Refactored item and block registers.

### Added
- Added Longbow to test items.

## [1.0.5.45] - 2025-07-01
### Changed
- Made **Strength** and **Weakness** effects multiplicative:
  - **Strength** now increases attack damage by **+30% per level**.
  - **Weakness** now reduces attack damage by **-40% per level**.
- **Strength** now also affects **ranged attacks**, not just melee.


## [1.0.5.44] - 2025-07-01
### Fixed
- Corrected tooltips for **Strength** and **Weakness** potions to accurately reflect their effects.

### Changed
- Adjusted **Resistance** scaling to `15% * level` for more consistent damage reduction.

## [1.0.5.43] - 2025-07-01
### Changed
- Adjusted **Instant Health** scaling from `2 * 2^level` to `4 * level`.
- Adjusted **Instant Damage** scaling from `3 * 2^level` to `6 * level`.

## [1.0.5.42] - 2025-07-01
### Removed
- Reverted changes to Creeper fuse times.

## [1.0.5.41] - 2025-07-01
### Changed
- Updated Creeper explosion strengths and fuse times to better reflect progression and difficulty scaling.

## [1.0.5.40] - 2025-07-01
### Changed
- Further tuned the damage players receive based on their current game stage for better balance.

## [1.0.5.39] - 2025-07-01
### Added
- Increased damage for non-melee attacks based on the player's progression.