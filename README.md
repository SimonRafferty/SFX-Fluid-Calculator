# SFX Fluid Calculator

A hydraulic and pneumatic calculation tool designed for special effects crews working with fluid-powered systems and gas cannons.

## Overview

This calculator helps SFX crews calculate and analyse the performance of hydraulic and pneumatic cylinders, as well as gas cannons. Unlike many other apps, it takes into account flow restrictions through hoses and fittings - a critical factor that significantly affects real-world performance.

Available as both a web app (PWA) and an Android app, all contained in a single HTML file for easy deployment and offline use.

## Features

### Three Specialized Calculators

1. **Hose & Elbow Calculator**
   - Calculate pressure drop through hoses using the Darcy-Weisbach equation
   - Determine maximum flow capacity based on velocity limits
   - Account for elbow losses and hose roughness
   - Supports both laminar and turbulent flow regimes

2. **Cylinder Calculator**
   - Calculate extension and retraction forces using Pascal's law
   - Determine velocities and timing based on flow rates
   - Includes Euler buckling analysis for compression loads with safety factors
   - Can automatically use hose calculator results to account for real-world flow restrictions

3. **Cannon Calculator**
   - Gas expansion simulation using ideal gas laws
   - Models compressed gas transfer through choked-flow orifices
   - Calculates piston velocity, exit velocity, and maximum throw height
   - In testing, a gas cannon efficiency of around **70% has been found to match actual performance** across several tests - though every situation and rig is different, this provides useful guidance on where to start

## Why Flow Restrictions Matter

Most calculators assume unlimited flow to cylinders or cannons, but in real SFX setups, hoses, fittings, and port sizes create significant restrictions. This calculator:

- Models pressure drop through your actual hose runs
- Calculates the true available flow after losses
- Shows how this affects cylinder speeds and cannon performance
- Helps you size hoses and ports correctly for your application

## How the Calculations Work

### Hose Calculations
- **Darcy-Weisbach equation** for pressure drop in pipes
- **Swamee-Jain approximation** for friction factor calculation
- **Reynolds number** analysis to determine flow regime (laminar vs turbulent)
- Learn more: [Darcy-Weisbach equation](https://en.wikipedia.org/wiki/Darcy%E2%80%93Weisbach_equation)

### Cylinder Calculations
- **Pascal's law** (P = F/A) for force calculations
- **Flow continuity equation** (Q = A × v) for velocities
- **Euler's buckling formula** for compression stability analysis
- Learn more: [Pascal's law](https://en.wikipedia.org/wiki/Pascal%27s_law) | [Euler buckling](https://en.wikipedia.org/wiki/Euler%27s_critical_load)

### Cannon Calculations
- **Ideal gas law** (PV = nRT) for gas expansion modeling
- **Choked flow** calculations for orifice flow rates
- **Isothermal expansion** approximation with efficiency factors
- Based on Alex (Lec) Fabre's cannon calculator methodology
- Learn more: [Choked flow](https://en.wikipedia.org/wiki/Choked_flow) | [Ideal gas law](https://en.wikipedia.org/wiki/Ideal_gas_law)

## Unit Support

Switch between metric and imperial units:
- **Metric**: bar, mm, L/min, meters, °C
- **Imperial**: PSI, inches, GPM, feet, °F

## Fluid Types

Supports multiple fluid types with accurate viscosity values:
- Hydraulic oil (ISO 32)
- Water
- Air
- Nitrogen

## Usage Tips

1. **Start with the Hose Calculator** - Calculate your hose system first to understand flow limitations
2. **Use Hose Values in Cylinder Calc** - Enable the checkbox to automatically adjust for pressure drop and flow restrictions
3. **Consider Safety Factors** - The cylinder calculator includes buckling analysis with adjustable safety factors (minimum 5.0 recommended)
4. **Print Professional Reports** - Each calculator can generate formatted reports for planning and documentation

## Files Included

- `SFX_Fluid_Calculator.html` - Standalone web app (works offline, can be installed as PWA)
- `android/` - Complete Android Studio project for building native Android app
- `Icon_192.png`, `Icon_512.png` - App icons for web and Android

## Technical Notes

- All calculations use SI units internally with automatic conversion for display
- BSP (British Standard Pipe) fitting sizes included for common hydraulic fittings
- Velocity limits follow industry standards (3.0 m/s continuous, up to 9.1 m/s peak)
- Cannon calculations use discrete time steps (dt=0.001s) for accurate simulation
- System efficiency parameters account for friction, heat transfer, and incomplete gas expansion

## Contributing

Found an issue or have a suggestion? Feel free to open an issue or submit a pull request.

## Support

If you find this tool useful, consider [buying me a coffee](https://ko-fi.com/psidesign)!

## License

This project is open source and available for use by the SFX community.

---

**Remember**: These calculations provide guidance and starting points. Always conduct proper testing and safety checks for your specific setup. Every rig is different, and real-world conditions may vary from theoretical calculations.
