require recipes-bsp/u-boot/u-boot-ti.inc

SUMMARY = "T3 Gemstone O1 U-Boot"

COMPATIBLE_MACHINE = "t3-gem-o1"

PV = "2025.04"

UBOOT_GIT_URI = "git://github.com/t3gemstone/u-boot.git"
UBOOT_GIT_PROTOCOL = "https"
BRANCH = "v2025.04-rc3-t3-gem-o1"
SRCREV = "6dcca25e8272582345db12b972183709a9d88cdf"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:t3-gem-o1 = " \
    file://uEnv-desktop.txt \
    file://uEnv-rd.txt \
    file://fragment-mmc-env.config \
    "

UBOOT_CONFIG_FRAGMENTS:append:t3-gem-o1 = " fragment-mmc-env.config"
UBOOT_ENV:t3-gem-o1 = "uEnv"

do_configure:prepend:t3-gem-o1() {
    cp "${WORKDIR}/fragment-mmc-env.config" "${S}/configs/"

    if [ "${DISTRO_GEMSTONE_QT_INCLUDED}" = "1" ]; then
        mv "${WORKDIR}/uEnv-desktop.txt" "${WORKDIR}/uEnv.txt"
    else
        mv "${WORKDIR}/uEnv-rd.txt" "${WORKDIR}/uEnv.txt"
    fi
}

do_deploy[vardeps] += "SRCPV SRCREV PV SRCREV_FORMAT"

do_deploy:append:k3() {
    install -d ${DEPLOYDIR}/bsp-srcrevs
    printf '%s\n' "${SRCREV}" > ${DEPLOYDIR}/bsp-srcrevs/u-boot.rev
}
