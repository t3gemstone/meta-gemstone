SUMMARY = "Collection of boot files for Gemstone distribution"

LICENSE = "Apache-2.0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install[depends] += "\
    virtual/kernel:do_deploy \
    gemstone-image-rd:do_image_complete \
    "

RDEPENDS:${PN}:t3-gem-o1 += "u-boot-t3-gem-o1"
RDEPENDS:${PN}:beagley-ai += "u-boot-bb.org"

do_install:append:j722s() {
    install -d "${D}/boot/overlays"

    install -m 0644 "${DEPLOY_DIR_IMAGE}/tiboot3.bin" "${D}/boot"
    install -m 0644 "${DEPLOY_DIR_IMAGE}/gemstone-image-rd-${MACHINE}.cpio.gz" "${D}/boot"
    install -m 0644 "${DEPLOY_DIR_IMAGE}/k3-am67a-${MACHINE}.dtb" "${D}/boot"

    overlays=$(find "${DEPLOY_DIR_IMAGE}" -mindepth 1 -maxdepth 1 -type 'f' -name 'k3-am67a-*.dtbo')
    for overlay in $overlays; do
        install -m 0644 "${overlay}" "${D}/boot/overlays"
    done
}

FILES:${PN} += "/boot/*"
