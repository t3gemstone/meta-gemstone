FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-7.2:"

SECTION = "kernel"
SUMMARY = "T3 Gemstone O1 Linux Kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

COMPATIBLE_MACHINE = "t3-gem-o1"

inherit kernel

DEPENDS += "gmp-native libmpc-native"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} ${EXTRA_DTC_ARGS}"

KERNEL_LOCALVERSION = "-t3"

S = "${WORKDIR}/git"

# SRC_URI = " \
#     git:///github.com/t3gemstone/linux-ng;protocol=https;branch=main \
# "

SRC_URI = "git://github.com/t3gemstone/linux-ng.git;protocol=https;branch=main"

# 7.2 version for 64-bit
SRCREV:aarch64 = "f4b77b31f1bbc4942f41218fa4e76fc8124be0ef"
PV:aarch64 = "7.2-stable"
BRANCH:aarch64 = "main"

do_configure:prepend() {
    cp ${S}/arch/arm64/configs/t3_gem_o1_defconfig ${S}/arch/arm64/configs/defconfig
}
