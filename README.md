# Renovatio

Renovatio is a Fabric mod for Minecraft 1.20.1 that focuses on combat and attribute rebalancing. The project uses mixins to tune entity toughness, damage calculations, status effects, creeper behavior, tool materials, and player attribute handling. It also includes a data generator entrypoint for producing mod data assets during development. The repository currently contains the build tooling and resource configuration needed to assemble the mod.

## Highlights

- **Mixin-driven gameplay tweaks:** Mixins target combat, status effects, mob behavior, tool materials, and player attribute logic. See the mixin configuration files for the full list of applied mixins.
- **Fabric data generation:** Includes a Fabric data generation entrypoint for producing data assets.
- **Attribute-focused dependencies:** Pulls in libraries such as AdditionalEntityAttributes, Reach Entity Attributes, Puffish Attributes, and Trinkets to support richer stat systems.

## Requirements

- Java 17
- Minecraft 1.20.1
- Fabric Loader (see `gradle.properties` for the exact loader version)

## Mod Dependencies

The Gradle build declares the following runtime/mod dependencies:

- Fabric API
- Reach Entity Attributes
- AdditionalEntityAttributes
- Ranged Weapon API
- Puffish Attributes
- Trinkets

Refer to `build.gradle` and `gradle.properties` for exact versions.

## Project Layout

- `src/main/resources/fabric.mod.json`: Core Fabric metadata, entrypoints, and dependencies.
- `src/main/resources/renovatio.mixins.json`: Server/common mixin configuration list.
- `src/client/resources/renovatio.client.mixins.json`: Client mixin configuration list.
- `build.gradle`: Gradle build configuration and dependency declarations.

## Building

```bash
./gradlew build
```

The compiled `.jar` will be located in `build/libs/`.

## Data Generation

Run Fabric data generation with:

```bash
./gradlew runDatagen
```

## Notes

- The mod is currently marked as `All-Rights-Reserved` in `fabric.mod.json`.
- If you add new mixins, update the appropriate mixin JSON file and keep the package paths in sync.
