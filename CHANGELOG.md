# Changelog — Nusantara EOL (santoni)

## v2 — Bug Fixes & FLAG_SECURE Bypass (29 Jun 2026)

| # | Commit | Change |
|---|--------|--------|
| #1 | `f1292bad` | Add Launcher3QuickStep to PRODUCT_PACKAGES (initial attempt — silently skipped) |
| #2 | `3ae05389` | Set maintainer name to ziachi |
| #3 | `2eee7a98` | Fix SystemUI ScreenDecorations crash (rounded_corner_radius overlay) |
| #4 | `aef6a5f9` | Switch to Launcher3 (still skipped — same root cause) |
| #5 | `3c6f75d1` | Update docs |
| #6 | (pending) | Revert to Launcher3QuickStep + document Android.mk fix for silent skip |

**Framework patch (separate repo):**

| Commit | Change |
|--------|--------|
| `763aca2a` | Hardcode FLAG_SECURE bypass (`isSecureLocked()` → always false) for scrcpy/ADB display |

**Required local patch (applied after repo sync):**
- `packages/apps/Launcher3/Android.mk`: Remove `LOCAL_PRODUCT_MODULE` + `LOCAL_REQUIRED_MODULES` from Launcher3 and Launcher3QuickStep sections. See `patches/launcher3-quickstep-fix.patch`.

## v1 — Initial Bring-up (22 Jun 2026)

| # | Commit | Change |
|---|--------|--------|
| #1 | `415e1540` | Adapt device tree for NusantaraProject (NAD) Android 10 |
| #2 | `b490bf01` | sepolicy: remove duplicate hal_perfcallback_hwservice |
| #3 | `0cda3250` | device.mk: fix dexpreopt duplicate compiler filter |
| #4 | `f8e795b0` | BoardConfig: disable artifact path requirements |
| #5 | `cdd1391d` | Enable ADB by default for debugging |
| #6 | `deda9b62` | Use custom release signing keys (CN=ziachi) |
| #7 | `6f7d8b02` | manifest: fix graphics HAL version mismatch causing bootloop |
