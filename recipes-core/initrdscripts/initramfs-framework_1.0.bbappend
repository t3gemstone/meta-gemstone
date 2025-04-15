FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "\
    file://first-boot.sh \
    file://usb-gadget.sh \
    file://gem-finish.sh \
    "

do_install:append() {
    install -m 0755 ${WORKDIR}/usb-gadget.sh ${D}/init.d/88-usbgadget
    install -m 0755 ${WORKDIR}/first-boot.sh ${D}/init.d/89-firstboot
    install -m 0755 ${WORKDIR}/gem-finish.sh ${D}/init.d/98-gemfinish
}

PACKAGES += "initramfs-module-firstboot initramfs-module-usbgadget initramfs-module-gemfinish"

RDEPENDS:initramfs-module-usbgadget = "${PN}-base"
RDEPENDS:initramfs-module-firstboot = "${PN}-base initramfs-module-rootfs"
RDEPENDS:initramfs-module-gemfinish = "${PN}-base"

FILES:initramfs-module-usbgadget = "/init.d/88-usbgadget"
FILES:initramfs-module-firstboot = "/init.d/89-firstboot"
FILES:initramfs-module-gemfinish = "/init.d/98-gemfinish"
