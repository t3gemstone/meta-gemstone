FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:beagley-ai = " \
    file://uEnv.txt \
    file://fragment-mmc-env.config \
    "

SRC_URI:append:t3-gem-o1 = " \
    file://uEnv-gem.txt \
    file://fragment-mmc-env.config \
    "

do_configure:prepend:beagley-ai() {
    cp "${WORKDIR}/fragment-mmc-env.config" "${S}/configs/"
}

do_configure:prepend:t3-gem-o1() {
    cp "${WORKDIR}/fragment-mmc-env.config" "${S}/configs/"
}

UBOOT_CONFIG_FRAGMENTS:append:beagley-ai = " fragment-mmc-env.config"
UBOOT_CONFIG_FRAGMENTS:append:t3-gem-o1 = " fragment-mmc-env.config"

UBOOT_ENV:beagley-ai = "uEnv"
UBOOT_ENV:t3-gem-o1 = "uEnv"

