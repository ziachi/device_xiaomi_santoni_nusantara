# Device Tree for Xiaomi Redmi 4X (santoni) — NusantaraProject

## ROM Info

| | |
|---|---|
| ROM | NusantaraProject EOL (Android 10) |
| Branch | `nad-10-dev` |
| Build type | UNOFFICIAL |
| Maintainer | ziachi |
| Signed | Custom release keys (CN=ziachi) |
| Security patch | System: 2023-06-05 · Vendor: 2020-12-05 |
| FLAG_SECURE | Disabled (framework patch for scrcpy/ADB) |
| Device spoof | Pixel 9 (model/brand/manufacturer/fingerprint) |

## Spec Sheet

| Feature                 | Specification                     |
| :---------------------- | :-------------------------------- |
| CPU                     | Octa-core 1.4 GHz Cortex-A53      |
| Chipset                 | Qualcomm MSM8940 Snapdragon 435   |
| GPU                     | Adreno 505                        |
| Memory                  | 2/3 GB                            |
| Shipped Android Version | 6.0.1                             |
| Storage                 | 16/32 GB                          |
| MicroSD                 | Up to 256 GB                      |
| Battery                 | 4100 mAh (non-removable)          |
| Dimensions              | 139 x 69 x 8.65 mm                |
| Display                 | 720 x 1280 pixels, 5" (~294 PPI)   |
| Rear Camera             | 13 MP, LED flash                  |
| Front Camera            | 5 MP                              |
| Release Date            | May 2017                          |

## Repos

Semua repo yang dibutuhkan untuk build. Mapping lengkap juga ada di `nad.dependencies`.

| Repo | Branch | Keterangan |
|------|--------|------------|
| [device_xiaomi_santoni_nusantara](https://github.com/ziachi/device_xiaomi_santoni_nusantara/tree/nad-10-dev) | `nad-10-dev` | Device tree (repo ini) |
| [vendor_xiaomi_santoni_nusantara](https://github.com/ziachi/vendor_xiaomi_santoni_nusantara/tree/10) | `10` | Vendor blobs |
| [kernel_xiaomi_santoni_nusantara](https://github.com/ziachi/kernel_xiaomi_santoni_nusantara/tree/luuvy-4.9) | `luuvy-4.9` | Kernel (Luuvy) |
| [android_frameworks_base_nusantara](https://github.com/ziachi/android_frameworks_base_nusantara/tree/nad-10-dev-santoni) | `nad-10-dev-santoni` | Framework (FLAG_SECURE bypass) |
| [android_packages_apps_Launcher3_nusantara](https://github.com/ziachi/android_packages_apps_Launcher3_nusantara/tree/10) | `10` | Launcher3 (build fix) |
| [android_prebuilts_clang_host_linux-x86_clang-6875598](https://github.com/crdroidandroid/android_prebuilts_clang_host_linux-x86_clang-6875598/tree/10.0) | `10.0` | Clang r399163b (crdroid) |

## Build di VPS Baru

### 1. Init repo + local manifest

```bash
repo init -u https://github.com/NusantaraProject-ROM/android_manifest -b 10 --depth=1

mkdir -p .repo/local_manifests
```

Buat file `.repo/local_manifests/santoni.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<manifest>
  <project path="device/xiaomi/santoni"
           name="ziachi/device_xiaomi_santoni_nusantara"
           remote="github"
           revision="nad-10-dev" />

  <project path="vendor/xiaomi/santoni"
           name="ziachi/vendor_xiaomi_santoni_nusantara"
           remote="github"
           revision="10" />

  <project path="kernel/xiaomi/msm8937"
           name="ziachi/kernel_xiaomi_santoni_nusantara"
           remote="github"
           revision="luuvy-4.9" />

  <project path="prebuilts/clang/host/linux-x86/clang-r399163b"
           name="crdroidandroid/android_prebuilts_clang_host_linux-x86_clang-6875598"
           remote="github"
           revision="10.0" />

  <remove-project name="NusantaraProject-ROM/android_frameworks_base" />
  <project path="frameworks/base"
           name="ziachi/android_frameworks_base_nusantara"
           remote="github"
           revision="nad-10-dev-santoni" />

  <remove-project name="platform/packages/apps/Launcher3" />
  <project path="packages/apps/Launcher3"
           name="ziachi/android_packages_apps_Launcher3_nusantara"
           remote="github"
           revision="10" />
</manifest>
```

### 2. Sync

```bash
repo sync -c -j$(nproc) --force-sync --no-tags --no-clone-bundle
```

### 3. Signing keys

Taruh signing keys di `vendor/extra/`:

```
vendor/extra/media.pk8
vendor/extra/media.x509.pem
vendor/extra/networkstack.pk8
vendor/extra/networkstack.x509.pem
vendor/extra/platform.pk8
vendor/extra/platform.x509.pem
vendor/extra/releasekey.pk8
vendor/extra/releasekey.x509.pem
vendor/extra/shared.pk8
vendor/extra/shared.x509.pem
```

### 4. Build

```bash
source build/envsetup.sh
lunch nad_santoni-userdebug
ALLOW_MISSING_DEPENDENCIES=true DISABLE_ARTIFACT_PATH_REQUIREMENTS=true mka nad -j$(nproc)
```

Output: `out/target/product/santoni/Nusantara-EOL-santoni-*-UNOFFICIAL-*.zip`

## Docs

- [CHANGELOG.md](CHANGELOG.md) — Full fix history
- [nad.dependencies](nad.dependencies) — Repo mapping (JSON)
- [docs/bug-analysis/](docs/bug-analysis/) — Root cause analysis for major bugs
  - [graphics-hal-bootloop.md](docs/bug-analysis/graphics-hal-bootloop.md)
  - [missing-launcher.md](docs/bug-analysis/missing-launcher.md)
  - [launcher3-silently-skipped.md](docs/bug-analysis/launcher3-silently-skipped.md)
  - [systemui-screendecorations-crash.md](docs/bug-analysis/systemui-screendecorations-crash.md)

## Device Picture

![Redmi 4X](https://cdn.tgdd.vn/Products/Images/42/99145/xiaomi-redmi-4x-400-400x460.png "Redmi 4X")
