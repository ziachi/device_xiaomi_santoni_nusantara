# Missing Launcher — "Phone is starting" Stuck

## Symptom
After flashing v1, device boots to lock screen but after unlock shows
"Phone is starting" indefinitely. SystemUI works (status bar, quick settings)
but no home screen appears.

## Root Cause
1. No launcher was included in `PRODUCT_PACKAGES` in v1
2. v2 initial fix added `Launcher3QuickStep` but it has `LOCAL_PRODUCT_MODULE := true`
3. Santoni is a legacy A-only device without a proper product partition
4. `ALLOW_MISSING_DEPENDENCIES=true` silently skipped the module
5. Result: APK intermediates created but never compiled/installed

## Fix
- Use `Launcher3` instead of `Launcher3QuickStep` in device.mk
- `Launcher3` installs to system partition (no `LOCAL_PRODUCT_MODULE`)
- Works correctly on legacy A-only devices

## Commits
- `f1292bad` — Initial attempt (Launcher3QuickStep, didn't work)
- `aef6a5f9` — Final fix (Launcher3)
