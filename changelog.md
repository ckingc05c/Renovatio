# Changelog

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