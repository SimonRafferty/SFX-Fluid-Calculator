$icon192 = [Convert]::ToBase64String([IO.File]::ReadAllBytes('C:\Users\simon\Desktop\SFX Fluid Calc - App\android\app\src\main\res\mipmap-xxxhdpi\ic_launcher.png'))
$icon512 = [Convert]::ToBase64String([IO.File]::ReadAllBytes('C:\Users\simon\Desktop\SFX Fluid Calc - App\Icon_512.png'))

$manifest = @"
{
  "name": "SFX Fluid Calculator",
  "short_name": "SFX Calc",
  "description": "Professional hydraulic and pneumatic calculations for special effects work",
  "icons": [
    {
      "src": "data:image/png;base64,$icon192",
      "sizes": "192x192",
      "type": "image/png"
    },
    {
      "src": "data:image/png;base64,$icon512",
      "sizes": "512x512",
      "type": "image/png"
    }
  ],
  "start_url": "/",
  "display": "standalone",
  "background_color": "#f9fafb",
  "theme_color": "#2563eb",
  "categories": ["engineering", "productivity"]
}
"@

$bytes = [System.Text.Encoding]::UTF8.GetBytes($manifest)
$base64 = [Convert]::ToBase64String($bytes)
Write-Output $base64
