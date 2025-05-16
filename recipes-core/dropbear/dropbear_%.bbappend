do_install:append:t3-gem-s1() {
    install -d ${D}${sysconfdir}/rcS.d
    install -d ${D}${sysconfdir}/rcK.d

    ln -s ../init.d/dropbear ${D}${sysconfdir}/rcS.d/S12dropbear
    ln -s ../init.d/dropbear ${D}${sysconfdir}/rcK.d/S12dropbear
}

FILES:${PN}:t3-gem-s1 += "\
    /etc/init.d/dropbear \
    /etc/rcS.d/* \
    /etc/rcK.d/* \
    "
