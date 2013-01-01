/*
 * Copyright 2002-2013 Drew Noakes
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 * More information about this project is available at:
 *
 *    http://drewnoakes.com/code/exif/
 *    http://code.google.com/p/metadata-extractor/
 */
package com.drew.metadata.exif;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

/**
 * Provides human-readable string representations of tag values stored in a {@link KodakMakernoteDirectory}.
 * 
 * @author Drew Noakes http://drewnoakes.com
 */
public class KodakMakernoteDescriptor extends TagDescriptor<KodakMakernoteDirectory>
{
    public KodakMakernoteDescriptor(@NotNull KodakMakernoteDirectory directory)
    {
        super(directory);
    }

    @Override
    @Nullable
    public String getDescription(int tagType)
    {
        switch (tagType) {
            case KodakMakernoteDirectory.TAG_QUALITY:
                return getQualityDescription();
            case KodakMakernoteDirectory.TAG_BURST_MODE:
                return getBurstModeDescription();
            case KodakMakernoteDirectory.TAG_SHUTTER_MODE:
                return getShutterModeDescription();
            case KodakMakernoteDirectory.TAG_FOCUS_MODE:
                return getFocusModeDescription();
            case KodakMakernoteDirectory.TAG_WHITE_BALANCE:
                return getWhiteBalanceDescription();
            case KodakMakernoteDirectory.TAG_FLASH_MODE:
                return getFlashModeDescription();
            case KodakMakernoteDirectory.TAG_FLASH_FIRED:
                return getFlashFiredDescription();
            case KodakMakernoteDirectory.TAG_COLOR_MODE:
                return getColorModeDescription();
            case KodakMakernoteDirectory.TAG_SHARPNESS:
                return getSharpnessDescription();
            default:
                return super.getDescription(tagType);
        }
    }

    @Nullable
    public String getSharpnessDescription()
    {
        return getIndexedDescription(KodakMakernoteDirectory.TAG_SHARPNESS, "Normal");
    }

    @Nullable
    public String getColorModeDescription()
    {
        Integer value = _directory.getInteger(KodakMakernoteDirectory.TAG_COLOR_MODE);
        if (value == null)
            return null;
        switch (value) {
            case 0x001: case 0x2000: return "B&W";
            case 0x002: case 0x4000: return "Sepia";
            case 0x003: return "B&W Yellow Filter";
            case 0x004: return "B&W Red Filter";
            case 0x020: return "Saturated Color";
            case 0x040: case 0x200: return "Neutral Color";
            case 0x100: return "Saturated Color";
            default: return "Unknown (" + value + ")";
        }
    }

    @Nullable
    public String getFlashFiredDescription()
    {
        return getIndexedDescription(KodakMakernoteDirectory.TAG_FLASH_FIRED, "No", "Yes");
    }

    @Nullable
    public String getFlashModeDescription()
    {
        Integer value = _directory.getInteger(KodakMakernoteDirectory.TAG_FLASH_MODE);
        if (value == null)
            return null;
        switch (value) {
            case 0x00: return "Auto";
            case 0x10: case 0x01: return "Fill Flash";
            case 0x20: case 0x02: return "Off";
            case 0x40: case 0x03: return "Red Eye";
            default: return "Unknown (" + value + ")";
        }
    }

    @Nullable
    public String getWhiteBalanceDescription()
    {
        return getIndexedDescription(KodakMakernoteDirectory.TAG_WHITE_BALANCE, "Auto", "Flash", "Tungsten", "Daylight");
    }

    @Nullable
    public String getFocusModeDescription()
    {
        return getIndexedDescription(KodakMakernoteDirectory.TAG_FOCUS_MODE, "Normal", null, "Macro");
    }

    @Nullable
    public String getShutterModeDescription()
    {
        Integer value = _directory.getInteger(KodakMakernoteDirectory.TAG_SHUTTER_MODE);
        if (value == null)
            return null;
        switch (value) {
            case 0: return "Auto";
            case 8: return "Aperture Priority";
            case 32: return "Manual";
            default: return "Unknown (" + value + ")";
        }
    }

    @Nullable
    public String getBurstModeDescription()
    {
        return getIndexedDescription(KodakMakernoteDirectory.TAG_BURST_MODE, "Off", "On");
    }

    @Nullable
    public String getQualityDescription()
    {
        return getIndexedDescription(KodakMakernoteDirectory.TAG_QUALITY, 1, "Fine", "Normal");
    }
}
