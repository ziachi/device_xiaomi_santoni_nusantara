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

| Repo | Branch |
|------|--------|
| [device_xiaomi_santoni_nusantara](https://github.com/ziachi/device_xiaomi_santoni_nusantara/tree/nad-10-dev) | `nad-10-dev` |
| [vendor_xiaomi_santoni_nusantara](https://github.com/ziachi/vendor_xiaomi_santoni_nusantara/tree/10) | `10` |
| [kernel_xiaomi_santoni_nusantara](https://github.com/ziachi/kernel_xiaomi_santoni_nusantara/tree/luuvy-4.9) | `luuvy-4.9` |
| [android_frameworks_base_nusantara](https://github.com/ziachi/android_frameworks_base_nusantara/tree/nad-10-dev-santoni) | `nad-10-dev-santoni` |

## Build

```bash
source build/envsetup.sh
lunch nad_santoni-userdebug
ALLOW_MISSING_DEPENDENCIES=true DISABLE_ARTIFACT_PATH_REQUIREMENTS=true mka nad -j$(nproc)
```

## Docs

- [CHANGELOG.md](CHANGELOG.md) — Full fix history
- [docs/bug-analysis/](docs/bug-analysis/) — Root cause analysis for major bugs
  - [graphics-hal-bootloop.md](docs/bug-analysis/graphics-hal-bootloop.md)
  - [missing-launcher.md](docs/bug-analysis/missing-launcher.md)
  - [systemui-screendecorations-crash.md](docs/bug-analysis/systemui-screendecorations-crash.md)

## Device Picture

![Redmi 4X](https://cdn.tgdd.vn/Products/Images/42/99145/xiaomi-redmi-4x-400-400x460.png "Redmi 4X")
