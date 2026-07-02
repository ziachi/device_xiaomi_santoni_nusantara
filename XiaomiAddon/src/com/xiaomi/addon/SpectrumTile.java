package com.xiaomi.addon;

import android.os.SystemProperties;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public class SpectrumTile extends TileService {

    private static final String SPECTRUM_PROP = "persist.spectrum.profile";

    private static final String[] PROFILE_LABELS = {
        "Balance", "Performance", "Battery", "Gaming"
    };

    @Override
    public void onStartListening() {
        super.onStartListening();
        updateTile();
    }

    @Override
    public void onClick() {
        super.onClick();
        int current = SystemProperties.getInt(SPECTRUM_PROP, 0);
        int next = (current + 1) % PROFILE_LABELS.length;
        SystemProperties.set(SPECTRUM_PROP, String.valueOf(next));
        updateTile();
    }

    private void updateTile() {
        Tile tile = getQsTile();
        if (tile == null) return;

        int profile = SystemProperties.getInt(SPECTRUM_PROP, 0);
        if (profile < 0 || profile >= PROFILE_LABELS.length) {
            profile = 0;
        }

        tile.setLabel("Spectrum");
        tile.setContentDescription(PROFILE_LABELS[profile]);

        switch (profile) {
            case 1: // Performance
            case 3: // Gaming
                tile.setState(Tile.STATE_ACTIVE);
                break;
            case 2: // Battery
                tile.setState(Tile.STATE_INACTIVE);
                break;
            default: // Balance
                tile.setState(Tile.STATE_ACTIVE);
                break;
        }

        // Use subtitle (Android 10+) to show profile name
        try {
            tile.setSubtitle(PROFILE_LABELS[profile]);
        } catch (NoSuchMethodError e) {
            // setSubtitle only available on API 29+, fallback to label
            tile.setLabel("Spectrum: " + PROFILE_LABELS[profile]);
        }

        tile.updateTile();
    }
}
