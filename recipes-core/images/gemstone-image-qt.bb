SUMMARY = "QT Included Gemstone image"
DESCRIPTION = "Small image capable of booting a device. The kernel includes \
the Minimal RAM-based Initial Root Filesystem (initramfs), which finds the \
first 'init' program more efficiently."
LICENSE = "Apache-2.0"

IMAGE_FEATURES += "\
    splash \
    package-management \
    ssh-server-dropbear\
    "

IMAGE_BOOT_FILES:append = "uEnv.txt"

TI_IMAGE_BOOT_FILES = "k3-am67a-beagley-ai.dtb"

IMAGE_BOOT_FILES:append:t3-gem-o1 = "${TI_IMAGE_BOOT_FILES}"
IMAGE_BOOT_FILES:append:t3-gem-s1 = "${TI_IMAGE_BOOT_FILES}"
IMAGE_BOOT_FILES:append:beagley-ai = "${TI_IMAGE_BOOT_FILES}"

TOOLCHAIN_TARGET_TASK:remove = "target-sdk-provides-dummy"

inherit core-image

PACKAGE_INSTALL += "\
    ${VIRTUAL-RUNTIME_base-utils} \
    base-files \
    parted \
    e2fsprogs-resize2fs \
    btrfs-tools \
    kernel-modules \
    libegl-mesa-dev \
    libgles1-mesa-dev \
    libgles2-mesa-dev \
    libgles3-mesa-dev \
    libgbm-dev \
    libdrm-dev \
    mesa-pvr-dev \
    kmscube \
    vim \
    nano \
    htop \
    busybox-init-scripts \
    packagegroup-gemqt6 \
    packagegroup-gemqt6-tools \
    packagegroup-gemqt6-translations \
    "

TI_PACKAGES = "\
    ti-img-rogue-umlibs \
    ti-img-rogue-umlibs-firmware \
    ti-img-rogue-umlibs-tools \
    ti-img-rogue-driver \
    "

PACKAGE_INSTALL:append:t3-gem-o1 = "${TI_PACKAGES}"
PACKAGE_INSTALL:append:t3-gem-s1 = "${TI_PACKAGES}"
PACKAGE_INSTALL:append:beagley-ai = "${TI_PACKAGES}"
