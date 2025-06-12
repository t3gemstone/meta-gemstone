SUMMARY = "QT Included Gemstone image"
DESCRIPTION = "Small image capable of booting a device. The kernel includes \
the Minimal RAM-based Initial Root Filesystem (initramfs), which finds the \
first 'init' program more efficiently."
LICENSE = "Apache-2.0"

IMAGE_FEATURES += "\
    splash \
    package-management \
    ssh-server-dropbear \
    weston \
    debug-tweaks \
    "

IMAGE_BOOT_FILES:append = " uEnv.txt"

TI_IMAGE_BOOT_FILES = "k3-am67a-beagley-ai.dtb"

IMAGE_BOOT_FILES:append:t3-gem-o1 = " ${TI_IMAGE_BOOT_FILES}"
IMAGE_BOOT_FILES:append:t3-gem-s1 = " ${TI_IMAGE_BOOT_FILES}"
IMAGE_BOOT_FILES:append:beagley-ai = " ${TI_IMAGE_BOOT_FILES}"

TOOLCHAIN_TARGET_TASK:remove = "target-sdk-provides-dummy"

inherit core-image

PREFERRED_PROVIDER_base-utils = "packagegroup-core-base-utils"
VIRTUAL-RUNTIME_base-utils = "packagegroup-core-base-utils"
VIRTUAL-RUNTIME_base-utils-hwclock = "util-linux-hwclock"
VIRTUAL-RUNTIME_base-utils-syslog = "syslog"

PACKAGE_INSTALL += "\
    ${VIRTUAL-RUNTIME_base-utils} \
    alsa-plugins-pulseaudio-conf \
    base-files \
    bash-completion \
    btrfs-tools \
    e2fsprogs-resize2fs \
    gstreamer1.0-libav \
    gstreamer1.0-meta-base \
    gstreamer1.0-plugins-bad \
    gstreamer1.0-plugins-bad-meta \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-good-udp \
    gstreamer1.0-plugins-good-meta \
    htop \
    kernel-modules \
    libdrm-dev \
    libgbm-dev \
    mesa-pvr-dev \
    nano \
    networkmanager \
    networkmanager-nmtui \
    packagegroup-gemqt6 \
    packagegroup-gemqt6-tools \
    packagegroup-gemqt6-translations \
    parted \
    pulseaudio \
    pulseaudio-module-alsa-card \
    pulseaudio-module-alsa-sink \
    pulseaudio-module-alsa-source \
    pulseaudio-server \
    qtdeclarative-examples \
    qtwayland \
    sudo \
    vim \
    vulkan-headers \
    vulkan-loader \
    vulkan-tools \
    "

TI_PACKAGES = " \
    ti-img-rogue-umlibs \
    ti-img-rogue-umlibs-firmware \
    ti-img-rogue-umlibs-tools \
    ti-img-rogue-driver \
    libegl-mesa-dev \
    libgles1-mesa-dev \
    libgles2-mesa-dev \
    libgles3-mesa-dev \
    libvk-rogue \
    "

PACKAGE_INSTALL:append:t3-gem-o1 = "${TI_PACKAGES}"
PACKAGE_INSTALL:append:t3-gem-s1 = "${TI_PACKAGES}"
PACKAGE_INSTALL:append:beagley-ai = "${TI_PACKAGES}"
