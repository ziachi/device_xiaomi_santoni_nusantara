# Launcher3QuickStep Silently Skipped by Build System

## Symptom
- `Launcher3QuickStep` in `PRODUCT_PACKAGES` but APK not in final ZIP
- Build succeeds without error (no visible failure)
- Phone boots to blank screen with no launcher

## Root Cause
In `packages/apps/Launcher3/Android.mk`, the Launcher3QuickStep section has:
```makefile
LOCAL_PRODUCT_MODULE := true
LOCAL_REQUIRED_MODULES := privapp_whitelist_com.android.launcher3
LOCAL_REQUIRED_MODULES += privapp_whitelist_com.android.launcher3-ext.xml
```

The module `privapp_whitelist_com.android.launcher3` does not exist in Nusantara
EOL branch 10. When building with `ALLOW_MISSING_DEPENDENCIES=true`, the build
system silently skips the entire Launcher3QuickStep module because its required
modules cannot be satisfied.

## Fix
Remove `LOCAL_PRODUCT_MODULE := true` and `LOCAL_REQUIRED_MODULES` lines from
both `Launcher3` and `Launcher3QuickStep` sections in `packages/apps/Launcher3/Android.mk`.

This is a local patch applied after `repo sync`. See `patches/launcher3-quickstep-fix.patch`
in the device tree for the exact changes needed.

## Verification
- Run `mka Launcher3QuickStep` explicitly — should compile without error
- Check `out/target/product/santoni/system/priv-app/Launcher3QuickStep/` exists
- After `mka nad`, verify in `filesystem_config.txt`:
  ```
  system/priv-app/Launcher3QuickStep/Launcher3QuickStep.apk
  ```

## Timeline
- v2 build #1: Launcher3QuickStep in device.mk → not in ZIP (silently skipped)
- v2 build #2: Switched to Launcher3 → also not in ZIP (same issue)
- v2 build #3: Patched Android.mk + explicit mka → APK built
- v2e build: Full rebuild with patch → APK confirmed in system image
