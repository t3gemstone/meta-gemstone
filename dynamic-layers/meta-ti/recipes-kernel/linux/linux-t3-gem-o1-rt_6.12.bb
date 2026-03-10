# Look in the generic major.minor directory for files
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.12:"

SECTION = "kernel"
SUMMARY = "T3 Gemstone O1 Linux Kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

COMPATIBLE_MACHINE = "t3-gem-o1"

inherit kernel

require recipes-kernel/linux/setup-defconfig.inc
require recipes-kernel/linux/ti-kernel.inc

DEPENDS += "gmp-native libmpc-native"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} ${EXTRA_DTC_ARGS}"

S = "${WORKDIR}/git"

# 6.12.24 version for 64-bit
SRCREV:aarch64 = "c0d426f64f22aa7ddba37b5552dd5b2013654990"
PV:aarch64 = "6.12.24+git"
BRANCH:aarch64 = "v6.12.24-ti-arm64-r43-t3-gem-o1"

KERNEL_GIT_URI = "git://github.com/t3gemstone/linux.git"
