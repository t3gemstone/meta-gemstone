FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:beagley-ai = " \
    file://uEnv-desktop.txt \
    file://uEnv-rd.txt \
    file://fragment-mmc-env.config \
    "

do_configure:prepend:beagley-ai() {
    cp "${WORKDIR}/fragment-mmc-env.config" "${S}/configs/"

    if [ "${DISTRO_GEMSTONE_QT_INCLUDED}" = "1" ]; then
        cp "${WORKDIR}/uEnv-desktop.txt" "${WORKDIR}/uEnv.txt"
    else
        cp "${WORKDIR}/uEnv-rd.txt" "${WORKDIR}/uEnv.txt"
    fi
}

do_deploy[vardeps] += "SRCPV SRCREV PV SRCREV_FORMAT"

do_deploy:append:k3() {
    install -d ${DEPLOYDIR}/bsp-srcrevs
    printf '%s\n' "${SRCREV}" > ${DEPLOYDIR}/bsp-srcrevs/u-boot.rev
}

UBOOT_CONFIG_FRAGMENTS:append:beagley-ai = " fragment-mmc-env.config"

UBOOT_ENV:beagley-ai = "uEnv"
