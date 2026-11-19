# Legends of Arcadia

Welcome to **Legends of Arcadia**, a 2D action-adventure RPG built entirely in pure Java using standard **Java2D** canvas rendering. 

Big props to [RyiSnow](https://www.youtube.com/@RyiSnow) for his inspiring game development tutorials, which served as the foundation for this project. Building on those core concepts such as tile-based collision detection and object oriented entity architecture, I developed this game to deepen my Java programming skills while bringing my own gameplay ideas and custom hand-drawn pixel art to life.

## Title Screen

<img width="1250" height="749" alt="Pasted 24-10-2026 at 16 08 57" src="https://github.com/user-attachments/assets/29f976e5-9ae2-4a0f-9057-c8f36cd83049" />

Inspired by classic adventure games, the title screen features mystical, ancient Greek-Style architecture to immediately set a sense of mystery and discovery. Because creating a high-resolution background illustration at that scale was outside the scope of my work, I generated the title background with AI, hopefully it does not count as AI slop, while keeping all sprites, tiles and UI elements 100% hand-drawn pixel art.

## Game World & Gameplay

<img width="1251" height="748" alt="Pasted 24-10-2026 at 16 04 11" src="https://github.com/user-attachments/assets/bf68b7a5-39b2-4317-889c-73465652ff61" />
<img width="1248" height="748" alt="Pasted 24-10-2026 at 16 06 22" src="https://github.com/user-attachments/assets/d0eaeea4-5b67-4247-801c-84e21a6e3b4d" />

You find yourself stranded on a mysterious main island filled with secrets, items and interconnected areas to explore. As you roam the world, you can enter buildings to uncover new locations and travel via portals to smaller surrounding islands, each hiding unique loot. Along the way, you can find various items scattered across the map to either equip new character abilities and advanced weapons or consume to aid you on your journey. To survive, you will need to battle bots patrolling the islands while interacting with wandering NPCs to trade gear, upgrade your arsenal and progress through your adventure.

## Tech Stack & Architecture

* Language: Java `java.awt` & `javax.swing` no external game engines
* Rendering: Custom 2D canvas drawing `Graphics2D` with tile based world rendering and sprite animations
* Core Systems: Custom entity/object management, collision handling, inventory/equipment system and NPC interaction logic
* Art: Custom hand drawn pixel art assets (characters, tilesets and items)

## License
This project is intended for learning and personal use. Feel free to use and experiment.
