# SystemUI ScreenDecorations Crash

## Symptom
SystemUI crashes once during boot with NPE in `ScreenDecorations.updateLayoutParams()`.
Recovery is automatic but causes a brief visual glitch.

## Root Cause
`ScreenDecorations` creates overlay views for rounded corners when
`rounded_corner_radius` > 0. A race condition occurs:
- `onSystemUiVisibilityChanged` fires before `setupDecorations()` inits `mOverlay`
- `updateLayoutParams()` calls `mOverlay.getLayoutParams()` on null reference

## Fix
Overlay `frameworks/base/packages/SystemUI/res/values/config.xml` with
`rounded_corner_radius = 0px`. This prevents `ScreenDecorations` from
creating overlay views at all (santoni has no rounded corners).

## Commit
- `2eee7a98` — overlay rounded_corner_radius = 0px
