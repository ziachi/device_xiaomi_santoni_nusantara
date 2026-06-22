# Graphics HAL Version Mismatch — Bootloop

## Symptom
Device bootloops. Boot animation plays but system never reaches launcher.

## Root Cause
`manifest.xml` declared `graphics.allocator@3.0` and `graphics.mapper@3.0`, but vendor only provides:
- `android.hardware.graphics.allocator@2.0-service`
- `android.hardware.graphics.mapper@2.1`

SurfaceFlinger tries to connect to `allocator@3.0` (because manifest says it exists), waits forever, never starts. `system_server` depends on SurfaceFlinger → Watchdog triggers → kills system_server → reboot → loop.

## Logcat Signature
```
W ServiceManagement: Waited one second for android.hardware.graphics.allocator@3.0::IAllocator/default
I ServiceManagement: getService: Trying again for android.hardware.graphics.allocator@3.0::IAllocator/default...
W ServiceManager: Service SurfaceFlinger didn't start. Returning NULL
W Gralloc3: mapper 3.x is not supported
D ComposerHal: failed to get mapper 3.0 service, falling back to mapper 2.0
```

## Fix
Remove 3.0 declarations from `manifest.xml`:
- Removed `<version>3.0</version>` from allocator section (keep 2.0)
- Removed `<fqname>@3.0::IMapper/default</fqname>` from mapper section (keep 2.1)

## Note
The upstream device tree (`androidsantoni/device_xiaomi_santoni` branch `rr-10-dev`) had these 3.0 entries. Santoni vendor blobs (MSM8937, Dec 2020) only support allocator@2.0 and mapper@2.1.

ComposerHal gracefully falls back to mapper 2.0, but the allocator service manager does NOT fall back — it retries forever.
