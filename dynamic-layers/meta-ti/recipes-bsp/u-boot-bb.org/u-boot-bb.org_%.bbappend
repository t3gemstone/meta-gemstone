FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:beagley-ai = " \
    file://uEnv.txt \
    file://fragment-mmc-env.config \
    "

do_configure:prepend:beagley-ai() {
    cp "${WORKDIR}/fragment-mmc-env.config" "${S}/configs/"
}

UBOOT_CONFIG_FRAGMENTS:append:beagley-ai = " fragment-mmc-env.config"

UBOOT_ENV:beagley-ai = "uEnv"

