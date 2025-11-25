# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

SFX Fluid Calculator is a single-file Progressive Web App (PWA) for professional hydraulic and pneumatic calculations used in special effects work. The entire application is contained in `SFX_Fluid_Calculator.html`.

## Architecture

**Single-file HTML application** with embedded CSS and JavaScript:
- Tailwind CSS loaded from CDN (`cdn.tailwindcss.com`)
- Custom print styles for generating professional reports
- PWA metadata (manifest, icons) embedded as base64/data URIs

**Three calculator tabs:**
1. **Hose & Elbow Calculator** - Pressure drop and flow capacity through hoses using Darcy-Weisbach equation and Swamee-Jain friction factor
2. **Cylinder Calculator** - Hydraulic/pneumatic cylinder forces, velocities, timing, and Euler buckling analysis
3. **Cannon Calculator** - Gas expansion simulation for pneumatic cannons using ideal gas laws and choked-flow orifice calculations

**Key calculation methods:**
- Hose: Darcy-Weisbach pressure drop, Hagen-Poiseuille laminar flow, velocity limits
- Cylinder: Pascal's law for forces, Euler critical load for buckling
- Cannon: Ideal gas law (PV=nRT), choked flow, isothermal expansion (based on Alex Fabre's cannon calculator)

## Development

No build system - edit `SFX_Fluid_Calculator.html` directly and open in browser to test.

**To test changes:** Open the HTML file in a web browser. Use browser DevTools (F12) for debugging JavaScript.

**Unit systems:** Supports metric (bar, mm, L/min) and imperial (PSI, in, GPM) with automatic conversion functions (`convertToMM`, `convertToLPM`, `convertToPa`).

## Key Code Sections

The JavaScript is in a single `<script>` block near the end of the file:
- Unit conversion functions and BSP size lookup table near the start
- Tab switching logic around line 1003
- Calculator functions: `calculateCylinder()`, hose calculations, `calculateCannon()` (if present)
- Display functions: `displayCylinderResults()`, etc.
- Print preparation: `preparePrintView()`
