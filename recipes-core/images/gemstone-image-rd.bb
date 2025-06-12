# Simple initramfs image. Mostly used for live images.
SUMMARY = "Small image capable of booting a device."
DESCRIPTION = "Small image capable of booting a device. The kernel includes \
the Minimal RAM-based Initial Root Filesystem (initramfs), which finds the \
first 'init' program more efficiently."
LICENSE = "Apache-2.0"

PACKAGE_INSTALL = "\
    ${VIRTUAL-RUNTIME_base-utils} \
    base-files \
    initramfs-framework-base \
    initramfs-module-rootfs \
    initramfs-module-firstboot \
    initramfs-module-usbgadget \
    initramfs-module-gemfinish \
    parted \
    e2fsprogs-resize2fs \
    btrfs-tools \
    kernel-module-usb-f-acm \
    kernel-module-u-serial \
    kernel-module-libcomposite \
    "

PACKAGE_INSTALL:remove:qemuarm64 = "kernel-module-usb-f-acm kernel-module-u-serial kernel-module-libcomposite"

# Don't allow the initramfs to contain a kernel
PACKAGE_EXCLUDE = "kernel-image-*"

IMAGE_NAME_SUFFIX ?= ""
IMAGE_LINGUAS = ""
IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

DEPENDS:beagley-ai += "ti-img-rogue-driver"
DEPENDS:t3-gem-o1 += "ti-img-rogue-driver"

inherit core-image

# Use the same restriction as initramfs-module-install
COMPATIBLE_HOST = '(x86_64.*|i.86.*|arm.*|aarch64.*|loongarch64.*)-(linux.*|freebsd.*)'
