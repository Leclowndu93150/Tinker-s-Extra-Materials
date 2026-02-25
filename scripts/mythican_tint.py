#!/usr/bin/env python3
from PIL import Image
import os
import sys

MODID = "tinkers_extra_materials"
MATERIAL = "mythican"
SUFFIX = f"{MODID}_{MATERIAL}"

GRAY_TO_COLOR = {
    63:  (31,  34,  65),
    102: (88,  103, 142),
    140: (113, 169, 118),
    178: (137, 194, 135),
    216: (169, 210, 158),
    255: (224, 239, 212),
}

TC_TEXTURES_ROOT = os.path.normpath(os.path.join(
    os.path.dirname(__file__), "..",
    "modsources", "TinkersConstruct-1.20.1",
    "src", "main", "resources", "assets", "tconstruct", "textures", "item", "tool"
))

OUTPUT_ROOT = os.path.normpath(os.path.join(
    os.path.dirname(__file__), "..",
    "src", "main", "resources", "assets", "tconstruct", "textures", "item", "tool"
))

TOOL_PARTS = {
    "pickaxe":       ["head", "handle", "binding"],
    "sword":         ["blade", "guard", "handle"],
    "dagger":        ["blade", "crossguard", "guard", "handle"],
    "hand_axe":      ["head", "binding"],
    "kama":          ["head", "binding"],
    "broad_axe":     ["blade", "back", "binding", "handle"],
    "cleaver":       ["head", "guard", "handle", "shield"],
    "pickadze":      ["pick", "adze"],
    "mattock":       ["pick", "axe"],
    "excavator":     ["head", "binding", "handle", "grip"],
    "sledge_hammer": ["head", "front", "back", "handle"],
    "vein_hammer":   ["head", "front", "grip", "handle"],
    "scythe":        ["head", "accessory", "binding", "handle"],
    "battlesign":    ["head", "binding", "handle"],
    "longbow":       ["limb_top", "limb_bottom", "grip", "bowstring"],
    "crossbow":      ["body", "limb", "binding", "bowstring"],
    "javelin":       ["head", "handle", "guard", "grip"],
    "fishing_rod":   ["rod", "hook"],
    "melting_pan":   ["head", "handle"],
    "war_pick":      ["body", "limb", "bowstring"],
    "swasher":       ["blade", "barrel", "handle"],
}

PART_ITEMS = [
    "ingot", "large_plate", "maille", "repair_kit",
    "small_blade", "tool_binding", "tool_handle",
    "tough_binding", "tough_handle",
]

LARGE_TOOLS = [
    "broad_axe", "cleaver", "excavator", "sledge_hammer",
    "vein_hammer", "scythe", "longbow", "javelin",
]

ARMOR_PARTS = {
    "armor/plate/boots":       ["maille", "plating"],
    "armor/plate/chestplate":  ["maille", "plating"],
    "armor/plate/helmet":      ["maille", "plating"],
    "armor/plate/leggings":    ["maille", "plating"],
    "armor/plate/shield":      ["core", "plating"],
    "armor/travelers/boots":   ["cuirass", "metal"],
    "armor/travelers/goggles": ["cuirass", "metal"],
    "armor/travelers/pants":   ["cuirass", "metal"],
    "armor/travelers/vest":    ["cuirass", "metal"],
    "armor/travelers/shield":  ["cuirass"],
}


def find_closest_color(gray_val):
    keys = sorted(GRAY_TO_COLOR.keys())
    if gray_val <= keys[0]:
        return GRAY_TO_COLOR[keys[0]]
    if gray_val >= keys[-1]:
        return GRAY_TO_COLOR[keys[-1]]
    for i in range(len(keys) - 1):
        lo, hi = keys[i], keys[i + 1]
        if lo <= gray_val <= hi:
            t = (gray_val - lo) / (hi - lo)
            c_lo = GRAY_TO_COLOR[lo]
            c_hi = GRAY_TO_COLOR[hi]
            return tuple(int(c_lo[j] + t * (c_hi[j] - c_lo[j])) for j in range(3))
    return GRAY_TO_COLOR[keys[-1]]


def tint_texture(src_path, dst_path):
    img = Image.open(src_path).convert("RGBA")
    pixels = img.load()
    w, h = img.size

    for y in range(h):
        for x in range(w):
            r, g, b, a = pixels[x, y]
            if a == 0:
                continue
            gray = r
            cr, cg, cb = find_closest_color(gray)
            pixels[x, y] = (cr, cg, cb, a)

    os.makedirs(os.path.dirname(dst_path), exist_ok=True)
    img.save(dst_path)


def process_all():
    count = 0

    for tool, parts in TOOL_PARTS.items():
        for part in parts:
            src = os.path.join(TC_TEXTURES_ROOT, tool, f"{part}.png")
            if os.path.exists(src):
                dst = os.path.join(OUTPUT_ROOT, tool, f"{part}_{SUFFIX}.png")
                tint_texture(src, dst)
                print(f"  {tool}/{part}.png -> {part}_{SUFFIX}.png")
                count += 1

            if tool in LARGE_TOOLS:
                src_large = os.path.join(TC_TEXTURES_ROOT, tool, "large", f"{part}.png")
                if os.path.exists(src_large):
                    dst_large = os.path.join(OUTPUT_ROOT, tool, "large", f"{part}_{SUFFIX}.png")
                    tint_texture(src_large, dst_large)
                    print(f"  {tool}/large/{part}.png -> large/{part}_{SUFFIX}.png")
                    count += 1

    for part in PART_ITEMS:
        src = os.path.join(TC_TEXTURES_ROOT, "parts", f"{part}.png")
        if os.path.exists(src):
            dst = os.path.join(OUTPUT_ROOT, "parts", f"{part}_{SUFFIX}.png")
            tint_texture(src, dst)
            print(f"  parts/{part}.png -> {part}_{SUFFIX}.png")
            count += 1

    for armor_path, parts in ARMOR_PARTS.items():
        for part in parts:
            src = os.path.join(TC_TEXTURES_ROOT, armor_path, f"{part}.png")
            if os.path.exists(src):
                dst = os.path.join(OUTPUT_ROOT, armor_path, f"{part}_{SUFFIX}.png")
                tint_texture(src, dst)
                print(f"  {armor_path}/{part}.png -> {part}_{SUFFIX}.png")
                count += 1

    print(f"\nDone! Generated {count} mythican textures.")


if __name__ == "__main__":
    process_all()
