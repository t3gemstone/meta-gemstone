FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI += "\
    file://setipaddr \
    file://modprobe \
    "

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rcS.d
    install -d ${D}${sysconfdir}/rcK.d
    install -m 0755 ${WORKDIR}/setipaddr ${D}${sysconfdir}/init.d/setipaddr
    install -m 0755 ${WORKDIR}/modprobe ${D}${sysconfdir}/init.d/modprobe

    ln -s ../init.d/modprobe ${D}${sysconfdir}/rcS.d/S10modprobe
    ln -s ../init.d/setipaddr ${D}${sysconfdir}/rcS.d/S11setipaddr

    ln -s ../init.d/modprobe ${D}${sysconfdir}/rcK.d/S10modprobe
    ln -s ../init.d/setipaddr ${D}${sysconfdir}/rcK.d/S11setipaddr
}

FILES:${PN} += "\
    /etc/init.d/modprobe \
    /etc/init.d/setipaddr \
    /etc/rcS.d/* \
    /etc/rcK.d/* \
    "
