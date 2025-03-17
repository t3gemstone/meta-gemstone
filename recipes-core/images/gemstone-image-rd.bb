# Simple initramfs image. Mostly used for live images.
SUMMARY = "Small image capable of booting a device."
DESCRIPTION = "Small image capable of booting a device. The kernel includes \
the Minimal RAM-based Initial Root Filesystem (initramfs), which finds the \
first 'init' program more efficiently."
LICENSE = "Apache-2.0"

PACKAGE_INSTALL = "\
    ${VIRTUAL-RUNTIME_base-utils} \
    base-files \
    btrfs-tools \
    e2fsprogs-resize2fs \
    initramfs-framework-base \
    initramfs-module-firstboot \
    initramfs-module-rootfs \
    parted \
    psplash \
    "

# Don't allow the initramfs to contain a kernel
PACKAGE_EXCLUDE = "kernel-image-*"

IMAGE_NAME_SUFFIX ?= ""
IMAGE_LINGUAS = ""
IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

DEPENDS:beagley-ai += "ti-img-rogue-driver virtual/bootloader virtual/kernel"

inherit core-image

# Use the same restriction as initramfs-module-install
COMPATIBLE_HOST = '(x86_64.*|i.86.*|arm.*|aarch64.*|loongarch64.*)-(linux.*|freebsd.*)'
