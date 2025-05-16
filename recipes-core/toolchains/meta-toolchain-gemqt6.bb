SUMMARY = "Meta package for building an installable Qt6 toolchain and SDK for Gemstone distro"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit populate_sdk populate_sdk_qt6

TOOLCHAIN_TARGET_TASK += "\
    libegl-mesa-dev \
    libgles1-mesa-dev \
    libgles2-mesa-dev \
    libgles3-mesa-dev \
    libgbm-dev \
    libdrm-dev \
    mesa-pvr-dev \
    "

TI_TOOLCHAIN_TARGET_TASK = "\
    ti-img-rogue-driver \
    ti-img-rogue-umlibs \
    "

TOOLCHAIN_TARGET_TASK:append:t3-gem-o1 = "${TI_TOOLCHAIN_TARGET_TASK}"
TOOLCHAIN_TARGET_TASK:append:t3-gem-s1 = "${TI_TOOLCHAIN_TARGET_TASK}"
TOOLCHAIN_TARGET_TASK:append:beagley-ai = "${TI_TOOLCHAIN_TARGET_TASK}"
