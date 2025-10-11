# Look in the generic major.minor directory for files
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

SECTION = "kernel"
SUMMARY = "T3 Gemstone S1 Linux Kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

COMPATIBLE_MACHINE = "t3-gem-s1"

inherit kernel

require recipes-kernel/linux/setup-defconfig.inc
require recipes-kernel/linux/ti-kernel.inc

DEPENDS += "gmp-native libmpc-native"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} ${EXTRA_DTC_ARGS}"

S = "${WORKDIR}/git"

# 6.1.83 version for 64-bit
SRCREV:aarch64 = "d3de2060e218efcd135482bd68f385843ed0ee30"
PV:aarch64 = "6.1.83+git"
BRANCH:aarch64 = "v6.1.83-ti-rt-arm64-r67-t3-gem-o1"

KERNEL_GIT_URI = "git://github.com/t3gemstone/linux.git"
