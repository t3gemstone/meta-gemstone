FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://first-boot.sh"

do_install:append() {
    install -m 0755 ${WORKDIR}/first-boot.sh ${D}/init.d/89-firstboot
}

PACKAGES += "initramfs-module-firstboot"

RDEPENDS:initramfs-module-firstboot = "${PN}-base initramfs-module-rootfs"
FILES:initramfs-module-firstboot = "/init.d/89-firstboot"
