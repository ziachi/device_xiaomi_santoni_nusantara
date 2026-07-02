# Changelog — Nusantara EOL (santoni)

## v3 — dex2oat Fix, Pixel 9 Spoof, Launcher3 Fork (02 Jul 2026)

| # | Commit | Change |
|---|--------|--------|
| #7 | `18de74f4` | Fix dex2oat crash (`bg-dexopt=speed-profile`, `install=quicken`) + Pixel 9 spoof |
| #8 | `037740cb` | Update nad.dependencies: tambah mapping frameworks_base + Launcher3 fork |

**Launcher3 fork (repo terpisah `ziachi/android_packages_apps_Launcher3_nusantara`):**

| Commit | Change |
|--------|--------|
| `a76a7cd` | Fix Launcher3QuickStep silently skipped (hapus `LOCAL_PRODUCT_MODULE` + `LOCAL_REQUIRED_MODULES` di `Android.mk`) |

**Perubahan dari v2:**
- Tidak perlu lagi patch manual `Android.mk` — sudah di-fork langsung
- Local manifest `santoni.xml` sudah include semua 6 repo (device, vendor, kernel, clang, frameworks_base, Launcher3)
- dex2oat tidak akan crash lagi di Shopee dan app berat lainnya
- Device teridentifikasi sebagai Pixel 9 (untuk Play Store compatibility)

## v2 — Bug Fixes & FLAG_SECURE Bypass (29 Jun 2026)

| # | Commit | Change |
|---|--------|--------|
| #1 | `f1292bad` | Add Launcher3QuickStep to PRODUCT_PACKAGES (initial attempt — silently skipped) |
| #2 | `3ae05389` | Set maintainer name to ziachi |
| #3 | `2eee7a98` | Fix SystemUI ScreenDecorations crash (rounded_corner_radius overlay) |
| #4 | `aef6a5f9` | Switch to Launcher3 (still skipped — same root cause) |
| #5 | `3c6f75d1` | Update docs |
| #6 | `a8fa0295` | Revert to Launcher3QuickStep + document Android.mk fix |

**Framework patch (repo terpisah `ziachi/android_frameworks_base_nusantara`):**

| Commit | Change |
|--------|--------|
| `763aca2a` | Hardcode FLAG_SECURE bypass (`isSecureLocked()` → always false) untuk scrcpy/ADB |

## v1 — Initial Bring-up (22 Jun 2026)

| # | Commit | Change |
|---|--------|--------|
| #1 | `415e1540` | Adapt device tree for NusantaraProject (NAD) Android 10 |
| #2 | `b490bf01` | sepolicy: remove duplicate hal_perfcallback_hwservice |
| #3 | `0cda3250` | device.mk: fix dexpreopt duplicate compiler filter |
| #4 | `f8e795b0` | BoardConfig: disable artifact path requirements |

## v4 — 02-07-2026

### Prebuilt Luuvy Kernel + Spectrum Profiles

**Kernel:**
- Switched to prebuilt Luuvy C.4.0 EOL kernel (`Image.gz-dtb`)
- No longer building kernel from source — faster build time
- Requires local patches to `BoardConfigKernel.mk` + `kernel.mk` (see README)

**Spectrum Profile Manager:**
- Added `init.spectrum.rc` with 4 profiles:
  - Balance (schedutil, GPU 216-475MHz)
  - Performance (performance governor, GPU 300-475MHz)
  - Battery (powersave, GPU 216-450MHz)
  - Gaming (performance governor, GPU 375-475MHz)
- Bridges: `persist.spectrum.profile` (0-3) ↔ `persist.luuvy.profile`
- Franco Kernel Manager compatible

**XiaomiAddon:**
- Added Spectrum QS Tile (cycle profiles from Quick Settings)
- Added Spectrum preference in XiaomiAddon settings
- BootReceiver syncs profile on boot

**SEPolicy:**
- Added `spectrum_prop` + `luuvy_prop` property types
- system_app + init allowed to set/get
