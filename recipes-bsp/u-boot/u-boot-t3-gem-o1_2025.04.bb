require recipes-bsp/u-boot/u-boot-ti.inc

SUMMARY = "T3 Gemstone O1 U-Boot"

COMPATIBLE_MACHINE = "t3-gem-o1"

PV = "2025.04"

UBOOT_GIT_URI = "git://github.com/akaybayram0/u-boot.git"
UBOOT_GIT_PROTOCOL = "https"
BRANCH = "v2025.04-rc3-t3-gem-o1"
SRCREV = "d4a65c7f993a977bd9cfa6b9ac83ba7a17701f20"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:t3-gem-o1 = " \
    file://uEnv.txt \
    file://fragment-mmc-env.config \
    "

UBOOT_CONFIG_FRAGMENTS:append:t3-gem-o1 = " fragment-mmc-env.config"
UBOOT_ENV:t3-gem-o1 = "uEnv"

do_configure:prepend:t3-gem-o1() {
    cp "${WORKDIR}/fragment-mmc-env.config" "${S}/configs/"
}
