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

PR[vardepsexpands] += "BSP_REV_INPUTS"
BSP_REV_INPUTS = "${DEPLOY_DIR_IMAGE}/bsp-srcrevs/u-boot.rev ${DEPLOY_DIR_IMAGE}/bsp-srcrevs/kernel.rev"

def bsp_rev(d):
    import os, re
    deploy = d.getVar("DEPLOY_DIR_IMAGE")
    revs = []
    for name in ("u-boot", "kernel", "initrd"):
        p = os.path.join(deploy, "bsp-srcrevs", f"{name}.rev")
        if os.path.exists(p):
            with open(p) as f:
                s = f.read().strip()
            revs.append(f"{name}{s[:5]}")
        else:
            revs.append("norev")
    return (("." + ".".join(revs)) if revs else "")

PV = "1.0"
PR = "r0${@bsp_rev(d)}"

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
